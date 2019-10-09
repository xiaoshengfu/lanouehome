package com.huishoucat.pay.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TUserPointsDetailsMapper;
import com.huishoucat.manager.pojo.TUserPointsDetails;
import com.huishoucat.manager.pojo.TUserPointsDetailsExample;
import com.huishoucat.manager.pojo.TUserPointsDetailsExample.Criteria;
import com.huishoucat.pay.service.IUserPointsDetailsService;

/**
 * 用户绿色积分明细Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月24日 上午12:00:30
 * @version V1.0
 */
@Service
public class UserPointsDetailsServiceImpl implements IUserPointsDetailsService {

	@Autowired
	private TUserPointsDetailsMapper userPointsDetailsMapper;

	@Override
	public PageBean<TUserPointsDetails> getUserPointsDetailsList(int pageNum, int limit, String sort, String order,
			String search) {
		PageHelper.startPage(pageNum, limit);
		TUserPointsDetailsExample userPointsDetailsExample = new TUserPointsDetailsExample();
		userPointsDetailsExample.setOrderByClause(sort + " " + order);
		Criteria criteria = userPointsDetailsExample.createCriteria();
		if (StringUtils.isNotBlank(search)) {
			criteria.andUserIdEqualTo(Long.parseLong(search));
		}
		List<TUserPointsDetails> list = userPointsDetailsMapper.selectByExample(userPointsDetailsExample);
		PageInfo<TUserPointsDetails> pageInfo = new PageInfo<TUserPointsDetails>(list);
		PageBean<TUserPointsDetails> pageBean = new PageBean<TUserPointsDetails>(limit, pageInfo.getTotal(), pageNum,
				list);
		return pageBean;
	}

	@Override
	public HuishoucatResult getUserPointsDetailsListByUserId(Long userId) {
		TUserPointsDetailsExample userPointsDetailsExample = new TUserPointsDetailsExample();
		userPointsDetailsExample.setOrderByClause("create_time desc");
		userPointsDetailsExample.createCriteria().andUserIdEqualTo(userId);
		return HuishoucatResult.HuishoucatResultOK(null,
				userPointsDetailsMapper.selectByExample(userPointsDetailsExample));
	}
}
