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
	function add_attr(index, attr_id, val_id, shxzh){
		// 数组传参用
	/* $("#attr_list_param")
				.append(
						"<span class='param_del"+index+"'><input type='hidden' name='param_' value='"+attr_id+"_"+val_id+"'><a>"
								+ shxzh
								+ "</a><a onclick='del_param("
								+ index
								+ ","+attr_id+")' style='color:red'>X</a>&nbsp</span>"); */
	// JSON传参使用, 类似\这种格式, key必须有双引号	{\"shxm_id\":"+attr_id+",\"shxzh_id\":"+val_id+"}						
	$("#attr_list_param")
	.append(
			"<span class='param_del"+index+"'><input type='hidden' name='param_' value='{\"shxm_id\":"+attr_id+",\"shxzh_id\":"+val_id+"}'><a>"
					+ shxzh
					+ "</a><a onclick='del_param("
					+ index
					+ ","+attr_id+")' style='color:red'>X</a></span>");
		// 隐藏当前行属性
		$("#param" + index).hide();

		// 异步查询
		asyn_list_sku();
		
	};
	
	
	function asyn_list_sku() {
		/*
		数组传参
		// 获取参数
		var json_array = new Array();
		//*********************************数组传参
		$("#attr_list_param input[name='param_']").each(function(i, json){
			json_array[i] = json.value;
		});
		//*********************************
		 */
		 var json_param = {};
		// 传入flbh2
		json_param["flbh2"] = "${flbh2}";
		// JSON传参
		 $("#attr_list_param input[name='param_']").each(function(i, data){
			var json = $.parseJSON(data.value);
			json_param["list_attr["+i+"].shxm_id"] = json.shxm_id;
			json_param["list_attr["+i+"].shxzh_id"] = json.shxzh_id;
		});
		// ajax异步传参
		$.post("asyn_list_sku.do", json_param, function(data) {
			// 局部刷新商品信息
			$("#sku_list").html(data);
		});
		//var str = JSON.stringify(json_param);
	}
	function del_param(index, shxm_id) {
		// 删除上面的参数, 多个属性值需要用class属性
		$(".param_del" + index).remove();
		// 显示属性行
		$("#param" + index).show();
		// 调用取消复选方法
		mul_cancel(index, shxm_id);
		
		//异步查询
		asyn_list_sku(index);
	}
	
	// 添加多选框
	function mul_param(index,shxm_id){
		$("#param"+index+":not:first").children("a[name='shxzh_name']").each(function(i,data){
			$("#param"+index+" #shxzh"+i).before("<input type='checkbox'>");
			// 原有属性值取消超链接点击时间
			$("#shxzh"+i).attr('onclick','return false;');
			// 不让其变色
			$("#shxzh"+i).attr('style','color:#666');
		});
		// 多选只能点一次
		$("#mul"+index).attr('onclick','javascript:;');
		// 显示取消按钮,提交按钮
		$("#add_del_btn"+index).show();
		//$("#param"+index+" #mul"+index).after('<a id="mul_cancel'+index+'" onclick="mul_cancel('+index+','+shxm_id+')" style="color:red;border: 1px solid #DDD;">取消✘</a>');
		//$("#param"+index+" #mul_cancel"+index).after('<a id="mul_submit'+index+'" onclick="mul_cancel('+index+','+shxm_id+')" style="color:green;border: 1px solid #DDD;">确定✔</a>');
	}
	
	// 取消复选框
	function mul_cancel(index,shxm_id){
		$("#param"+index+":not:first").children("a[name='shxzh_name']").each(function(i,data){
			//alert(("#param"+index+" :checkbox"));
			$("#param"+index+" :checkbox").remove();
			// 原有属性值添加超链接
			$("#shxzh"+i).attr('onclick','return true;');
			$("#shxzh"+i).attr('style',';');
		});
		// 多选恢复
		$("#mul"+index).attr('onclick','mul_param('+index+','+shxm_id+')');
		// 隐藏取消按钮,提交按钮
		$("#add_del_btn"+index).hide();
		//$("#param"+index+" #mul"+index).after('<a id="mul_cancel'+index+'" onclick="mul_cancel('+index+','+shxm_id+')" style="color:red;border: 1px solid #DDD;">取消✘</a>');
		//$("#param"+index+" #mul_cancel"+index).after('<a id="mul_submit'+index+'" onclick="mul_cancel('+index+','+shxm_id+')" style="color:green;border: 1px solid #DDD;">确定✔</a>');
	}
	
	// 确认复选框选中的值
	function mul_submit(index,shxm_id,list_attr){
		// 属性名id
		var shxm_id = 0;
		// 属性值id
		var shxzh_id = 0;
		// 属性值名称数组
		var shxzh = "";
		// 把名称合在一起
		var shxzh_long = "";
		$("#param"+index+" :checked").next().each(function(i, data){
			// 拿到每一个被选中的复选框, javascript:add_attr(0,13,32,'1399%C3%971024%E5%83%8F%E7%B4%A0')
			// 对应着添加需要传入的变量, 乱码用decodeURI(XX)解码
			var str = new String(data);
			// 拿到属性id, 属性值id, 属性值的拼接字符串
			str = str.substring(str.indexOf('(')+1, str.length-1);
			// 通过, 分割为数组
			var check_arr = str.split(',');
			for(var j = 0;j<check_arr.length;j++){
				shxm_id = check_arr[1];
				// 属性值id
				shxzh_id = check_arr[2];
				// 属性值
				shxzh = check_arr[3];
			}
			// 调用添加到上面的方法
			//alert(shxm_id);
			//alert(shxzh_id);
			//alert(decodeURI(shxzh));
			// 调用一个中间方法, 拼接属性值不拼接属性值id
			add_attr(index, shxm_id, shxzh_id, decodeURI(shxzh));
		});
	}
	
</script>
<title>Title</title>
</head>
<body>
	<div id="attr_list_param">
	</div>
	<hr>
	<font size="5px">属性列表: </font><br>
	<c:forEach items="${list_attr }" var="attr" varStatus="index">
		<div id="param${index.index }" style="display: block;width: 1000px;">
		<font style="width: 50px;padding: 0 26px 0 4px;">${attr.shxm_mch }：</font>
			<c:forEach items="${attr.list_value }" var="value" varStatus="shxzh_index">
			<a id="shxzh${shxzh_index.index }" name='shxzh_name' style="position: relative;" href="javascript:add_attr(${index.index},${attr.id },${value.id },'${value.shxzh }${value.shxzh_mch }')">${value.shxzh }${value.shxzh_mch }</a>
			</c:forEach>
			<a id="mul${index.index }" onclick="mul_param(${index.index},${attr.id })" style="color: #f00;font-size: 15px;">✚多选</a>
			<span id="add_del_btn${index.index }" style="display: none;">
				<a id="mul_cancel${index.index }" onclick="mul_cancel(${index.index},${attr.id })" style="color:red;border: 1px solid #DDD;">取消✘</a>
				<a id="mul_submit${index.index }" onclick="mul_submit(${index.index},${attr.id })" style="color:green;border: 1px solid #DDD;">确定✔</a>
			</span>
		</div>
	</c:forEach>
</body>
</html>