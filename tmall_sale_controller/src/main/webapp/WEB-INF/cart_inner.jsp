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
	function checkOrder(){
		$("#goto_checkOrder").submit();
	}
</script>
<title>Title</title>
</head>
<body>
	<div class="Cbox">
		<table class="table table-striped table-bordered table-hover">
		   <thead>
		     <tr>
		       <th>商品图片</th>
		       <th>商品名称</th>
		       <th>商品属性</th>
		       <th>商品价格</th>
		       <th>商品数量</th>
		       <th>操作</th>
		     </tr>
		   </thead>
		   <tbody>
		   	<c:forEach items="${list_cart }" var="cart">
		     <tr>
		       <td><input onclick="if_checked(this.checked, ${cart.sku_id})" type="checkbox" ${cart.shfxz=='1'?"checked":"" } value="${cart.shfxz }">是否选中： ${cart.shfxz } 
		       		<img width="90px" height="60px" src="upload/image/${cart.shp_tp }" alt=""></td>
		       <td>${cart.sku_mch }</td>
		       <td>
		       		颜色：<span style='color:#ccc'>白色</span><br>
		       		尺码：<span style='color:#ccc'>L</span>
		       </td>
		       <td>${cart.sku_jg }</td>
		       <td><input type="text" name="min" value="${cart.tjshl }" style="width:50px;text-align:center"></td>
		       <td>删除</td>
		     </tr>
	   		</c:forEach>
		   </tbody>
	 	</table>
	</div>
	<div class="Cprice">
	<form id="goto_checkOrder" action="goto_checkOrder.do">
		<input type="hidden" name="sum" value="${sum }">
		<div style="background: red" class="price">总价：${sum }</div>
		<div class="jiesuan" onclick="checkOrder()" style="cursor: pointer;">结算</div>
	</form>
	</div>
</body>
</html>