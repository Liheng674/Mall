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
	<table border="1px" cellspacing="0" class="easyui-datagrid" title="Basic DataGrid" style="width:700px;height:250px;"
			data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80">ID</th>
				<th data-options="field:'productid',width:100">属性名</th>
				<th data-options="field:'listprice',width:80,align:'right'">创建时间</th>
				<th data-options="field:'unitcost',width:80,align:'right'">属性值</th>
			</tr>
			<c:forEach items="${list_attr }" var="attr">
				<tr>
					<td>${attr.id }</td>
					<td>${attr.shxm_mch }</td>
					<td><fmt:formatDate value="${attr.chjshj }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:forEach items="${attr.list_value }" var="value">
							${value.shxzh }${value.shxzh_mch }
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</thead>
	</table>

</body>
</html>