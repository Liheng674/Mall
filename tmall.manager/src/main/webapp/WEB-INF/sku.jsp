<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	
</script>
<title>硅谷商城</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">   
	   <div data-options="region:'north',title:'商品库存管理',split:true" style="height:100px;">
	   <div style="margin-top: 10px">
	   	一级: <select data-options="width: 100" class="easyui-combobox" id="sku_class_1" onchange="getClass2_sku(this.value)"><option>请选择</select>
		二级: <select data-options="width: 100" class="easyui-combobox" id="sku_class_2"><option>请选择</select> 
	   </div>
	   </div>   
	   <div data-options="region:'west',title:'操作',split:true" style="width:100px;">
	   	<div style="margin-left: 10px">
			查询<br>
			<a href="javascript:;" onclick="go_skuAdd()">添加</a><br>
			修改<br>
			删除<br>
		</div>   
	</div>   
	   <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
			<table id="attrListInner"></table>
		</div>   
	</div>
	
<script type="text/javascript">

	$(function(){
		$('#sku_class_1').combobox({    
		    url:'js/json/class_1.js',    
		    valueField:'id',
		    textField:'flmch1',
		    // 原来的onChange时间失效, 使用easyUI的combo的onChange事件
		    onChange: function (){
		    	// 获取当前选中下拉列表的值
		    	var sku_class1_id = $(this).combobox("getValue");
				$('#sku_class_2').combobox({    
				    url:"js/json/class_2_"+sku_class1_id+".js",    
				    valueField:'id',
				    textField:'flmch2',
				    onChange: function(){
				    	
				    }
				});
			}
		});
	});

	/* $(function(){
		$.getJSON("js/json/class_1.js",function(data){
			$.each(data, function(i,item){
			    $("#sku_class_1").append("<option value="+item.id+">"+item.flmch1);
			  });
		});
	}); */
	/* function getClass2_sku(class1_id){
		$.getJSON("js/json/class_2_"+class1_id+".js",function(data){
			$("#sku_class_2").empty();
			$.each(data, function(i,item){
			    $("#sku_class_2").append("<option value="+item.id+">"+item.flmch2);
			  });
		});
	} */
	function go_skuAdd(){
		var sku_class_1_val = $('#sku_class_1').combobox("getValue");
		var sku_class_2_val = $('#sku_class_2').combobox("getValue");
		//window.location.href="goSkuAdd.do?flbh1="+sku_class_1_val+"&flbh2="+sku_class_2_val;
		add_tab("goSkuAdd.do?flbh1="+sku_class_1_val+"&flbh2="+sku_class_2_val,"添加商品库存");
	}
	</script>
</body>
</html>