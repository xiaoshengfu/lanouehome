package com.huishoucat.cms.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.cms.service.IWastePriceService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.DateUtils;
import com.huishoucat.manager.mapper.TWastePriceMapper;
import com.huishoucat.manager.pojo.TWastePrice;
import com.huishoucat.manager.pojo.TWastePriceExample;
import com.huishoucat.manager.pojo.TWastePriceExample.Criteria;

/**
 * 废品价格Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月18日 下午8:13:12
 * @version V1.0
 */
@Service
public class WastePriceServiceImpl implements IWastePriceService {

	@Autowired
	private TWastePriceMapper wastePriceMapper;

	@Override
	public PageBean<TWastePrice> getWastePriceList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TWastePriceExample wastePriceExample = new TWastePriceExample();
		wastePriceExample.setOrderByClause(sort + " " + order);
		Criteria criteria = wastePriceExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andWasteIdEqualTo(Long.parseLong(search));
		}
		List<TWastePrice> list = wastePriceMapper.selectByExample(wastePriceExample);
		PageInfo<TWastePrice> pageInfo = new PageInfo<TWastePrice>(list);
		PageBean<TWastePrice> pageBean = new PageBean<TWastePrice>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateWastePrice(TWastePrice wastePrice) {
		wastePrice.setUpdateTime(new Date());
		int i = wastePriceMapper.updateByPrimaryKeySelective(wastePrice);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新废品价格信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteWastePriceByWastePriceId(Long[] wastePriceIds) {
		TWastePrice wastePrice = new TWastePrice();
		for (int i = 0; i < wastePriceIds.length; i++) {
			wastePrice.setWastePriceId(wastePriceIds[i]);
			wastePrice.setIsDeleted(true);
			wastePrice.setUpdateTime(new Date());
			wastePriceMapper.updateByPrimaryKeySelective(wastePrice);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult addWastePrice(TWastePrice wastePrice) {
		Date nowDay;
		try {
			nowDay = DateUtils.getNowDay();
		} catch (ParseException e) {
			e.printStackTrace();
			return HuishoucatResult.HuishoucatResultError("日期转换错误！", null);
		}
		TWastePriceExample wastePriceExample = new TWastePriceExample();
		Criteria criteria = wastePriceExample.createCriteria();
		criteria.andIsDeletedEqualTo(false).andWasteIdEqualTo(wastePrice.getWasteId())
				.andCreateTimeGreaterThanOrEqualTo(nowDay);
		int count = wastePriceMapper.countByExample(wastePriceExample);
		if (count == 0) {
			Date date = new Date();
			wastePrice.setCreateTime(date);
			wastePrice.setUpdateTime(date);
			int i = wastePriceMapper.insertSelective(wastePrice);
			if (i == 1) {
				return HuishoucatResult.HuishoucatResultOK(null, wastePrice);
			}
		} else {
			return HuishoucatResult.HuishoucatResultError("今日已有该废品价格！", null);
		}
		return HuishoucatResult.HuishoucatResultError("添加废品价格失败！", null);
	}

	@Override
	public HuishoucatResult getNewestWastePriceByWasteId(Long wasteId) {
		PageHelper.startPage(1, 1);
		TWastePriceExample wastePriceExample = new TWastePriceExample();
		wastePriceExample.setOrderByClause("create_time desc");
		Criteria criteria = wastePriceExample.createCriteria();
		criteria.andIsDeletedEqualTo(false).andWasteIdEqualTo(wasteId);
		List<TWastePrice> list = wastePriceMapper.selectByExample(wastePriceExample);
		new PageInfo<TWastePrice>(list);
		if (list.size() != 0) {
			return HuishoucatResult.HuishoucatResultOK(null, list.get(0));
		}
		return HuishoucatResult.HuishoucatResultError("无效废品ID！", null);
	}

}
