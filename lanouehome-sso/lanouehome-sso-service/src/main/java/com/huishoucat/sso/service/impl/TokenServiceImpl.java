package com.huishoucat.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.huishoucat.common.jedis.JedisClient;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.pojo.TRecycler;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.sso.service.ITokenService;

/**
 * Token操作Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 下午5:04:14
 * @version V1.0
 */
@Service
public class TokenServiceImpl implements ITokenService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;

	@Override
	public HuishoucatResult getUserByToken(String token) {
		// 根据token到redis中取用户信息
		String json = jedisClient.get("USER_SESSION:" + token);
		// 取不到用户信息，登录已经过期，返回登录过期
		if (StringUtils.isBlank(json)) {
			return HuishoucatResult.HuishoucatResultError("登录已经过期！", null);
		}
		// 取到用户信息更新token的过期时间
		jedisClient.expire("USER_SESSION:" + token, SESSION_EXPIRE);
		// 返回结果
		TUser user = JsonUtils.jsonToPojo(json, TUser.class);
		return HuishoucatResult.HuishoucatResultOK(null, user);
	}

	@Override
	public HuishoucatResult getRecyclerByToken(String token) {
		// 根据token到redis中取回收员信息
		String json = jedisClient.get("RECYCLER_SESSION:" + token);
		// 取不到回收员信息，登录已经过期，返回登录过期
		if (StringUtils.isBlank(json)) {
			return HuishoucatResult.HuishoucatResultError("登录已经过期！", null);
		}
		// 取到回收员信息更新token的过期时间
		jedisClient.expire("RECYCLER_SESSION:" + token, SESSION_EXPIRE);
		// 返回结果
		TRecycler recycler = JsonUtils.jsonToPojo(json, TRecycler.class);
		return HuishoucatResult.HuishoucatResultOK(null, recycler);
	}
}
