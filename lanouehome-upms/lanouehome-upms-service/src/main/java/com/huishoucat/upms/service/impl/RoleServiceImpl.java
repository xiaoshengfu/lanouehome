package com.huishoucat.upms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TRoleMapper;
import com.huishoucat.manager.pojo.TRole;
import com.huishoucat.manager.pojo.TRoleExample;
import com.huishoucat.manager.pojo.TRoleExample.Criteria;
import com.huishoucat.upms.service.IRoleService;

public class RoleServiceImpl implements IRoleService {

	@Autowired
	private TRoleMapper roleMapper;

	@Override
	public PageBean<TRole> getRoleList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TRoleExample roleExample = new TRoleExample();
		roleExample.setOrderByClause(sort + " " + order);
		Criteria criteria = roleExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andNameLike("%" + search + "%");
		}
		List<TRole> list = roleMapper.selectByExample(roleExample);
		PageInfo<TRole> pageInfo = new PageInfo<TRole>(list);
		PageBean<TRole> pageBean = new PageBean<TRole>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateRole(TRole role) {
		role.setUpdateTime(new Date());
		int i = roleMapper.updateByPrimaryKeySelective(role);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新权限信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteRoleByRoleId(Long[] roleIds) {
		TRole role = new TRole();
		Date date = new Date();
		for (int i = 0; i < roleIds.length; i++) {
			role.setRoleId(roleIds[i]);
			role.setIsDeleted(true);
			role.setUpdateTime(date);
			roleMapper.updateByPrimaryKeySelective(role);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}
}
