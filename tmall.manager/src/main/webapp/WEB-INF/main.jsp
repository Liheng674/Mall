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
<link rel="stylesheet" type="text/css" href="js/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	// 操作成功后返回之前页面
	$(function(){
		var url = "${url}";
		var title = "${title}";
		if(url != "" && title != ""){
			add_tab(url,title);
		}
	});

	function add_tab(url,title){
		// 判断选项卡是否存在
		var b = $('#tt').tabs('exists',title);
		if(!b){
			// add a new tab panel    
			$('#tt').tabs('add',{    
			    title:title,    
			    href: url,    
			    closable:true,    
			    tools:[{    
			        iconCls:'icon-mini-refresh',    
			        handler:function(){    
			            //alert('refresh');
			        	var tab = $('#tt').tabs('getSelected');  // 获取选择的面板
			        	tab.panel('refresh');
			        }    
			    }]    
			}); 
		}else{
			$('#tt').tabs('select',title);
		}
	}
	
</script>
<title>硅谷商城</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;"><h1 style="margin-left: 10px">后台管理首页</h1></div>
	<hr>
	<div data-options="region:'west',split:true,title:'West'" style="width:180px;padding:10px;">
		<div class="easyui-accordion" style="width:500px;">
			<div title="About" data-options="iconCls:'icon-reload'" style="overflow:auto;">
			<ul>
				<li><a href="javascript:add_tab('goSpu.do','商品信息管理')">商品信息管理</a></li>
				<li><a href="javascript:add_tab('goAttr.do','商品属性管理')">商品属性管理</a></li>
				<li><a href="javascript:add_tab('goSku.do','商品库存管理')">商品库存管理</a></li>
			</ul>
			</div>
			<div title="About" data-options="iconCls:'icon-reload'" style="overflow:auto;">
			<ul>
				<li>商品缓存管理</li>
			</ul>
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
	
		<div id="tt" class="easyui-tabs" style="height: 500px">
		
		</div>
	</div>
</body>
</html>