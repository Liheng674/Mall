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
	function show_cart(){
		$.post("mini_cart.do", function(data){
			$("#mini_cart_list").html(data);
		});
		$(".cart_pro").show();
	}
	function hide_cart(){
		$(".cart_pro").hide();
	}
</script>
<title>mini_cart</title>
</head>
<body>
	<div class="card" onmouseout="hide_cart()" onmouseover="show_cart()">
			<a href="go_shopping_cart.do">购物车<div class="num">0</div></a>
			
			<!--购物车商品-->
			<div id="mini_cart_list" class="cart_pro" style="display: none;">
			<jsp:include page="mini_cart_list.jsp"></jsp:include>
			</div>
		
		</div>
</body>
</html>