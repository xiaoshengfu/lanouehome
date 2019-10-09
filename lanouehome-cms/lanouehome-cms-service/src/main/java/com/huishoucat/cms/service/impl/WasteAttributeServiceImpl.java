package com.huishoucat.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.cms.service.IWasteAttributeService;
import com.huishoucat.common.jedis.JedisClient;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TWasteAttributeMapper;
import com.huishoucat.manager.pojo.TWasteAttribute;
import com.huishoucat.manager.pojo.TWasteAttributeExample;
import com.huishoucat.manager.pojo.TWasteAttributeExample.Criteria;

/**
 * 废品属性Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月11日 下午10:24:25
 * @version V1.0
 */
@Service
public class WasteAttributeServiceImpl implements IWasteAttributeService {

	@Value("${WASTE_ATTIBUTE_LIST}")
	private String WASTE_ATTIBUTE_LIST;

	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TWasteAttributeMapper wasteAttributeMapper;

	@Override
	public PageBean<TWasteAttribute> getWasteAttributeList(int pageNum, int limit, String sort, String order,
			String search) {
		PageHelper.startPage(pageNum, limit);
		TWasteAttributeExample wasteAttributeExample = new TWasteAttributeExample();
		wasteAttributeExample.setOrderByClause(sort + " " + order);
		Criteria criteria = wasteAttributeExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andWasteIdEqualTo(Long.parseLong(search));
		}
		List<TWasteAttribute> list = wasteAttributeMapper.selectByExampleWithBLOBs(wasteAttributeExample);
		PageInfo<TWasteAttribute> pageInfo = new PageInfo<TWasteAttribute>(list);
		PageBean<TWasteAttribute> pageBean = new PageBean<TWasteAttribute>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateWasteAttribute(TWasteAttribute wasteAttribute) {
		wasteAttribute.setUpdateTime(new Date());
		TWasteAttribute oldWasteAttribute = wasteAttributeMapper
				.selectByPrimaryKey(wasteAttribute.getWasteAttributeId());
		if (oldWasteAttribute != null) {
			int i = wasteAttributeMapper.updateByPrimaryKeySelective(wasteAttribute);
			if (i == 1) {
				jedisClient.hdel(WASTE_ATTIBUTE_LIST, oldWasteAttribute.getWasteId().toString());
				return HuishoucatResult.HuishoucatResultOK(null, null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("更新废品失败！", null);
	}

	@Override
	public HuishoucatResult deleteWasteAttributeByWasteAttributeId(Long[] wasteAttributeIds) {
		TWasteAttribute wasteAttribute = new TWasteAttribute();
		wasteAttribute.setUpdateTime(new Date());
		wasteAttribute.setIsDeleted(true);
		for (int i = 0; i < wasteAttributeIds.length; i++) {
			wasteAttribute.setWasteAttributeId(wasteAttributeIds[i]);
			wasteAttributeMapper.updateByPrimaryKeySelective(wasteAttribute);
			jedisClient.hdel(WASTE_ATTIBUTE_LIST, wasteAttributeIds[i].toString());
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult addWasteAttribute(TWasteAttribute wasteAttribute) {
		Date date = new Date();
		wasteAttribute.setCreateTime(date);
		wasteAttribute.setUpdateTime(date);
		int i = wasteAttributeMapper.insertSelective(wasteAttribute);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, wasteAttribute);
		}
		return HuishoucatResult.HuishoucatResultError("添加商品属性失败！", null);
	}

	@Override
	public HuishoucatResult findWasteAttributeByWasteId(Long wasteId) {
		// 查询缓存
		try {
			// 如果缓存中有直接响应结果
			String json = jedisClient.hget(WASTE_ATTIBUTE_LIST, wasteId + "");
			if (StringUtils.isNotBlank(json)) {
				TWasteAttribute wasteAttribute = JsonUtils.jsonToPojo(json, TWasteAttribute.class);
				return HuishoucatResult.HuishoucatResultOK(null, wasteAttribute);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TWasteAttributeExample wasteAttributeExample = new TWasteAttributeExample();
		wasteAttributeExample.createCriteria().andWasteIdEqualTo(wasteId).andIsDeletedEqualTo(false);
		List<TWasteAttribute> list = wasteAttributeMapper.selectByExampleWithBLOBs(wasteAttributeExample);
		if (list.size() != 0) {
			try {
				jedisClient.hset(WASTE_ATTIBUTE_LIST, wasteId + "", JsonUtils.objectToJson(list.get(0)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return HuishoucatResult.HuishoucatResultOK(null, list.get(0));
		}
		return HuishoucatResult.HuishoucatResultError("无效废品ID！", null);
	}

}
