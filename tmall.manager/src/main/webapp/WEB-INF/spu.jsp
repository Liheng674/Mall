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
	   <div data-options="region:'north',title:'商品信息管理',split:true" style="height:100px;">
	   	<div style="margin-top: 10px">
		   	一级: <select data-options="width: 100" class="easyui-combobox" id="spu_class_1" onchange="getClass2_spu(this.value)"><option>请选择</select>
			 二级: <select data-options="width: 100" class="easyui-combobox" id="spu_class_2"><option>请选择</select> 
			 品牌: <select data-options="width: 100" class="easyui-combobox" id="mall_flow"><option>请选择</select><br>
	 	</div>
	   </div>   
	   <div data-options="region:'west',title:'操作',split:true" style="width:100px;">
	   	<div style="margin-left: 10px">
			查询<br>
			<a href="javascript:;" onclick="go_spuAdd()">添加</a><br>
			修改<br>
			删除<br>
		</div>   
	</div>   
	   <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
			<table id="attrListInner"></table>
		</div>   
	</div>
	
	<script type="text/javascript">
	
	/* function add_tab_spu(url,title){
		// 判断选项卡是否存在
		var b = $('#tt_spu').tabs('exists',title);
		if(!b){
			// add a new tab panel    
			$('#tt_spu').tabs('add',{    
			    title:title,    
			    href: url,    
			    closable:true,    
			    tools:[{    
			        iconCls:'icon-mini-refresh',    
			        handler:function(){    
			            //alert('refresh');    
			        }    
			    }]    
			}); 
		}else{
			$('#tt_spu').tabs('select',title);
		}
	} */
	
	$(function(){
		/* $.getJSON("js/json/class_1.js",function(data){
			$.each(data, function(i,item){
			    $("#spu_class_1").append("<option value="+item.id+">"+item.flmch1);
			  });
		}); */
		$('#spu_class_1').combobox({    
		    url:'js/json/class_1.js',    
		    valueField:'id',
		    textField:'flmch1',
		    onChange: function(){
		    	var spu_class1_id = $(this).combobox("getValue");
		    	/* $.getJSON("js/json/class_2_"+class1_id+".js",function(data){
					$("#spu_class_2").empty();
					$.each(data, function(i,item){
					    $("#spu_class_2").append("<option value="+item.id+">"+item.flmch2);
					  });
				}); */
				$('#spu_class_2').combobox({    
				    url:"js/json/class_2_"+spu_class1_id+".js",    
				    valueField:'id',
				    textField:'flmch2',
				    onChange: function(){
				    	/* function getMall(class1_id){
				    		$("#mall_flow").empty();
				    		$.getJSON("js/json/tm_class_1_"+class1_id+".js",function(data){
				    			$.each(data, function(i,item){
				    			    $("#mall_flow").append("<option value="+item.id+">"+item.ppmch);
				    			  });
				    		});
				    	} */
				    	$('#mall_flow').combobox({    
						    url:"js/json/tm_class_1_"+spu_class1_id+".js",    
						    valueField:'id',
						    textField:'ppmch',
						    onChange: function(){
						    	
						    }
						});
				    }
			    });
	    	}
		});
	});
	
	function go_spuAdd(){
		//var spu_class_1_val = $('#spu_class_1').val();
		//var spu_class_2_val = $('#spu_class_2').val();
		//var mall_flow_val = $('#mall_flow').val();
		var spu_class_1_val = $('#spu_class_1').combobox("getValue");
		var spu_class_2_val = $('#spu_class_2').combobox("getValue");
		var mall_flow_val = $('#mall_flow').combobox("getValue");
		//window.location.href="goSpuAdd.do?flbh1="+spu_class_1_val+"&flbh2="+spu_class_2_val+"&pp_id="+mall_flow_val;
		add_tab("goSpuAdd.do?flbh1="+spu_class_1_val+"&flbh2="+spu_class_2_val+"&pp_id="+mall_flow_val,"添加商品信息");
	}
	</script>
</body>
</html>