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
<link rel="stylesheet" type="text/css" href="css/css.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		var key = "yh_nch";
		var val = "";
		var cookies = document.cookie;
		// cookies由分号加空格分隔
		//替换空格
		cookies = cookies.replace(/\s+/g, "");
		// 截取
		var cookie_arr = cookies.split(";");
		for(var i=0;i<cookie_arr.length;i++){
			var cookie_val = cookie_arr[i];
			// 分隔key和value
			var key_val = cookie_val.split("=");
			if(key_val[0] == key){
				val = key_val[1];
			}
		}
		// 解码
		//val = decodeURIComponent(val);
		val = decodeURI(val);
		
		$("#yh_nch").append(val);
	});
</script>
<title>Title</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<c:if test="${empty user }">
				<a href="go_login.do">用户登录: <span id="yh_nch" style="color: red"></span></a>
				<a href="">用户注册</a>
			</c:if>
			<c:if test="${not empty user }">
				<a href="">用户名称: ${user.yh_mch }</a>
				<a href="">用户订单</a>
			</c:if>
		</div>
	</div>
</body>
</html>