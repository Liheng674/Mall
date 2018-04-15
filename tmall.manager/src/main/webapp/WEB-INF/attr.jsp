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
	   <div data-options="region:'north',title:'商品属性管理',split:true" style="height:100px;">
	   <div style="margin-top: 10px">
		   	一级: <select data-options="width: 100" class="easyui-combobox" id="attr_class_1" onchange="getClass2_attr(this.value)"><option>请选择</select>
		 	二级: <select data-options="width: 100" class="easyui-combobox" id="attr_class_2" onchange="getAttrList_json(this.value)"><option>请选择</select><br> 
	   </div>
	   </div>   
	   <div data-options="region:'west',title:'操作',split:true" style="width:100px;">
	   	<div style="margin-left: 10px">
			查询<br>
			<a href="javascript:;" onclick="go_attrAdd()">添加</a><br>
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
		/* $.getJSON("js/json/class_1.js",function(data){
			$.each(data, function(i,item){
			    $("#attr_class_1").append("<option value="+item.id+">"+item.flmch1);
			  });
		}); */
		// 使用Javascript创建下拉列表框
		$('#attr_class_1').combobox({    
		    url:'js/json/class_1.js',    
		    valueField:'id',
		    textField:'flmch1',
		    // 原来的onChange时间失效, 使用easyUI的combo的onChange事件
		    onChange: function getClass2_attr(){
		    	// 获取当前选中下拉列表的值
		    	var attr_class1_id = $(this).combobox("getValue");
				/* $.getJSON("js/json/class_2_"+attr_class1_id+".js",function(data){
					$("#attr_class_2").empty();
					$.each(data, function(i,item){
					    $("#attr_class_2").append("<option value="+item.id+">"+item.flmch2);
					  });
				}); */
				$('#attr_class_2').combobox({    
				    url:"js/json/class_2_"+attr_class1_id+".js",    
				    valueField:'id',
				    textField:'flmch2',
				    onChange: function(){
				    	var flbh2 = $(this).combobox("getValue");
				    	getAttrList_json(flbh2);
				    }
				});
			}
		});
	});
	
	/* 
	function getAttrList(flbh2){
		// 异步查询ajax
		$.post("get_attr_list.do",{flbh2:flbh2},function(data){
			$("#attrListInner").html(data);
		});
		
	} */
	function getAttrList_json(flbh2){
		//使用easyUI
		$('#attrListInner').datagrid({    
		    url:'get_attr_list_json.do',    
	        queryParams: {
	    		flbh2: flbh2
	    	},
		    columns:[[    
		        {field:'id',title:'ID',width:100},    
		        {field:'shxm_mch',title:'属性名称',width:100},    
		        {field:'chjshj',title:'创建时间',width:200,
		        	formatter: function(value,row,index){
		        		var date = new Date(value);
						return date.toLocaleString();
					}
		        },
		        {field:'list_value',title:'属性值',width:500,
		        	formatter: function(value,row,index){
		        		var val = "";
		        		for(var i = 0;i< value.length;i++){
		        			val = val + value[i].shxzh+value[i].shxzh_mch+" ";
		        		}
						return val;
					}
		        },
		    ]]    
		});
	}
	
	function go_attrAdd(){
		var attr_class_2_val = $('#attr_class_2').combobox("getValue");
		//window.location.href="goAttrAdd.do?flbh2="+attr_class_2_val;
		add_tab("goAttrAdd.do?flbh2="+attr_class_2_val,"添加商品属性");
	}
	</script>
</body>
</html>