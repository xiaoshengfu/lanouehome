package com.huishoucat.pay.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TUserPointsMapper;
import com.huishoucat.manager.pojo.TUserPoints;
import com.huishoucat.manager.pojo.TUserPointsExample;
import com.huishoucat.manager.pojo.TUserPointsExample.Criteria;
import com.huishoucat.pay.service.IUserPointsService;

/**
 * 用户绿色积分Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午11:41:48
 * @version V1.0
 */
@Service
public class UserPointsServiceImpl implements IUserPointsService {

	@Autowired
	private TUserPointsMapper userPointsMapper;

	@Override
	public PageBean<TUserPoints> getUserPointsList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TUserPointsExample userPointsExample = new TUserPointsExample();
		userPointsExample.setOrderByClause(sort + " " + order);
		Criteria criteria = userPointsExample.createCriteria();
		if (StringUtils.isNotBlank(search)) {
			criteria.andUserIdEqualTo(Long.parseLong(search));
		}
		List<TUserPoints> list = userPointsMapper.selectByExample(userPointsExample);
		PageInfo<TUserPoints> pageInfo = new PageInfo<TUserPoints>(list);
		PageBean<TUserPoints> pageBean = new PageBean<TUserPoints>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateUserPoints(Long userId, Long income, Long outlay) {
		TUserPoints userPoints = userPointsMapper.selectByPrimaryKey(userId);
		if (userPoints != null) {
			TUserPoints userPoints2 = new TUserPoints();
			userPoints2.setUserId(userId);
			userPoints2.setTotalIncome(userPoints.getTotalIncome() + income);
			userPoints2.setTotalOutlay(userPoints.getTotalOutlay() + outlay);
			userPoints2.setUpdateTime(new Date());
			int i = userPointsMapper.updateByPrimaryKeySelective(userPoints2);
			if (i == 1) {
				return HuishoucatResult.HuishoucatResultOK(null, userPoints2);
			}
		}
		return HuishoucatResult.HuishoucatResultError("无效用户ID！", null);
	}

	@Override
	public HuishoucatResult findUserPointsByUserId(Long userId) {
		return HuishoucatResult.HuishoucatResultOK(null, userPointsMapper.selectByPrimaryKey(userId));
	}
}
