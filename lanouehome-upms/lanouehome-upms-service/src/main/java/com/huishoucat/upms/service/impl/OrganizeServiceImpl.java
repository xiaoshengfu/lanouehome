package com.huishoucat.upms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TOrganizeMapper;
import com.huishoucat.manager.pojo.TOrganize;
import com.huishoucat.manager.pojo.TOrganizeExample;
import com.huishoucat.manager.pojo.TOrganizeExample.Criteria;
import com.huishoucat.upms.service.IOrganizeService;

public class OrganizeServiceImpl implements IOrganizeService {

	@Autowired
	private TOrganizeMapper organizeMapper;

	@Override
	public PageBean<TOrganize> getOrganizeList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TOrganizeExample organizeExample = new TOrganizeExample();
		organizeExample.setOrderByClause(sort + " " + order);
		Criteria criteria = organizeExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andNameLike("%" + search + "%");
		}
		List<TOrganize> list = organizeMapper.selectByExample(organizeExample);
		PageInfo<TOrganize> pageInfo = new PageInfo<TOrganize>(list);
		PageBean<TOrganize> pageBean = new PageBean<TOrganize>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateOrganize(TOrganize organize) {
		organize.setUpdateTime(new Date());
		int i = organizeMapper.updateByPrimaryKeySelective(organize);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新组织部门信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteOrganizeByOrganizeId(Long[] organizeIds) {
		TOrganize organize = new TOrganize();
		Date date = new Date();
		for (int i = 0; i < organizeIds.length; i++) {
			organize.setOrganizeId(organizeIds[i]);
			organize.setIsDeleted(true);
			organize.setUpdateTime(date);
			organizeMapper.updateByPrimaryKeySelective(organize);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}
}
