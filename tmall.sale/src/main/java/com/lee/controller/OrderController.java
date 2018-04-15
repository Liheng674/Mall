package com.lee.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lee.bean.OBJECT_T_MALL_FLOW;
import com.lee.bean.OBJECT_T_MALL_ORDER;
import com.lee.bean.T_MALL_ADDRESS;
import com.lee.bean.T_MALL_ORDER_INFO;
import com.lee.bean.T_MALL_SHOPPINGCAR;
import com.lee.bean.T_MALL_USER_ACCOUNT;
import com.lee.exception.OverSaleException;
import com.lee.server.AddressServer;
import com.lee.service.CartService;
import com.lee.service.OrderService;
import com.lee.util.MyJsonUtil;

@Controller
@SessionAttributes("order")
public class OrderController {

	@Autowired
	private AddressServer addressServer;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/save_order")
	public String save_order(@ModelAttribute("order") OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS address, HttpSession session) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		// 获取地址信息
		String json_address = addressServer.select_address_byId(address.getId());
		T_MALL_ADDRESS get_address = MyJsonUtil.json_to_object(json_address, T_MALL_ADDRESS.class);
		
		// 调用保存订单的业务
		orderService.save_order(order, get_address);
		
		// 同步session
		session.setAttribute("list_cart_session", cartService.get_list_cart_byUser(user));
		
		// 重定向到支付服务, 传入订单号和总金额
		return "realPay";
	}
	
	@RequestMapping("/goto_pay")
	public String goto_pay() {
		// 伪支付服务
		return "pay";
	}
	
	@RequestMapping("/pay_success")
	public String pay_success(@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {
		// 支付成功
		try {
			orderService.pay_success(order);
		} catch (OverSaleException e) {
			e.printStackTrace();
			return "redirect:/order_err.do";
		}
		return "redirect:/order_success.do";
	}
	
	@RequestMapping("/real_pay_success")
	@ResponseBody
	public String real_pay_success(@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {
		// 支付成功
		try {
			orderService.pay_success(order);
		} catch (OverSaleException e) {
			e.printStackTrace();
			return "success";
		}
		return "success";
	}
	
	@RequestMapping("/order_success")
	public String order_success() {
		return "orderSuccess";
	}
	
	@RequestMapping("/order_err")
	public String order_err() {
		return "orderErr";
	}
	
	/**
	* @Title: goto_checkOrder
	* @Description: TODO(去结算页面)
	* 		重定向到登录页面, 页面放置隐藏域参数, 在登录方法入参得到参数, 判断去主页还是结算页面
	* @param @param session
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/goto_checkOrder")
	public String goto_checkOrder(HttpSession session, Model model) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		if (user == null) {
			return "redirect:/go_loginCheck.do";
		}else {
			// session购物车列表
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
			
			Set<String> set_kcdz = new HashSet<>();
			// 根据购物车的选中状态获得选中商品所有的库存地址信息
			for (int i = 0; i < list_cart.size(); i++) {
				if ("1".equals(list_cart.get(i).getShfxz())) {
					set_kcdz.add(list_cart.get(i).getKcdz());
				}
			}
			
			// 总订单对象, List<OBJECT_T_MALL_FLOW>
			OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
			// 提交订单后才有地址ID和地址
			order.setYh_id(user.getId());
			order.setJdh(1);
			order.setZje(getSum(list_cart));
			
			// 根据地址封装送货清单
			List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<>();
			for (String kcdz : set_kcdz) {
				// 每一个库存地址都是一个OBJECT_T_MALL_FLOW清单, 包含List<T_MALL_ORDER_INFO>
				OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
				flow.setYh_id(user.getId());
				flow.setMqdd("商品未出库");
				flow.setPsfsh("顺丰快递");
				
				List<T_MALL_ORDER_INFO> list_info = new ArrayList<>();
				for (int i = 0; i < list_cart.size(); i++) {
					T_MALL_SHOPPINGCAR cart = list_cart.get(i);
					if ("1".equals(list_cart.get(i).getShfxz()) && kcdz.equals(list_cart.get(i).getKcdz())) {
						// 订单里面的每一个商品信息
						T_MALL_ORDER_INFO info = new T_MALL_ORDER_INFO();
						// 购物车id
						info.setGwch_id(cart.getId());
						info.setShp_tp(cart.getShp_tp());
						info.setSku_id(cart.getSku_id());
						info.setSku_jg(cart.getSku_jg());
						info.setSku_kcdz(kcdz);
						info.setSku_mch(cart.getSku_mch());
						info.setSku_shl(cart.getTjshl());
						// 相同地址的商品放入一个flow订单
						list_info.add(info);
					}
				}
				// 送货清单放入总订单
				flow.setList_info(list_info);
				list_flow.add(flow);
			}
			// 送货清单放入主订单
			try {
				String address_byUser = addressServer.select_address_byUser(user);
				List<T_MALL_ADDRESS> list_addr = MyJsonUtil.json_to_list(address_byUser, T_MALL_ADDRESS.class);
				model.addAttribute("list_addr", list_addr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			order.setList_flow(list_flow); // 内存中的游离对象
			model.addAttribute("order", order);
			return "checkOrder";
		}
	}
	
	private BigDecimal getSum(List<T_MALL_SHOPPINGCAR> list_cart) {
		// 计算总金额
		BigDecimal sum = new BigDecimal("0");
		if (list_cart != null && list_cart.size() > 0) {
			for (int i = 0; i < list_cart.size(); i++) {
				if ("1".equals(list_cart.get(i).getShfxz())) {
					sum = sum.add(new BigDecimal(list_cart.get(i).getHj() + ""));
				}
			}
		}
		return sum;
	}
}
