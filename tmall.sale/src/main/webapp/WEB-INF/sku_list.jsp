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
<title>Title</title>
</head>
<body>

	<c:forEach items="${list_sku }" var="sku">
		<div style="margin-top:10px;margin-left:10px;float:left;width:200px;height:300px; left:0;top:0;background:#fff;border:1px solid #0f0;">
			<img width="200px" height="200px" data-img="1" src="upload/image/${sku.spu.shp_tp}">
			<div style="color:#e4393c;font-weight:400;font-family:Verdana">
			    <strong data-price-plus="1"><em>¥</em><i>${sku.jg}</i></strong>
	  		</div>
	  		<div>
	  			<a target="_blank" href="goto_sku_detail.do?sku_id=${sku.id }&shp_id=${sku.shp_id}">${sku.sku_mch}</a>
	  		</div>
		</div>
	</c:forEach>
</body>
</html>