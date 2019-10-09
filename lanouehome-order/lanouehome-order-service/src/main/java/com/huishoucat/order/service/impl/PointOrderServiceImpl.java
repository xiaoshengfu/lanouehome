package com.huishoucat.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TPointOrderMapper;
import com.huishoucat.manager.pojo.TPointOrder;
import com.huishoucat.manager.pojo.TPointOrderExample;
import com.huishoucat.manager.pojo.TPointOrderExample.Criteria;
import com.huishoucat.order.service.IPointOrderService;

/**
 * 积分订单Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月27日 下午6:55:59
 * @version V1.0
 */
@Service
public class PointOrderServiceImpl implements IPointOrderService {

	@Autowired
	private TPointOrderMapper pointOrderMapper;

	@Override
	public PageBean<TPointOrder> getPointOrderList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TPointOrderExample pointOrderExample = new TPointOrderExample();
		pointOrderExample.setOrderByClause(sort + " " + order);
		Criteria criteria = pointOrderExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andPointOrderIdEqualTo(Long.parseLong(search));
		}
		List<TPointOrder> list = pointOrderMapper.selectByExample(pointOrderExample);
		PageInfo<TPointOrder> pageInfo = new PageInfo<TPointOrder>(list);
		PageBean<TPointOrder> pageBean = new PageBean<TPointOrder>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updatePointOrder(TPointOrder pointOrder) {
		pointOrder.setUserId(null);
		pointOrder.setUpdateTime(new Date());
		int i = pointOrderMapper.updateByPrimaryKeySelective(pointOrder);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新积分商品订单信息失败！", null);
	}

	@Override
	public HuishoucatResult deletePointOrderByPointOrderId(Long[] pointOrderIds) {
		TPointOrder pointOrder = new TPointOrder();
		for (int i = 0; i < pointOrderIds.length; i++) {
			pointOrder.setPointOrderId(pointOrderIds[i]);
			pointOrder.setIsDeleted(true);
			pointOrder.setUpdateTime(new Date());
			pointOrderMapper.updateByPrimaryKeySelective(pointOrder);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}
}
