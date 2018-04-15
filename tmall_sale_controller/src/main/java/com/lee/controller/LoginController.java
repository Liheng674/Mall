package com.lee.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lee.bean.T_MALL_SHOPPINGCAR;
import com.lee.bean.T_MALL_USER_ACCOUNT;
import com.lee.server.LoginServer;
import com.lee.server.TestServer;
import com.lee.service.CartService;
import com.lee.service.LoginMessage;
import com.lee.util.MyJsonUtil;

@Controller
public class LoginController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private LoginServer LoginServer;
	
	@Autowired
	private TestServer testServer;
	
	@Autowired
	private LoginMessage loginMessage;

	/**
	* @Title: Login
	* @Description: 增删改重定向, 查询转发
	* 				登录也需要重定向
	* @param @param loginUser
	* @param @param session
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/login")
	public String Login(@RequestParam(value="redirect", required=false) String redirect, @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			T_MALL_USER_ACCOUNT loginUser, HttpSession session, HttpServletResponse response, String dataSource_type) {
		// T_MALL_USER_ACCOUNT user = loginMapper.select_user(loginUser);

		// 远程调用登录接口
//		String url = MyPropertiesUtil.loadProperties("ws.properties" ,"login_url");
//		LoginServer myWs = MyFactoryBean.getMyWs(url, LoginServer.class);
		String loginJson = "";
		if ("1".equals(dataSource_type)) {
			loginJson = LoginServer.login(loginUser);
			testServer.hello("hello");
		}
		if ("2".equals(dataSource_type)) {
			loginJson = LoginServer.login2(loginUser);
		}
		T_MALL_USER_ACCOUNT user = MyJsonUtil.json_to_object(loginJson, T_MALL_USER_ACCOUNT.class);
		
		if(user == null) {
			return "redirect:/login.do";
		}else {
			// 发送写日志
			try {
				loginMessage.sendMessage(loginJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("user", user);
			
			// 在客户端存储用户个性化信息，方便用户下次再访问网站时使用
			try {
				Cookie cookie = new Cookie("yh_mch", user.getYh_mch());
				cookie.setMaxAge(60 * 60 * 24);
				Cookie cookie2 = new Cookie("yh_nch", URLEncoder.encode("周润发", "utf-8"));
				cookie.setMaxAge(60 * 60 * 24);
				cookie2.setMaxAge(60 * 60 * 24);
				cookie.setPath("/");
				cookie2.setPath("/");
				// 如果类上面有RequestMapping, 则必须设置path
				response.addCookie(cookie);
				response.addCookie(cookie2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 同步购物车
		combine_cart(list_cart_cookie, session, response, user);
		
		if (StringUtils.isBlank(redirect)) {
			return "redirect:/index.do";
		}else {
			return redirect;
		}
	}

	private void combine_cart(String list_cart_cookie, HttpSession session, HttpServletResponse response,
			T_MALL_USER_ACCOUNT user) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		if(!StringUtils.isBlank(list_cart_cookie)) {
			// Cookie不为空
			// 获取Cookie中的商品信息
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			// 获取当前用户db列表集合
			List<T_MALL_SHOPPINGCAR> list_cart_db = cartService.get_list_cart_byUser(user);
			for (int i = 0; i < list_cart.size(); i++) {
				// 设置用户id
				list_cart.get(i).setYh_id(user.getId());
				// 判断Cookie中的商品信息是否存在db
				boolean b = cartService.if_exist_cart(list_cart.get(i));
				if (b) {
					// 老车, 更新
					for (int j = 0; j < list_cart_db.size(); j++) {
						if (list_cart.get(i).getSku_id() == list_cart_db.get(j).getSku_id()) {
							// 数量
							list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + list_cart_db.get(j).getTjshl());
							// 合计
							list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
							// 更新
							cartService.updata_cart(list_cart.get(i));
						}
					}
				}else {
					// 新车, 添加
					cartService.insert_cart(list_cart.get(i));
				}
			}
		}
		// 同步session, 清空Cookie
		session.setAttribute("list_cart_session", cartService.get_list_cart_byUser(user));
		response.addCookie(new Cookie("list_cart_cookie", ""));
	}
/*	private void combine_cart(String list_cart_cookie, HttpSession session, HttpServletResponse response,
			T_MALL_USER user) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<>();
		if(!StringUtils.isBlank(list_cart_cookie)) {
			// Cookie不为空
			// 获取Cookie中的商品信息
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
			// 获取当前用户db列表集合
			List<T_MALL_SHOPPINGCAR> list_cart_db = cartService.get_list_cart_byUser(user);
			// 判断db列表是否为空
			if (list_cart_db == null || list_cart_db.size() == 0) {
				// 直接插入
				for (int i = 0; i < list_cart.size(); i++) {
					// 设置Cookie中商品数据的主键
					list_cart.get(i).setYh_id(user.getId());
					// 插入数据
					cartService.insert_cart(list_cart.get(i));
				}
			}else {
				// 判断Cookie里面的商品信息与db里面是否重复
				for (int i = 0; i < list_cart.size(); i++) {
					boolean b = if_new_cart(list_cart_db, list_cart.get(i));
					if (b) {
						// 新车
						// 插入数据
						list_cart.get(i).setYh_id(user.getId());
						cartService.insert_cart(list_cart.get(i));
					} else {
						// 老车
						for (int j = 0; j < list_cart_db.size(); j++) {
							if (list_cart.get(i).getSku_id() == list_cart_db.get(j).getSku_id()) {
								// 数量
								list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + list_cart_db.get(j).getTjshl());
								// 合计
								list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
								// 更新
								cartService.updata_cart(list_cart.get(i));
							}
						}
					}
				}
			}
			// 同步session, 清空Cookie
			session.setAttribute("list_cart_session", cartService.get_list_cart_byUser(user));
			response.addCookie(new Cookie("list_cart_cookie", ""));
		}
	}
*/	
	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart) {
		for (int i = 0; i < list_cart.size(); i++) {
			if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
				return false;
			}
		}
		return true;
	}
}
