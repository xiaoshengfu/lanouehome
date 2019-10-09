package com.huishoucat.upms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TSystemMapper;
import com.huishoucat.manager.pojo.TSystem;
import com.huishoucat.manager.pojo.TSystemExample;
import com.huishoucat.manager.pojo.TSystemExample.Criteria;
import com.huishoucat.upms.service.ISystemService;

/**
 * 系统管理Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月2日 下午12:06:23
 * @version V1.0
 */
public class SystemServiceImpl implements ISystemService {

	@Autowired
	private TSystemMapper systemMapper;

	@Override
	public PageBean<TSystem> getSystemList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TSystemExample systemExample = new TSystemExample();
		systemExample.setOrderByClause(sort + " " + order);
		Criteria criteria = systemExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andNameLike("%" + search + "%");
		}
		List<TSystem> list = systemMapper.selectByExample(systemExample);
		PageInfo<TSystem> pageInfo = new PageInfo<TSystem>(list);
		PageBean<TSystem> pageBean = new PageBean<TSystem>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateSystem(TSystem system) {
		system.setUpdateTime(new Date());
		int i = systemMapper.updateByPrimaryKeySelective(system);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新系统信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteSystemBySystemId(Long[] systemIds) {
		TSystem system = new TSystem();
		Date date = new Date();
		for (int i = 0; i < systemIds.length; i++) {
			system.setSystemId(systemIds[i]);
			system.setIsDeleted(true);
			system.setUpdateTime(date);
			systemMapper.updateByPrimaryKeySelective(system);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

}
