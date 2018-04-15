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
	添加商品属性
	<hr>
	<form action="arrtAdd.do" method="post" enctype="multipart/form-data">
		<input type="text" name="flbh2" value="${flbh2 }">
		<table id="attr_tab_0" border="1" style="text-align: center;">
			<tr>
				<td>属性名: <input type="text" name="list_attr[0].shxm_mch"></td>
				<td></td>
				<td><a href="javascript:;" onclick="addAttrValue(0)">添加值</a></td>
			</tr>
			<tr>
				<td>属性值: <input type="text" name="list_attr[0].list_value[0].shxzh"></td>
				<td>单位: <input type="text" name="list_attr[0].list_value[0].shxzh_mch"></td>
				<td><a href="javascript:;" onclick="delAttrValue(this)">删除</a></td>
			</tr>
			<tr>
				<td>属性值: <input type="text" name="list_attr[0].list_value[1].shxzh"></td>
				<td>单位: <input type="text" name="list_attr[0].list_value[1].shxzh_mch"></td>
				<td><a href="javascript:;" onclick="delAttrValue(this)">删除</a></td>
			</tr>
		</table>
		<br>
		<table id="attr_tab_1" border="1" style="text-align: center;">
			<tr>
				<td>属性名: <input type="text" name="list_attr[1].shxm_mch"></td>
				<td></td>
				<td><a href="javascript:;" onclick="addAttrValue(1)">添加值</a></td>
			</tr>
			<tr>
				<td>属性值: <input type="text" name="list_attr[1].list_value[0].shxzh"></td>
				<td>单位: <input type="text" name="list_attr[1].list_value[0].shxzh_mch"></td>
				<td><a href="javascript:;" onclick="delAttrValue(this)">删除</a></td>
			</tr>
			<tr>
				<td>属性值: <input type="text" name="list_attr[1].list_value[1].shxzh"></td>
				<td>单位: <input type="text" name="list_attr[1].list_value[1].shxzh_mch"></td>
				<td><a href="javascript:;" onclick="delAttrValue(this)">删除</a></td>
			</tr>
		</table>
		<br><a href="javascript:;">添加table: </a><input type="submit" value="提交">
	</form>
	<script type="text/javascript">
	function addAttrValue(index) {
		var count = 0;
		var tr = '<tr><td>属性值: <input type="text" name="list_attr['+index+'].list_value['+(++count)+'].shxzh"></td><td>单位: <input type="text" name="list_attr['+index+'].list_value['+count+'].shxzh_mch"></td><td><a href="javascript:;" onclick="delAttrValue(this)">删除</a></td></tr>';
		$("#attr_tab_"+index).append(tr);
		
	}
	function delAttrValue(obj){
		$(obj).parent().parent().remove();
	}
	 var table = 0;
	function addTable(index){
		var count_1 = 0;
		var a = '<br><table id="attr_tab_'+(table+1)+'" border="1" style="text-align: center;">';
		var b = '<tr><td>属性名: <input type="text" name="list_attr['+(table+1)+'].shxm_mch"></td><td></td><td><a href="javascript:;" onclick="addAttrValue('+(index+1)+')">添加值</a></td></tr>';
		var c = '<tr><td>属性值: <input type="text" name="list_attr['+(table)+'].list_value['+count_1+'].shxzh"></td><td>单位: <input type="text" name="list_attr['+(index+1)+'].list_value['+count_1+'].shxzh_mch"></td><td><a href="javascript:;" onclick="delAttrValue(this)">删除</a></td></tr>';
		$("#attr_tab_"+table).after(a+b+c);
		table++;
	} 
	</script>
</body>
</html>