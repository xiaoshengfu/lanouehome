package com.huishoucat.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.huishoucat.common.jedis.JedisClient;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.IDUtils;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.common.utils.MD5Utils;
import com.huishoucat.common.utils.SMSUtils;
import com.huishoucat.manager.mapper.TRecyclerMapper;
import com.huishoucat.manager.pojo.TRecycler;
import com.huishoucat.manager.pojo.TRecyclerExample;
import com.huishoucat.manager.pojo.TRecyclerExample.Criteria;
import com.huishoucat.sso.service.IRecyclerLoginService;

/**
 * 回收员登录Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月10日 下午5:47:52
 * @version V1.0
 */
@Service
public class RecyclerLoginServiceImpl implements IRecyclerLoginService {

	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	@Value("${SIGN_NAME}")
	private String SIGN_NAME;
	@Value("${TEMPLATE_CODE}")
	private String TEMPLATE_CODE;
	@Value("${CODE_CACHE_EXPIRE}")
	private Integer CODE_CACHE_EXPIRE;
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@Autowired
	private TRecyclerMapper recyclerMapper;
	@Autowired
	private JedisClient jedisClient;

	@Override
	public HuishoucatResult recyclerLoginByPassword(String telephone, String password) {
		TRecyclerExample recyclerExample = new TRecyclerExample();
		Criteria criteria = recyclerExample.createCriteria();
		criteria.andTelephoneEqualTo(telephone).andPasswordEqualTo(MD5Utils.md5("h" + password + "s"))
				.andStateEqualTo(1).andIsDeletedEqualTo(false);
		List<TRecycler> list = recyclerMapper.selectByExample(recyclerExample);
		if (list.size() != 0) {
			TRecycler recycler = list.get(0);
			// 生成token
			String token = IDUtils.getUUID();
			recycler.setPictureUrl(IMAGE_SERVER_URL + recycler.getPictureUrl());
			// 密码置为null
			recycler.setPassword(null);
			recycler.setPaymentPassword(null);
			jedisClient.set("RECYCLER_SESSION:" + token, JsonUtils.objectToJson(recycler));
			// 设置Session的过期时间
			jedisClient.expire("RECYCLER_SESSION:" + token, SESSION_EXPIRE);
			// 把token返回
			return HuishoucatResult.HuishoucatResultOK(null, token);
		}
		return HuishoucatResult.HuishoucatResultError("密码或账号错误！", null);
	}

	@Override
	public HuishoucatResult recyclerLoginByOpenid(String openid) {
		TRecyclerExample recyclerExample = new TRecyclerExample();
		Criteria criteria = recyclerExample.createCriteria();
		criteria.andOpenidEqualTo(openid).andStateEqualTo(1).andIsDeletedEqualTo(false);
		List<TRecycler> list = recyclerMapper.selectByExample(recyclerExample);
		if (list.size() != 0) {
			TRecycler recycler = list.get(0);
			// 生成token
			String token = IDUtils.getUUID();
			recycler.setPictureUrl(IMAGE_SERVER_URL + recycler.getPictureUrl());
			// 密码置为null
			recycler.setPassword(null);
			recycler.setPaymentPassword(null);
			jedisClient.set("RECYCLER_SESSION:" + token, JsonUtils.objectToJson(recycler));
			// 设置Session的过期时间
			jedisClient.expire("RECYCLER_SESSION:" + token, SESSION_EXPIRE);
			// 把token返回
			return HuishoucatResult.HuishoucatResultOK(null, token);
		}
		return HuishoucatResult.HuishoucatResultError("该回收员未注册！", null);
	}

	@Override
	public HuishoucatResult recyclerLoginByTelephone(String telephone, String verificationCode) {
		String code = jedisClient.get("RECYCLER_LOGIN_CODE:" + telephone);
		if (verificationCode.equals(code)) {
			TRecyclerExample recyclerExample = new TRecyclerExample();
			Criteria criteria = recyclerExample.createCriteria();
			criteria.andTelephoneEqualTo(telephone).andStateEqualTo(1).andIsDeletedEqualTo(false);
			List<TRecycler> list = recyclerMapper.selectByExample(recyclerExample);
			if (list.size() == 1) {
				TRecycler recycler = list.get(0);
				// 生成token
				String token = IDUtils.getUUID();
				recycler.setPictureUrl(IMAGE_SERVER_URL + recycler.getPictureUrl());
				// 密码置为null
				recycler.setPassword(null);
				recycler.setPaymentPassword(null);
				jedisClient.set("RECYCLER_SESSION:" + token, JsonUtils.objectToJson(recycler));
				// 设置Session的过期时间
				jedisClient.expire("RECYCLER_SESSION:" + token, SESSION_EXPIRE);
				// 把token返回
				return HuishoucatResult.HuishoucatResultOK(null, token);
			} else {
				return HuishoucatResult.HuishoucatResultError("登录失败！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("验证码错误！", null);
	}

	@Override
	public HuishoucatResult sendRecyclerLoginSMSCode(String telephone) {
		try {
			TRecyclerExample recyclerExample = new TRecyclerExample();
			recyclerExample.createCriteria().andTelephoneEqualTo(telephone).andStateEqualTo(1)
					.andIsDeletedEqualTo(false);
			int i = recyclerMapper.countByExample(recyclerExample);
			if (i != 0) {
				String code = IDUtils.getVerificationCode();
				jedisClient.set("RECYCLER_LOGIN_CODE:" + telephone, code);
				// 设置过期时间
				jedisClient.expire("RECYCLER_LOGIN_CODE:" + telephone, CODE_CACHE_EXPIRE);
				SMSUtils.sendSMSVerificationCode(telephone, SIGN_NAME, TEMPLATE_CODE, code);
				return HuishoucatResult.HuishoucatResultOK(null, null);
			} else {
				return HuishoucatResult.HuishoucatResultError("该手机号未注册！", null);
			}
			// ClientException e
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HuishoucatResult.HuishoucatResultError("未知错误！", null);
	}
}
