package com.huishoucat.basket.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.manager.pojo.TWasteOrderItem;

/**
 * 废品筐Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午7:12:04
 * @version V1.0
 */
public interface IBasketService {

	/**
	 * 向回收筐中添加废品
	 * @param openid
	 * @param wasteOrderItem
	 * @return
	 */
	public HuishoucatResult addBasket(String openid, TWasteOrderItem wasteOrderItem);

	/**
	 * 获得废品筐列表
	 * @param openid
	 * @return
	 */
	public HuishoucatResult getBasketList(String openid);

	/**
	 * 编辑废品筐内废品数量
	 * @param openid
	 * @param wasteOrderItemId
	 * @param num
	 * @return
	 */
	public HuishoucatResult editBasketNum(String openid, long wasteOrderItemId, int num);

	/**
	 * 删除废品筐中废品
	 * @param openid
	 * @param wasteOrderItemIds
	 * @return
	 */
	public HuishoucatResult deleteBasketWaste(String openid, Long[] wasteOrderItemIds);

	/**
	 * 清空废品筐废品
	 * @param openid
	 * @return
	 */
	public HuishoucatResult clearBasketWaste(String openid);

	/**
	 * 查找废品筐内的废品信息
	 * @param openid
	 * @param wasteOrderItemIds
	 * @return
	 */
	public HuishoucatResult findWasteListInBasketWaste(String openid, Long[] wasteOrderItemIds);
}
