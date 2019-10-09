package com.huishoucat.ucenter.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.MD5Utils;
import com.huishoucat.manager.mapper.TUserMapper;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.manager.pojo.TUserExample;
import com.huishoucat.manager.pojo.TUserExample.Criteria;
import com.huishoucat.ucenter.service.IUserService;

/**
 * 用户service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月9日 上午9:58:17
 * @version V1.0
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private TUserMapper userMapper;

	@Override
	public PageBean<TUser> getUserList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TUserExample userExample = new TUserExample();
		userExample.setOrderByClause(sort + " " + order);
		Criteria criteria = userExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andTelephoneEqualTo(search);
		}
		List<TUser> list = userMapper.selectByExample(userExample);
		PageInfo<TUser> pageInfo = new PageInfo<TUser>(list);
		PageBean<TUser> pageBean = new PageBean<TUser>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateUser(TUser user) {
		if (StringUtils.isNoneBlank(user.getTelephone())) {
			TUserExample userExample = new TUserExample();
			Criteria criteria = userExample.createCriteria();
			criteria.andTelephoneEqualTo(user.getTelephone());
			int count = userMapper.countByExample(userExample);
			if (count != 0) {
				return HuishoucatResult.HuishoucatResultError("该手机号已注册！", null);
			}
		}
		if (StringUtils.isNoneBlank(user.getPassword())) {
			user.setPassword(MD5Utils.md5("h" + user.getPassword() + "s"));
		}
		user.setUpdateTime(new Date());
		int i = userMapper.updateByPrimaryKeySelective(user);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新用户信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteUsersByUserId(Long[] userIds) {
		TUser user = new TUser();
		Date date = new Date();
		for (int i = 0; i < userIds.length; i++) {
			user.setUserId(userIds[i]);
			user.setIsDeleted(true);
			user.setUpdateTime(date);
			userMapper.updateByPrimaryKeySelective(user);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult findUsersByUserId(Long userId) {
		TUser user = userMapper.selectByPrimaryKey(userId);
		if (user != null && !user.getIsDeleted()) {
			return HuishoucatResult.HuishoucatResultOK(null, user);
		}
		return HuishoucatResult.HuishoucatResultError("无效用户ID！", null);
	}
}
