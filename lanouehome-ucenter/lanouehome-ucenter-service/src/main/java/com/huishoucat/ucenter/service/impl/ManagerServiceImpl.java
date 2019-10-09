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
import com.huishoucat.manager.mapper.TManagerMapper;
import com.huishoucat.manager.pojo.TManager;
import com.huishoucat.manager.pojo.TManagerExample;
import com.huishoucat.manager.pojo.TManagerExample.Criteria;
import com.huishoucat.ucenter.service.IManagerService;

@Service
public class ManagerServiceImpl implements IManagerService {

	@Autowired
	private TManagerMapper managerMapper;

	@Override
	public HuishoucatResult managerLogin(Long jobNumber, String password) {
		TManagerExample managerExample = new TManagerExample();
		Criteria criteria = managerExample.createCriteria();
		criteria.andJobNumberEqualTo(jobNumber).andPasswordEqualTo(MD5Utils.md5("h" + password + "s"))
				.andIsDeleteEqualTo(false);
		List<TManager> list = managerMapper.selectByExample(managerExample);
		if (list.size() != 0) {
			return HuishoucatResult.HuishoucatResultOK(null, list.get(0));
		}
		return HuishoucatResult.HuishoucatResultError("账号或密码错误！", null);
	}

	@Override
	public PageBean<TManager> getManagerList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TManagerExample managerExample = new TManagerExample();
		managerExample.setOrderByClause(sort + " " + order);
		Criteria criteria = managerExample.createCriteria();
		criteria.andIsDeleteEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andTelephoneEqualTo(search);
		}
		List<TManager> list = managerMapper.selectByExample(managerExample);
		PageInfo<TManager> pageInfo = new PageInfo<TManager>(list);
		PageBean<TManager> pageBean = new PageBean<TManager>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateManager(TManager manager) {
		TManagerExample managerExample = new TManagerExample();
		if (StringUtils.isNoneBlank(manager.getTelephone())) {
			managerExample.createCriteria().andTelephoneEqualTo(manager.getTelephone());
			int count = managerMapper.countByExample(managerExample);
			if (count != 0) {
				return HuishoucatResult.HuishoucatResultError("该手机号已注册！", null);
			}
		}
		if (manager.getJobNumber() != null) {
			managerExample.clear();
			managerExample.createCriteria().andJobNumberEqualTo(manager.getJobNumber());
			int count = managerMapper.countByExample(managerExample);
			if (count != 0) {
				return HuishoucatResult.HuishoucatResultError("该工号已注册！", null);
			}
		}
		if (StringUtils.isNoneBlank(manager.getPassword())) {
			manager.setPassword(MD5Utils.md5("h" + manager.getPassword() + "s"));
		}
		manager.setUpdateTime(new Date());
		int i = managerMapper.updateByPrimaryKeySelective(manager);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新管理员信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteManagerByManagerId(Long[] managerIds) {
		TManager manager = new TManager();
		Date date = new Date();
		for (int i = 0; i < managerIds.length; i++) {
			manager.setManagerId(managerIds[i]);
			manager.setIsDelete(true);
			manager.setUpdateTime(date);
			managerMapper.updateByPrimaryKeySelective(manager);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult addManager(TManager manager) {
		TManagerExample managerExample = new TManagerExample();
		managerExample.createCriteria().andTelephoneEqualTo(manager.getTelephone());
		int count = managerMapper.countByExample(managerExample);
		if (count != 0) {
			return HuishoucatResult.HuishoucatResultError("该手机号已注册！", null);
		}
		managerExample.clear();
		managerExample.createCriteria().andJobNumberEqualTo(manager.getJobNumber());
		count = managerMapper.countByExample(managerExample);
		if (count != 0) {
			return HuishoucatResult.HuishoucatResultError("该工号已注册！", null);
		}
		Date date = new Date();
		manager.setCreateTime(date);
		manager.setUpdateTime(date);
		manager.setPassword(MD5Utils.md5("h" + manager.getPassword() + "s"));
		int i = managerMapper.insertSelective(manager);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK("", manager);
		}
		return HuishoucatResult.HuishoucatResultError("添加失败！", null);
	}
}
