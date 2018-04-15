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
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function add_cart(){
		$("#cart_form").submit();
	}
</script>
<title>Title</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<a href="">用户登录</a>
			<a href="">用户注册</a>
			<a href="">供应商登录</a>
			<a href="">供应商注册</a>
		</div>
	</div>
	<!--<div class="top_img">
		<img src="./images/top_img.jpg" alt="">
	</div>-->
	<div class="search searchBac">
		<div class="logo"><img src="./images/logo.png" alt=""></div>
		<!--<div class="search_on">
			<div class="se">
				<input type="text" name="search" class="lf">
				<input type="submit" class="clik" value="搜索">
			</div>
			<div class="se">
				<a href="">取暖神奇</a>
				<a href="">1元秒杀</a>
				<a href="">吹风机</a>
				<a href="">玉兰油</a>
			</div>
		</div>-->
		<div class="card">
			<a href="">购物车<div class="num">0</div></a>
			
			<!--购物车商品-->
			<div class="cart_pro">
				<h6>最新加入的商品</h6>
				<div class="one">
					<img src="images/lec1.png"/>
					<span class="one_name">
						商品名称商品名称商品名称商品名称
					</span>
					<span class="one_prece">
						<b>￥20000</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
				</div>
				
				<div class="one border">
					<img src="images/lec1.png"/>
					<span class="one_name">
						商品名称商品名称商品名称商品名称
					</span>
					<span class="one_prece">
						<b>￥20000</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
				</div>
				<div class="one border">
					<img src="images/lec1.png"/>
					<span class="one_name">
						商品名称商品名称商品名称商品名称
					</span>
					<span class="one_prece">
						<b>￥20000</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
				</div>
				
				
				<div class="gobottom">
					共<span>2</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
					共计￥<span>20000</span>
					<button class="goprice">去购物车</button>
				</div>
				
				
			</div>
			
		</div>
	</div>
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini" style="display:none">
						<ul>
							<li>
								<a href="">家用电器</a>
								<div class="two_nav">
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
									<a href="">11111</a>
								</div>
							</li>
							<li><a href="">手机、数码、通信</a></li>
							<li><a href="">电脑、办公</a></li>
							<li><a href="">家具、家居、家装</a></li>
							<li><a href="">男装、女装、内衣</a></li>
							<li><a href="">个户化妆</a></li>
							<li><a href="">鞋靴</a></li>
							<li><a href="">户外运动</a></li>
							<li><a href="">汽车</a></li>
							<li><a href="">母婴</a></li>
							<li><a href="">食品</a></li>
							<li><a href="">营养保健</a></li>
							<li><a href="">图书</a></li>
							<li><a href="">彩票</a></li>
							<li><a href="">理财</a></li>
						</ul>
					</div>
				</div>
				<ul>
					<li><a href="">服装城</a></li>
					<li><a href="">美妆馆</a></li>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="Dbox">
		<div class="box">
			<div class="left">
				<div class="timg"><img width="300px" height="250px" src="upload/image/${detail_sku.spu.shp_tp }" alt=""></div>
				<div class="timg2">
					<div class="lf"><img src="images/lf.jpg" alt=""></div>
					<div class="center">
						<c:forEach items="${detail_sku.list_image }" var="img">
							<span><img width="50px" height="40px" src="upload/image/${img.url }" alt=""></span>
						</c:forEach>
						
					</div>
					<div class="rg"><img src="images/rg.jpg" alt=""></div>
				</div>
				<div class="goods"><img src="images/img_6.jpg" alt=""></div>
			</div>
			<div class="cent">
				<div class="title">${detail_sku.sku_mch }</div>
				<div class="bg">
					<p>市场价：<strong>￥${detail_sku.jg }</strong></p>
					<p>促销价：<strong>￥${detail_sku.jg }</strong></p>
				</div>
				<div class="clear">
					<div class="min_t">选择版本：</div>
							<c:forEach items="${list_sku }" var="sku">
								<div class="min_mx" onclick="">
									<a href="goto_sku_detail.do?sku_id=${sku.id }&shp_id=${sku.shp_id }">${sku.sku_mch }</a>
								</div>
							</c:forEach>
				</div>
				<div class="clear">
					<div class="min_t" onclick="func($(this),'1')">服务：</div>
					<div class="min_mx" onclick="func($(this),'1')">服务1号1</div>
					<div class="min_mx" onclick="func($(this),'1')">服务二号1112</div>
					<div class="min_mx" onclick="func($(this),'1')">55英服务二号1111寸活动中3</div>
					<div class="min_mx" onclick="func($(this),'1')">4</div>
					<div class="min_mx" onclick="func($(this),'1')">呃呃呃5</div>
					<div class="min_mx" onclick="func($(this),'1')">55英寸活动中6</div>
				</div>
				<div class="clear" style="margin-top:20px;">
					<div class="min_t" style="line-height:36px">数量：</div>
					<div class="num_box">
						<input type="text" name="num" value="1" style="width:40px;height:32px;text-align:center;float:left">
						<div class="rg">
							<img src="images/jia.jpg" id='jia' alt="">
							<img src="images/jian.jpg" id='jian' alt="">
						</div>
					</div>
				</div>
				<div class="clear" style="margin-top:20px;">
					<form  id="cart_form" action="add_cart.do" method="post">
							<input type="hidden" name="sku_mch" value="${detail_sku.sku_mch}" />
							<input type="hidden" name="sku_jg" value="${detail_sku.jg}" />
							<input type="hidden" name="tjshl" value="1" />
							<input type="hidden" name="hj" value="${detail_sku.jg}" />
							<input type="hidden" name="shp_id" value="${detail_sku.shp_id}" />
							<input type="hidden" name="sku_id" value="${detail_sku.id}" />
							<input type="hidden" name="shp_tp" value="${detail_sku.spu.shp_tp}" />
							<input type="hidden" name="shfxz" value="1" />
							<input type="hidden" name="kcdz" value="${detail_sku.kcdz}" />
							<c:if test="${not empty user}">
								<input type="hidden" name="yh_id" value="${user.id}" />
							</c:if>
						</form>		
							<img src="images/mai.jpg" alt="">
							<img onclick="add_cart()" style="cursor: pointer;" src="images/shop.jpg" alt="">
				</div>
			</div>
		</div>
	</div>
	<div class="Dbox1">
		<div class="boxbottom">
			<div class="top">
				<span>商品详情</span>
				<span>评价</span>
			</div>
			<div class="btm">
				<c:forEach items="${detail_sku.list_av_name }" var="av">
					<div>${av.shxm_mch }：${av.shxzh_mch }</div>
				</c:forEach>
				<c:forEach items="${detail_sku.list_image }" var="img">
					<span><img src="upload/image/${img.url }" alt=""></span>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="top"></div>
		<div class="bottom"><img src="images/foot.jpg" alt=""></div>
	</div>
	
</body>
</html>