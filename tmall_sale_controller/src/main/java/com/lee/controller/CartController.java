package com.lee.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lee.bean.T_MALL_SHOPPINGCAR;
import com.lee.bean.T_MALL_USER_ACCOUNT;
import com.lee.service.CartService;
import com.lee.util.MyJsonUtil;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/if_checked")
	public String if_checked(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			HttpSession session, T_MALL_SHOPPINGCAR cart, HttpServletResponse response, Model model) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		// 判断是否登录
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		if (user == null) {
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			// 修改Cookie
			for (int i = 0; i < list_cart.size(); i++) {
				list_cart.get(i).setShfxz(cart.getShfxz());
			}
			// 更新Cookie
			Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		}else {
			// 修改db
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
			for (int i = 0; i < list_cart.size(); i++) {
				if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
					list_cart.get(i).setShfxz(cart.getShfxz());
					cartService.updata_cart(list_cart.get(i));
				}
			}
			// 同步session
			session.setAttribute("list_cart_session", list_cart);
		}
		
		BigDecimal sum = getSum(list_cart);

		model.addAttribute("sum", sum);
		model.addAttribute("list_cart", list_cart);
		return "cart_inner";
	}

	/**
	 * @Title: go_shopping_cart @Description: TODO(去购物车, 同迷你购物车) @param @param
	 * list_cart_cookie @param @param session @param @param model @param @return
	 * 参数 @return String 返回类型 @throws
	 */
	@RequestMapping("/go_shopping_cart")
	public String go_shopping_cart(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			HttpSession session, Model model) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		if (user == null) {
			// Cookie
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
		} else {
			// session
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}

		BigDecimal sum = getSum(list_cart);

		model.addAttribute("sum", sum);
		model.addAttribute("list_cart", list_cart);
		return "shopping_cart";
	}

	/**
	 * @Title: mini_cart @Description: TODO(迷你购物车列表) @param @param
	 * list_cart_cookie @param @param session @param @param model @param @return
	 * 参数 @return String 返回类型 @throws
	 */
	@RequestMapping("/mini_cart")
	public String mini_cart(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			HttpSession session, Model model) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		if (user == null) {
			// Cookie
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
		} else {
			// session
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}

		BigDecimal sum = getSum(list_cart);
		
		model.addAttribute("list_cart", list_cart);
		model.addAttribute("sum", sum);
		return "mini_cart_list";
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

	/**
	 * @Title: add_cart @Description: TODO(添加到购物车) @param @return 参数 @return String
	 * 返回类型 @throws
	 */
	@RequestMapping("/add_cart")
	public String add_cart(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			HttpServletResponse response, T_MALL_SHOPPINGCAR cart, HttpSession session) {
		// 判断用户是否登录
		int yh_id = cart.getYh_id();
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		if (yh_id == 0) {
			// 未登录
			// 判断Cookie是否为空
			if (StringUtils.isBlank(list_cart_cookie)) {
				// Cookie为空, 把商品信息添加到Cookie
				list_cart.add(cart);
			} else {
				// 获取Cookie中的商品信息
				list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
				// 判断添加的商品是否存在
				boolean b = if_new_cart(list_cart, cart);
				if (b) {
					// 不存在, 新车
					list_cart.add(cart);
				} else {
					// 存在, 老车
					for (int i = 0; i < list_cart.size(); i++) {
						if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
							// 设置添加数量
							list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
							// 设置价格
							list_cart.get(i).setHj(list_cart.get(i).getSku_jg() * list_cart.get(i).getTjshl());
						}
					}
				}
			}
			// 保存到Cookie
			Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		} else {
			// 用户已登录
			// 从session中获取商品信息
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
			// 判断数据库中是否有此商品信息
			boolean b = cartService.if_exist_cart(cart);
			if (b) {
				for (int i = 0; i < list_cart.size(); i++) {
					if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
						// 设置添加数量
						list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
						// 设置价格
						list_cart.get(i).setHj(list_cart.get(i).getSku_jg() * list_cart.get(i).getTjshl());
						// 存在, 更新
						cartService.updata_cart(list_cart.get(i));
					}
				}
			} else {
				// 不存在
				cartService.insert_cart(cart);
				// list_cert为null
				if (list_cart == null || list_cart.isEmpty()) {
					list_cart = new ArrayList<>();
				}
				list_cart.add(cart);
			}
			// 放入session中
			session.setAttribute("list_cart_session", list_cart);
		}

		return "redirect:/cart_success.do";
	}

	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart) {
		for (int i = 0; i < list_cart.size(); i++) {
			if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
				return false;
			}
		}
		return true;
	}

	@RequestMapping("/cart_success")
	public String cart_success() {
		return "cart_success";
	}
}
