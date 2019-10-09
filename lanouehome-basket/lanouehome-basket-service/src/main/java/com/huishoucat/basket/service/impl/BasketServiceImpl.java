package com.huishoucat.basket.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.huishoucat.basket.service.IBasketService;
import com.huishoucat.common.jedis.JedisClient;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.IDUtils;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TWasteMapper;
import com.huishoucat.manager.pojo.TWaste;
import com.huishoucat.manager.pojo.TWasteOrderItem;

/**
 * 废品筐Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午7:42:12
 * @version V1.0
 */
@Service
public class BasketServiceImpl implements IBasketService {

	@Value("${REDIS_BASKET_PRE}")
	private String REDIS_BASKET_PRE;
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TWasteMapper wasteMapper;

	@Override
	public HuishoucatResult addBasket(String openid, TWasteOrderItem wasteOrderItem) {
		TWaste waste = wasteMapper.selectByPrimaryKey(wasteOrderItem.getWasteId());
		waste.setPictureUrl(IMAGE_SERVER_URL + waste.getPictureUrl());
		wasteOrderItem.setWaste(waste);
		wasteOrderItem.setOrderItemId(IDUtils.getItemId());
		jedisClient.hset(REDIS_BASKET_PRE + ":" + openid, wasteOrderItem.getOrderItemId() + "",
				JsonUtils.objectToJson(wasteOrderItem));
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult getBasketList(String openid) {
		List<String> jsonList = jedisClient.hvals(REDIS_BASKET_PRE + ":" + openid);
		List<TWasteOrderItem> wasteOrderItemList = new ArrayList<TWasteOrderItem>();
		for (int i = 0; i < jsonList.size(); i++) {
			TWasteOrderItem wasteOrderItem = JsonUtils.jsonToPojo(jsonList.get(i), TWasteOrderItem.class);
			wasteOrderItemList.add(wasteOrderItem);
		}
		return HuishoucatResult.HuishoucatResultOK(null, wasteOrderItemList);
	}

	@Override
	public HuishoucatResult editBasketNum(String openid, long wasteOrderItemId, int num) {
		String json = jedisClient.hget(REDIS_BASKET_PRE + ":" + openid, wasteOrderItemId + "");
		TWasteOrderItem wasteOrderItem = JsonUtils.jsonToPojo(json, TWasteOrderItem.class);
		wasteOrderItem.setEstimateNum(num);
		jedisClient.hset(REDIS_BASKET_PRE + ":" + openid, wasteOrderItemId + "",
				JsonUtils.objectToJson(wasteOrderItem));
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult deleteBasketWaste(String openid, Long[] wasteOrderItemIds) {
		for (int i = 0; i < wasteOrderItemIds.length; i++) {
			jedisClient.hdel(REDIS_BASKET_PRE + ":" + openid, wasteOrderItemIds[i] + "");
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult clearBasketWaste(String openid) {
		jedisClient.del(REDIS_BASKET_PRE + ":" + openid);
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult findWasteListInBasketWaste(String openid, Long[] wasteOrderItemIds) {
		List<TWasteOrderItem> list = new ArrayList<TWasteOrderItem>(wasteOrderItemIds.length);
		for (int i = 0; i < wasteOrderItemIds.length; i++) {
			String json = jedisClient.hget(REDIS_BASKET_PRE + ":" + openid, wasteOrderItemIds[i] + "");
			if (StringUtils.isNotBlank(json)) {
				list.add(JsonUtils.jsonToPojo(json, TWasteOrderItem.class));
			}
		}
		return HuishoucatResult.HuishoucatResultOK(null, list);
	}
}
