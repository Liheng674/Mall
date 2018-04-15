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
	/* function subBtn(){
		$.ajax({
			type : "POST",
    		url  : "spuAdd.do",
    		data : {
    			shp_mch : $("#shp_mch").val(),
    			shp_msh : $("#shp_msh").val()
    		},
    		success: function(data){
    			
    		}
		});
	} */
	
</script>
<title>商品添加页面</title>
</head>
<body>
	${T_MALL_PRODUCT.flbh1 }<br>${T_MALL_PRODUCT.flbh2 }<br>${T_MALL_PRODUCT.pp_id }
	<hr>
	<form action="spuAdd.do?" method="post" enctype="multipart/form-data">
		<input type="hidden" name="flbh1" value="${T_MALL_PRODUCT.flbh1 }">
		<input type="hidden" name="flbh2" value="${T_MALL_PRODUCT.flbh2 }">
		<input type="hidden" name="pp_id" value="${T_MALL_PRODUCT.pp_id }">
		商品名称: <input type="text" id="shp_mch" name="shp_mch"><br>
		商品描述: <br><textarea rows="10" cols="50" id="shp_msh" name="shp_msh"></textarea><br>
		商品图片: <br>
		<div id="upload_img1" style="float:left">
			<input id="file1" style="display:none;" type="file" name="files" onchange="clickFile(1)"><br>
			<img id="img_1" onclick="clickImg(1)" src="image/upload_hover.png" width="80px" height="80px" style="cursor:pointer"><br>
			设置为封面<input name="img_cover" value="1" class="img_cover" type="radio">
		</div>
		<br>
		<input type="submit" value="提交"/>
	</form>
<script type="text/javascript">
	function clickImg(index){
		$("#file"+index).click();
	}
	
	function clickFile(index){
		var file = $("#file"+index)[0].files[0];
		var URL = window.URL || window.webkitURL;
        var imgURL = URL.createObjectURL(file);
        
        var imgObj = $("#img_"+index);
        imgObj.attr("src", imgURL);
        
        // 判断是否选择的是最后一个图片
        if(index == $(":file").length){
	        // 设定最大上传图片数
	        if($(":file").length == 5){
	        	
	        }else{
        		addImg(index);
	        }
        }
	}
	
	function addImg(index){
		var a = '<div id="upload_img'+(index+1)+'" style="float:left">';
		var b = '<input id="file'+(index+1)+'" style="display:none;" type="file" name="files" onchange="clickFile('+(index+1)+')"><br>';
		var c = '<img id="img_'+(index+1)+'" onclick="clickImg('+(index+1)+')" src="image/upload_hover.png" width="80px" height="80px" style="cursor:pointer"><br>';
		var d = '设置为封面<input name="img_cover" value="'+(index+1)+'"  class="img_cover" type="radio">';
		var e = '</div>';
		$("#upload_img"+index).after(a+b+c+d+e);
	}
</script>
</body>
</html>