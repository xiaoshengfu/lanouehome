package com.huishoucat.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.manager.mapper.TWasteOrderItemMapper;
import com.huishoucat.manager.pojo.TWasteOrderItem;
import com.huishoucat.manager.pojo.TWasteOrderItemExample;
import com.huishoucat.order.service.IWasteOrderItemService;

/**
 * 废品订单内容Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月19日 下午9:33:10
 * @version V1.0
 */
@Service
public class WasteOrderItemServiceImpl implements IWasteOrderItemService {

	@Autowired
	private TWasteOrderItemMapper wasteOrderItemMapper;

	@Override
	public HuishoucatResult findWasteOrderItemListByWasteOrderId(Long wasteOrderId) {
		TWasteOrderItemExample wasteOrderItemExample = new TWasteOrderItemExample();
		wasteOrderItemExample.createCriteria().andWasteOrderIdEqualTo(wasteOrderId);
		List<TWasteOrderItem> list = wasteOrderItemMapper.selectByExample(wasteOrderItemExample);
		if (list.size() != 0) {
			return HuishoucatResult.HuishoucatResultOK(null, list);
		}
		return HuishoucatResult.HuishoucatResultError("无效废品订单ID!", null);
	}

	@Override
	public HuishoucatResult addWasteOrderItem(TWasteOrderItem wasteOrderItem) {
		int i = wasteOrderItemMapper.insertSelective(wasteOrderItem);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, wasteOrderItem);
		}
		return HuishoucatResult.HuishoucatResultError("添加废品订单内容失败！", null);
	}
}
