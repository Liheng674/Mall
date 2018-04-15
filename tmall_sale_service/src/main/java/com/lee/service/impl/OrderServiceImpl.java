package com.lee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.OBJECT_T_MALL_FLOW;
import com.lee.bean.OBJECT_T_MALL_ORDER;
import com.lee.bean.T_MALL_ADDRESS;
import com.lee.bean.T_MALL_ORDER_INFO;
import com.lee.exception.OverSaleException;
import com.lee.mapper.OrderMapper;
import com.lee.service.OrderService;
import com.lee.util.MyDateUtil;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS get_address) {
		List<Integer> list_id = new ArrayList<>();
		// 保存总订单
		Map<String, Object> mapOrder = new HashMap<>();
		mapOrder.put("order", order);
		mapOrder.put("address", get_address);
		orderMapper.insert_order(mapOrder);
		// 保存物流
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for (int i = 0; i < list_flow.size(); i++) {
			OBJECT_T_MALL_FLOW flow = list_flow.get(i);
			flow.setMdd(get_address.getYh_dz());
			Map<String, Object> mapFlow = new HashMap<>();
			mapFlow.put("flow", flow);
			mapFlow.put("dd_id", order.getId());
			orderMapper.insert_flow(mapFlow);
			// 保存订单信息
			List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
			Map<String, Object> mapInfo = new HashMap<>();
			mapInfo.put("list_info", list_info);
			mapInfo.put("dd_id", order.getId());
			mapInfo.put("flow_id", flow.getId());
			orderMapper.insert_infos(mapInfo);
			
			// 获取所有订单的购物车id
			for (int j = 0; j < list_info.size(); j++) {
				list_id.add(list_info.get(j).getGwch_id());
			}
		}
		// 删除已经生成订单的购物车对象
		Map<String, Object> mapCart = new HashMap<>();
		mapCart.put("list_id", list_id);
		orderMapper.delete_cart(mapCart);
	}

	@Override
	public void pay_success(OBJECT_T_MALL_ORDER order) throws OverSaleException {
		// 修改订单状态, 已支付
		order.setJdh(2);
		orderMapper.update_order(order);
		
		// 修改物流信息
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for (int i = 0; i < list_flow.size(); i++) {
			// 修改物流
			OBJECT_T_MALL_FLOW flow = list_flow.get(i);
			flow.setPsmsh("商品正在出库");
			flow.setPsshj(MyDateUtil.getMyDate(1));
			flow.setYwy("佟老板");
			flow.setLxfsh("16688888888");
			orderMapper.update_flow(flow);
			
			List<T_MALL_ORDER_INFO> list_info = list_flow.get(i).getList_info();
			// 修改sku数量和销量等信息
			for (int j = 0; j < list_info.size(); j++) {
				T_MALL_ORDER_INFO info = list_info.get(j);
				// 查询库存的业务
				long kc = 0;
				// 判断库存警戒线
				long count = orderMapper.select_count_kc(info);
				if (count == 0) {
					// 执行带锁sql
					kc = orderMapper.select_kc_lock(info);
				} else {
					// 执行不带锁sql
					kc = orderMapper.select_kc(info);
				}
				if (kc >= info.getSku_shl()) {
					// 修改库存
					orderMapper.update_kc(info);
				}else {
					throw new OverSaleException("over sale");
				}
			}
		}
		// 修改订单状态
		order.setYjsdshj(MyDateUtil.getMyDate(3));
		orderMapper.update_order(order);
	}
	
}
