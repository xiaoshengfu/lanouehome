package com.huishoucat.ucenter.service.impl;

import java.util.ArrayList;
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
import com.huishoucat.manager.mapper.TRecyclerMapper;
import com.huishoucat.manager.mapper.TRelationRecyclerDistrictMapper;
import com.huishoucat.manager.pojo.TRecycler;
import com.huishoucat.manager.pojo.TRecyclerExample;
import com.huishoucat.manager.pojo.TRecyclerExample.Criteria;
import com.huishoucat.manager.pojo.TRelationRecyclerDistrict;
import com.huishoucat.manager.pojo.TRelationRecyclerDistrictExample;
import com.huishoucat.ucenter.service.IRecyclerService;

/**
 * 回收员Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月13日 上午10:58:16
 * @version V1.0
 */
@Service
public class RecyclerServiceImpl implements IRecyclerService {

	@Autowired
	private TRecyclerMapper recyclerMapper;
	@Autowired
	private TRelationRecyclerDistrictMapper recyclerDistrictMapper;

	@Override
	public PageBean<TRecycler> getRecyclerList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TRecyclerExample recyclerExample = new TRecyclerExample();
		recyclerExample.setOrderByClause(sort + " " + order);
		Criteria criteria = recyclerExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andTelephoneEqualTo(search);
		}
		List<TRecycler> list = recyclerMapper.selectByExample(recyclerExample);
		PageInfo<TRecycler> pageInfo = new PageInfo<TRecycler>(list);
		PageBean<TRecycler> pageBean = new PageBean<TRecycler>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateRecycler(TRecycler recycler) {
		if (StringUtils.isNoneBlank(recycler.getTelephone())) {
			TRecyclerExample recyclerExample = new TRecyclerExample();
			Criteria criteria = recyclerExample.createCriteria();
			criteria.andTelephoneEqualTo(recycler.getTelephone());
			int count = recyclerMapper.countByExample(recyclerExample);
			if (count != 0) {
				return HuishoucatResult.HuishoucatResultError("该手机号已注册！", null);
			}
		}
		if (StringUtils.isNoneBlank(recycler.getPassword())) {
			recycler.setPassword(MD5Utils.md5("h" + recycler.getPassword() + "s"));
		}
		recycler.setUpdateTime(new Date());
		int i = recyclerMapper.updateByPrimaryKeySelective(recycler);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新回收员信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteRecyclerByRecyclerId(Long[] recyclerIds) {
		TRelationRecyclerDistrictExample example = new TRelationRecyclerDistrictExample();
		TRecycler recycler = new TRecycler();
		Date date = new Date();
		for (int i = 0; i < recyclerIds.length; i++) {
			recycler.setRecyclerId(recyclerIds[i]);
			recycler.setIsDeleted(true);
			recycler.setUpdateTime(date);
			recyclerMapper.updateByPrimaryKeySelective(recycler);
			example.clear();
			example.createCriteria().andRecyclerIdEqualTo(recyclerIds[i]);
			recyclerDistrictMapper.deleteByExample(example);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult addRecycler(TRecycler recycler, Long[] districtIds) {
		TRecyclerExample recyclerExample = new TRecyclerExample();
		Criteria criteria = recyclerExample.createCriteria();
		criteria.andTelephoneEqualTo(recycler.getTelephone());
		int count = recyclerMapper.countByExample(recyclerExample);
		if (count != 0) {
			return HuishoucatResult.HuishoucatResultError("该手机号已注册！", null);
		}
		Date date = new Date();
		recycler.setCreateTime(date);
		recycler.setUpdateTime(date);
		recycler.setPassword(MD5Utils.md5("h" + recycler.getPassword() + "s"));
		int i = recyclerMapper.insertSelective(recycler);
		if (i == 1) {
			TRelationRecyclerDistrict relationRecyclerDistrict = new TRelationRecyclerDistrict();
			relationRecyclerDistrict.setRecyclerId(recycler.getRecyclerId());
			for (int j = 0; j < districtIds.length; j++) {
				relationRecyclerDistrict.setDistrictId(districtIds[j]);
				recyclerDistrictMapper.insertSelective(relationRecyclerDistrict);
			}
			return HuishoucatResult.HuishoucatResultOK("", recycler);
		}
		return HuishoucatResult.HuishoucatResultError("添加失败！", null);
	}

	@Override
	public HuishoucatResult findRecyclerByRecyclerId(Long recyclerId) {
		TRecycler recycler = recyclerMapper.selectByPrimaryKey(recyclerId);
		if (recycler != null) {
			return HuishoucatResult.HuishoucatResultOK(null, recycler);
		}
		return HuishoucatResult.HuishoucatResultError("无效回收员ID！", null);
	}

	@Override
	public HuishoucatResult findRecyclerDistrictIds(Long recyclerId) {
		ArrayList<Long> districtIdList = new ArrayList<Long>();
		TRelationRecyclerDistrictExample example = new TRelationRecyclerDistrictExample();
		example.createCriteria().andRecyclerIdEqualTo(recyclerId);
		List<TRelationRecyclerDistrict> list = recyclerDistrictMapper.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			districtIdList.add(list.get(i).getDistrictId());
		}
		return HuishoucatResult.HuishoucatResultOK(null, districtIdList);
	}

	@Override
	public HuishoucatResult updateRecyclerDistrictIds(Long recyclerId, Long[] districtIds) {
		if (recyclerMapper.selectByPrimaryKey(recyclerId) != null) {
			TRelationRecyclerDistrictExample example = new TRelationRecyclerDistrictExample();
			example.createCriteria().andRecyclerIdEqualTo(recyclerId);
			recyclerDistrictMapper.deleteByExample(example);
			TRelationRecyclerDistrict relationRecyclerDistrict = new TRelationRecyclerDistrict();
			relationRecyclerDistrict.setRecyclerId(recyclerId);
			for (int j = 0; j < districtIds.length; j++) {
				relationRecyclerDistrict.setDistrictId(districtIds[j]);
				recyclerDistrictMapper.insertSelective(relationRecyclerDistrict);
			}
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("无效回收员ID！", null);
	}

	@Override
	public HuishoucatResult findRecyclerByDistrictId(Long districtId) {
		TRelationRecyclerDistrict recyclerDistrict = recyclerDistrictMapper.selectByPrimaryKey(districtId);
		if (recyclerDistrict != null) {
			TRecycler recycler = recyclerMapper.selectByPrimaryKey(recyclerDistrict.getRecyclerId());
			return HuishoucatResult.HuishoucatResultOK(null, recycler);
		}
		return HuishoucatResult.HuishoucatResultError("无效区域ID！", null);
	}
}
