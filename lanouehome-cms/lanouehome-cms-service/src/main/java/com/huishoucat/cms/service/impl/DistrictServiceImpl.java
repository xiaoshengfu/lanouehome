package com.huishoucat.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.cms.service.IDistrictService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TDistrictMapper;
import com.huishoucat.manager.pojo.TDistrict;
import com.huishoucat.manager.pojo.TDistrictExample;
import com.huishoucat.manager.pojo.TDistrictExample.Criteria;

/**
 * 区域Sercice
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月12日 下午1:25:58
 * @version V1.0
 */
@Service
public class DistrictServiceImpl implements IDistrictService {

	@Autowired
	private TDistrictMapper districtMapper;

	@Override
	public PageBean<TDistrict> getDistrictList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TDistrictExample districtExample = new TDistrictExample();
		districtExample.setOrderByClause(sort + " " + order);
		Criteria criteria = districtExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andDistrictNameLike("%" + search + "%");
		}
		List<TDistrict> list = districtMapper.selectByExample(districtExample);
		PageInfo<TDistrict> pageInfo = new PageInfo<TDistrict>(list);
		PageBean<TDistrict> pageBean = new PageBean<TDistrict>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateDistrict(TDistrict district) {
		if (district.getParentId() != null && district.getParentId() != 0) {
			TDistrict parentDistrict = districtMapper.selectByPrimaryKey(district.getParentId());
			if (parentDistrict == null) {
				return HuishoucatResult.HuishoucatResultError("无效父区域ID！", null);
			}
		}
		district.setUpdateTime(new Date());
		int i = districtMapper.updateByPrimaryKeySelective(district);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新区域信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteDistrictByDistrictId(Long[] districtIds) {
		TDistrictExample districtExample = new TDistrictExample();
		TDistrict district = new TDistrict();
		district.setIsDeleted(true);
		district.setUpdateTime(new Date());
		boolean notComplete = false;
		long num = 0;
		for (int i = 0; i < districtIds.length; i++) {
			districtExample.createCriteria().andParentIdEqualTo(districtIds[i]);
			districtExample.clear();
			if (districtMapper.selectByExample(districtExample).size() != 0) {
				notComplete = true;
				num++;
				continue;
			}
			district.setDistrictId(districtIds[i]);
			districtMapper.updateByPrimaryKeySelective(district);
		}
		if (notComplete) {
			return HuishoucatResult.HuishoucatResultWarning("有" + num + "条地区信息未删除原因：父地区不能被删除！", null);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult findDistrictByDistrictId(Long districtId) {
		TDistrict district = districtMapper.selectByPrimaryKey(districtId);
		if (district != null && !district.getIsDeleted()) {
			return HuishoucatResult.HuishoucatResultOK(null, district);
		}
		return HuishoucatResult.HuishoucatResultError("没有该区域ID", null);
	}

	@Override
	public HuishoucatResult addDistrict(TDistrict district) {
		if (district.getParentId() != null && district.getParentId() != 0) {
			TDistrict parentDistrict = districtMapper.selectByPrimaryKey(district.getParentId());
			if (parentDistrict != null && !parentDistrict.getIsDeleted()) {
				district.setLevel(parentDistrict.getLevel() + 1);
			} else {
				return HuishoucatResult.HuishoucatResultError("无效父区域ID！", null);
			}
		} else {
			district.setLevel(1);
		}
		district.setIsDeleted(false);
		Date date = new Date();
		district.setCreateTime(date);
		district.setUpdateTime(date);
		int i = districtMapper.insertSelective(district);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, district);
		}
		return HuishoucatResult.HuishoucatResultError("添加区域失败！", null);
	}

	@Override
	public HuishoucatResult getChildrenDistrictList(Long districtId) {
		TDistrictExample districtExample = new TDistrictExample();
		Criteria criteria = districtExample.createCriteria();
		criteria.andParentIdEqualTo(districtId);
		criteria.andIsDeletedEqualTo(false);
		List<TDistrict> list = districtMapper.selectByExample(districtExample);
		return HuishoucatResult.HuishoucatResultOK(null, list);
	}
}
