package com.huishoucat.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.cms.service.IWasteService;
import com.huishoucat.common.jedis.JedisClient;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TWasteMapper;
import com.huishoucat.manager.pojo.TWaste;
import com.huishoucat.manager.pojo.TWasteExample;
import com.huishoucat.manager.pojo.TWasteExample.Criteria;

/**
 * 废品Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月18日 下午8:14:11
 * @version V1.0
 */
@Service
public class WasteServiceImpl implements IWasteService {

	@Value("${WASTE_LIST}")
	private String WASTE_LIST;
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@Autowired
	private TWasteMapper wasteMapper;
	@Autowired
	private JedisClient jedisClient;

	@Override
	public PageBean<TWaste> getWasteList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TWasteExample wasteExample = new TWasteExample();
		wasteExample.setOrderByClause(sort + " " + order);
		Criteria criteria = wasteExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andNameLike("%" + search + "%");
		}
		List<TWaste> list = wasteMapper.selectByExampleWithBLOBs(wasteExample);
		PageInfo<TWaste> pageInfo = new PageInfo<TWaste>(list);
		PageBean<TWaste> pageBean = new PageBean<TWaste>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateWaste(TWaste waste) {
		if (waste.getParentId() != null) {
			TWaste parentWaste = wasteMapper.selectByPrimaryKey(waste.getParentId());
			if (parentWaste == null) {
				return HuishoucatResult.HuishoucatResultError("无效父废品ID", null);
			}
			TWasteExample wasteExample = new TWasteExample();
			wasteExample.createCriteria().andParentIdEqualTo(parentWaste.getWasteId()).andIsDeletedEqualTo(false);
			int i = wasteMapper.countByExample(wasteExample);
			if (i == 1) {
				TWaste fixWaste = new TWaste();
				fixWaste.setUpdateTime(new Date());
				fixWaste.setWasteId(parentWaste.getWasteId());
				fixWaste.setIsParent(false);
				int j = wasteMapper.updateByPrimaryKeySelective(fixWaste);
				jedisClient.hdel(WASTE_LIST, waste.getParentId().toString());
				if (j != 1) {
					return HuishoucatResult.HuishoucatResultError("更新废品信息失败！", null);
				}
			}
		}
		TWaste oldWaste = wasteMapper.selectByPrimaryKey(waste.getWasteId());
		waste.setUpdateTime(new Date());
		jedisClient.hdel(WASTE_LIST, oldWaste.getParentId().toString());
		int i = wasteMapper.updateByPrimaryKeySelective(waste);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新废品信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteWasteByWasteId(Long[] wasteIds) {
		TWasteExample wasteExample = new TWasteExample();
		TWaste waste = new TWaste();
		waste.setIsDeleted(true);
		waste.setUpdateTime(new Date());
		boolean notComplete = false;
		long num = 0;
		for (int i = 0; i < wasteIds.length; i++) {
			TWaste oldWaste = wasteMapper.selectByPrimaryKey(wasteIds[i]);
			jedisClient.hdel(WASTE_LIST, oldWaste.getParentId().toString());
			wasteExample.createCriteria().andParentIdEqualTo(wasteIds[i]);
			if (wasteMapper.selectByExample(wasteExample).size() != 0) {
				notComplete = true;
				num++;
				continue;
			}
			waste.setWasteId(wasteIds[i]);
			wasteMapper.updateByPrimaryKeySelective(waste);
		}
		if (notComplete) {
			return HuishoucatResult.HuishoucatResultWarning("有" + num + "条废品信息未删除原因：父废品不能被删除！", null);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult addWaste(TWaste waste) {
		if (waste.getParentId() != null && waste.getParentId() != 0) {
			TWaste parentWaste = wasteMapper.selectByPrimaryKey(waste.getParentId());
			if (parentWaste != null && !parentWaste.getIsDeleted()) {
				waste.setLevel(parentWaste.getLevel() + 1);
				if (!parentWaste.getIsParent()) {
					TWaste fixWaste = new TWaste();
					fixWaste.setWasteId(parentWaste.getWasteId());
					fixWaste.setIsParent(true);
					fixWaste.setUpdateTime(new Date());
					int i = wasteMapper.updateByPrimaryKeySelective(fixWaste);
					if (i != 1) {
						return HuishoucatResult.HuishoucatResultError("添加区域失败！", null);
					}
				}
			} else {
				return HuishoucatResult.HuishoucatResultError("无效父废品ID！", null);
			}
		} else {
			waste.setLevel(1);
			waste.setIsParent(false);
		}
		Date date = new Date();
		waste.setCreateTime(date);
		waste.setUpdateTime(date);
		int i = wasteMapper.insertSelective(waste);
		if (i == 1) {
			jedisClient.hdel(WASTE_LIST, waste.getParentId().toString());
			return HuishoucatResult.HuishoucatResultOK(null, waste);
		}
		return HuishoucatResult.HuishoucatResultError("添加区域失败！", null);
	}

	@Override
	public HuishoucatResult findWasteByWasteId(Long wasteId) {
		TWaste waste = wasteMapper.selectByPrimaryKey(wasteId);
		if (waste != null && !waste.getIsDeleted()) {
			waste.setPictureUrl(IMAGE_SERVER_URL + waste.getPictureUrl());
			return HuishoucatResult.HuishoucatResultOK(null, waste);
		}
		return HuishoucatResult.HuishoucatResultError("无效废品ID!", null);
	}

	@Override
	public HuishoucatResult findChildWaste(Long parentWasteId) {
		// 查询缓存
		try {
			// 如果缓存中有直接响应结果
			String json = jedisClient.hget(WASTE_LIST, parentWasteId + "");
			if (StringUtils.isNotBlank(json)) {
				List<TWaste> list = JsonUtils.jsonToList(json, TWaste.class);
				return HuishoucatResult.HuishoucatResultOK(null, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TWasteExample wasteExample = new TWasteExample();
		wasteExample.setOrderByClause("sort asc");
		wasteExample.createCriteria().andParentIdEqualTo(parentWasteId).andIsDeletedEqualTo(false);
		List<TWaste> list2 = wasteMapper.selectByExampleWithBLOBs(wasteExample);
		TWaste waste = null;
		for (int i = 0; i < list2.size(); i++) {
			waste = list2.get(i);
			waste.setPictureUrl(IMAGE_SERVER_URL + waste.getPictureUrl());
		}
		try {
			jedisClient.hset(WASTE_LIST, parentWasteId + "", JsonUtils.objectToJson(list2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HuishoucatResult.HuishoucatResultOK(null, list2);
	}
}
