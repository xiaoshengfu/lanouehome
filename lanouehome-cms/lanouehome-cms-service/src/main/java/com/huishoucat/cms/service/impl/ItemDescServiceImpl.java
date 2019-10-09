package com.huishoucat.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.cms.service.IItemDescService;
import com.huishoucat.common.jedis.JedisClient;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TItemDescMapper;
import com.huishoucat.manager.pojo.TItemDesc;
import com.huishoucat.manager.pojo.TItemDescExample;
import com.huishoucat.manager.pojo.TItemDescExample.Criteria;

/**
 * 积分商品Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月27日 下午2:15:37
 * @version V1.0
 */
@Service
public class ItemDescServiceImpl implements IItemDescService {

	@Value("${ITEM_DESC}")
	private String ITEM_DESC;
	@Value("${ITEM_DESC_LIST}")
	private String ITEM_DESC_LIST;

	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TItemDescMapper itemDescMapper;

	@Override
	public PageBean<TItemDesc> getItemDescList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TItemDescExample itemDescExample = new TItemDescExample();
		itemDescExample.setOrderByClause(sort + " " + order);
		Criteria criteria = itemDescExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andNameLike("%" + search + "%");
		}
		List<TItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(itemDescExample);
		PageInfo<TItemDesc> pageInfo = new PageInfo<TItemDesc>(list);
		PageBean<TItemDesc> pageBean = new PageBean<TItemDesc>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateItemDesc(TItemDesc itemDesc) {
		if (itemDesc.getItemId() != null) {
			itemDesc.setUpdateTime(new Date());
			int i = itemDescMapper.updateByPrimaryKeySelective(itemDesc);
			if (i == 1) {
				jedisClient.del(ITEM_DESC + ":" + itemDesc.getItemId());
				return HuishoucatResult.HuishoucatResultOK(null, null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("更新商品信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteItemDescByItemId(Long[] itemIds) {
		TItemDesc itemDesc = new TItemDesc();
		itemDesc.setUpdateTime(new Date());
		for (int i = 0; i < itemIds.length; i++) {
			itemDesc.setItemId(itemIds[i]);
			itemDesc.setIsDeleted(true);
			int j = itemDescMapper.updateByPrimaryKeySelective(itemDesc);
			if (j != 0) {
				jedisClient.del(ITEM_DESC + ":" + itemIds[i]);
			}
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult findItemDescByItemId(Long itemId) {
		// 查询缓存
		try {
			// 如果缓存中有直接响应结果
			String json = jedisClient.get(ITEM_DESC + ":" + itemId);
			if (StringUtils.isNotBlank(json)) {
				TItemDesc redisItemDesc = JsonUtils.jsonToPojo(json, TItemDesc.class);
				return HuishoucatResult.HuishoucatResultOK(null, redisItemDesc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		if (itemDesc != null && !itemDesc.getIsDeleted()) {
			try {
				jedisClient.set(ITEM_DESC + ":" + itemId, JsonUtils.objectToJson(itemDesc));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return HuishoucatResult.HuishoucatResultOK(null, itemDesc);
		}
		return HuishoucatResult.HuishoucatResultError("无效商品积分ID！", null);
	}

	@Override
	public HuishoucatResult addItemDesc(TItemDesc itemDesc) {
		Date date = new Date();
		itemDesc.setUpdateTime(date);
		itemDesc.setCreateTime(date);
		int i = itemDescMapper.insertSelective(itemDesc);
		if (i == 1) {
			jedisClient.del(ITEM_DESC_LIST);
			return HuishoucatResult.HuishoucatResultOK(null, itemDesc);
		}
		return HuishoucatResult.HuishoucatResultError("添加积分商品失败！", null);
	}

	@Override
	public HuishoucatResult findItemDescList() {
		// 查询缓存
		try {
			// 如果缓存中有直接响应结果
			String json = jedisClient.get(ITEM_DESC_LIST);
			if (StringUtils.isNotBlank(json)) {
				List<TItemDesc> redisList = JsonUtils.jsonToList(json, TItemDesc.class);
				return HuishoucatResult.HuishoucatResultOK(null, redisList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TItemDescExample itemDescExample = new TItemDescExample();
		itemDescExample.setOrderByClause("price asc");
		itemDescExample.createCriteria().andIsDeletedEqualTo(false);
		List<TItemDesc> list = itemDescMapper.selectByExample(itemDescExample);
		try {
			jedisClient.set(ITEM_DESC_LIST, JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HuishoucatResult.HuishoucatResultOK(null, list);
	}
}
