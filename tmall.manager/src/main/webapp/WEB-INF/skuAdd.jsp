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
<title>商品添加页面</title>
</head>
<body>
	<form action="save_sku.do?flbh1=${flbh1 }&flbh2=${flbh2}" method="post">
		品牌: <select data-options="region:'center',title:'center title'" id="sku_mall_flow" name="pp_id" onchange="getSkuMall(this.value)"></select> 
		商品: <select data-options="region:'center',title:'center title'" id="spu_list" name="shp_id"></select>
		<hr>
		分类属性: <br>
		<c:forEach items="${list_attr }" var="attr" varStatus="var">
			<input onclick="click_value(this.checked,${var.index})" type="checkbox" name="list_attr[${var.index }].shxm_id" value="${attr.id }">
			${attr.shxm_mch }
		</c:forEach>
		<c:forEach items="${list_attr }" var="attr" varStatus="var">
			<div id="d_value${var.index }" style="display: none;">
				<c:forEach items="${attr.list_value }" var="value">
					<input type="radio" name="list_attr[${var.index }].shxzh_id" value="${value.id }">
					${value.shxzh }${value.shxzh_mch }
				</c:forEach>
			</div>
		</c:forEach>
		
		<br>商品库存名称: <input type="text" name="sku_mch"><br>
		商品库存数量: <input type="text" name="kc"><br>
		商品库存价格: <input type="text" name="jg"><br>
		商品库存地址: <input type="text" name="kcdz"><br>
		<input type="submit" value="提交">
	</form>
<script type="text/javascript">
	/* $(function(){
		var flbh1 = "${flbh1}";
		var flbh2 = "${flbh2}";
		$('#sku_mall_flow').combobox({    
		    url:"js/json/tm_class_1_"+flbh1+".js",    
		    valueField:'id',
		    textField:'ppmch',
		    // 原来的onChange时间失效, 使用easyUI的combo的onChange事件
		    onChange: function (){
		    	// 获取当前选中下拉列表的值
		    	var sku_class1_id = $(this).combobox("getValue");
				$('#spu_list').combobox({    
				    url:"js/json/class_2_"+sku_class1_id+".js",    
				    valueField:'id',
				    textField:'shp_mch',
				    onChange: function(){
				    	
				    }
				});
			}
		});
	}); */
	 $(function(){
		$("#sku_mall_flow").empty();
		$.getJSON("js/json/tm_class_1_"+"${flbh1}"+".js",function(data){
			$.each(data, function(i,item){
			    $("#sku_mall_flow").append("<option value="+item.id+">"+item.ppmch);
			  });
		});
	});
	function getSkuMall(pp_id){
		$("#spu_list").empty();
		var flbh2 = "${flbh2}";
		$.post("get_spu_list.do",{flbh2:flbh2,pp_id:pp_id}, function(data){
			$.each(data, function(i,item){
				$("#spu_list").append("<option value="+item.id+">"+item.shp_mch);
			});
		});
	}
	function click_value(check,index){
		if(check){
			$("#d_value"+index).show();
		}else{
			$("#d_value"+index).hide();
		}
	}
	</script>
</body>
</html>