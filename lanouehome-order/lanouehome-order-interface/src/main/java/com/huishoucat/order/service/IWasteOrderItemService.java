package com.huishoucat.order.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.manager.pojo.TWasteOrderItem;

/**
 * 废品订单内容Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月19日 下午9:27:37
 * @version V1.0
 */
public interface IWasteOrderItemService {

	/**
	 * 通过废品订单ID查找订单内容
	 * @param wasteOrderId
	 * @return
	 */
	public HuishoucatResult findWasteOrderItemListByWasteOrderId(Long wasteOrderId);
	
	/**
	 * 添加废品订单内容
	 * @param wasteOrderItem
	 * @return
	 */
	public HuishoucatResult addWasteOrderItem(TWasteOrderItem wasteOrderItem);
}
