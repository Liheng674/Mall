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
<link rel="stylesheet" type="text/css" href="css/css.css"/>	
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function add_address(yh_dz,shjr,lxfsh){
		$("#span_address").html("寄送至 ： "+yh_dz+"    &nbsp;"+shjr+"（收）  "+lxfsh);
	}

</script>
<title>结算页面</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include> 
	<div class="top_img">
		<img src="images/top_img.jpg" alt="">
	</div>
	<jsp:include page="searchArea.jsp"></jsp:include>
	
			<div class="message">
			<div class="msg_title">
				收货人信息
			</div>
			<form action="save_order.do" id="saveOrder">
				<c:forEach items="${list_addr }" var="addr">
					<div class="msg_addr">
						<span class="msg_left">
							<input type="radio" name="id" value="${addr.id }" onclick="add_address('${addr.yh_dz}','${addr.shjr }','${addr.lxfsh }')">
							${addr.shjr } 北京
						</span>
						<span class="msg_right">
							${addr.yh_dz }
						</span>
					</div>
				</c:forEach>
			</form>
			<span class="addrs">查看更多地址信息</span>
			<div class="msg_line"></div>
		
			<div class="msg_title">
				送货清单
			</div>
			<c:forEach items="${order.list_flow }" var="flow">
				<div style="margin-top: 20px">
				<c:forEach items="${flow.list_info }" var="info" varStatus="index">
					<div class="msg_list">
						<div class="msg_list_left">
							<c:if test="${index.index==0 }">
								配送方式
								<div class="left_title">
									${flow.psfsh }&nbsp;${flow.mqdd }
								</div>
							</c:if>
						</div>
						<div class="msg_list_right">
							<div class="msg_img">
								<img width="80px" src="upload/image/${info.shp_tp }"/>
							</div>
							<div class="msg_name">
								${info.sku_mch }
							</div>
							<div class="msg_price">
								￥${info.sku_jg }
							</div>
							<div class="msg_mon">
								*${info.sku_shl }
							</div>
							<div class="msg_state">
								有货
							</div>
						</div>				
					</div>
				</c:forEach>
				</div>			
			</c:forEach>
			<div class="msg_line"></div>
		
			<div class="msg_sub">
				<div class="msg_sub_tit">
					应付金额：
					<b>￥${order.zje }</b>
				</div>
				<div class="msg_sub_adds">
					<span id="span_address">
						
					</span>
				</div>
				<button id="save_button" style="cursor: pointer;" class="msg_btn">提交订单</button>
			</div>
		
		</div>
<script type="text/javascript">
	$("#save_button").click(function(){
		$("#saveOrder").submit();
	});	
</script>
</body>
</html>