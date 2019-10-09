package com.huishoucat.pay.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TUserTransactionDetailsMapper;
import com.huishoucat.manager.pojo.TUserTransactionDetails;
import com.huishoucat.manager.pojo.TUserTransactionDetailsExample;
import com.huishoucat.manager.pojo.TUserTransactionDetailsExample.Criteria;
import com.huishoucat.pay.service.IUserTransactionDetailsService;

/**
 * 用户余额交易明细Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午10:19:24
 * @version V1.0
 */
@Service
public class UserTransactionDetailsServiceImpl implements IUserTransactionDetailsService {

	@Autowired
	public TUserTransactionDetailsMapper userTransactionDetailsMapper;

	@Override
	public PageBean<TUserTransactionDetails> getUserTransactionDetailsList(int pageNum, int limit, String sort,
			String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TUserTransactionDetailsExample userTransactionDetailsExample = new TUserTransactionDetailsExample();
		userTransactionDetailsExample.setOrderByClause(sort + " " + order);
		Criteria criteria = userTransactionDetailsExample.createCriteria();
		if (StringUtils.isNotBlank(search)) {
			criteria.andUserIdEqualTo(Long.parseLong(search));
		}
		List<TUserTransactionDetails> list = userTransactionDetailsMapper
				.selectByExample(userTransactionDetailsExample);
		PageInfo<TUserTransactionDetails> pageInfo = new PageInfo<TUserTransactionDetails>(list);
		PageBean<TUserTransactionDetails> pageBean = new PageBean<TUserTransactionDetails>(limit, pageInfo.getTotal(),
				pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult getUserTransactionDetailsListByUserId(Long userId) {
		TUserTransactionDetailsExample userTransactionDetailsExample = new TUserTransactionDetailsExample();
		userTransactionDetailsExample.setOrderByClause("create_time desc");
		userTransactionDetailsExample.createCriteria().andUserIdEqualTo(userId);
		return HuishoucatResult.HuishoucatResultOK(null,
				userTransactionDetailsMapper.selectByExample(userTransactionDetailsExample));
	}
}
