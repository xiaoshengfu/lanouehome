package com.huishoucat.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyuncs.exceptions.ClientException;
import com.huishoucat.common.jedis.JedisClient;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.IDUtils;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.common.utils.MD5Utils;
import com.huishoucat.common.utils.SMSUtils;
import com.huishoucat.manager.mapper.TUserMapper;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.manager.pojo.TUserExample;
import com.huishoucat.manager.pojo.TUserExample.Criteria;
import com.huishoucat.sso.service.IUserLoginService;

/**
 * 用户登录service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月9日 上午9:58:17
 * @version V1.0
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService {

	@Value("${SIGN_NAME}")
	private String SIGN_NAME;
	@Value("${TEMPLATE_CODE}")
	private String TEMPLATE_CODE;
	@Value("${CODE_CACHE_EXPIRE}")
	private Integer CODE_CACHE_EXPIRE;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;

	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;

	@Override
	public HuishoucatResult userLoginByPassword(String telephone, String password) {
		TUserExample userExample = new TUserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andTelephoneEqualTo(telephone).andPasswordEqualTo(MD5Utils.md5("h" + password + "s"))
				.andStateEqualTo(1).andIsDeletedEqualTo(false);
		List<TUser> list = userMapper.selectByExample(userExample);
		if (list.size() != 0) {
			TUser user = list.get(0);
			// 生成token
			String token = IDUtils.getUUID();
			// 密码置为null
			user.setPassword(null);
			jedisClient.set("USER_SESSION:" + token, JsonUtils.objectToJson(user));
			// 设置Session的过期时间
			jedisClient.expire("USER_SESSION:" + token, SESSION_EXPIRE);
			// 把token返回
			return HuishoucatResult.HuishoucatResultOK(null, token);
		}
		return HuishoucatResult.HuishoucatResultError("密码或账号错误！", null);
	}

	@Override
	public HuishoucatResult userLoginByOpenid(String openid) {
		TUserExample userExample = new TUserExample();
		Criteria criteria = userExample.createCriteria();
		criteria.andOpenidEqualTo(openid).andStateEqualTo(1).andIsDeletedEqualTo(false);
		List<TUser> list = userMapper.selectByExample(userExample);
		if (list.size() != 0) {
			TUser user = list.get(0);
			// 生成token
			String token = IDUtils.getUUID();
			// 密码置为null
			user.setPassword(null);
			jedisClient.set("USER_SESSION:" + token, JsonUtils.objectToJson(user));
			// 设置Session的过期时间
			jedisClient.expire("USER_SESSION:" + token, SESSION_EXPIRE);
			// 把token返回
			return HuishoucatResult.HuishoucatResultOK(null, token);
		}
		return HuishoucatResult.HuishoucatResultError("该用户未注册！", null);
	}

	@Override
	public HuishoucatResult sendUserLoginSMSCode(String telephone) {
		try {
			TUserExample userExample = new TUserExample();
			userExample.createCriteria().andTelephoneEqualTo(telephone).andStateEqualTo(1).andIsDeletedEqualTo(false);
			int i = userMapper.countByExample(userExample);
			if (i != 0) {
				String code = IDUtils.getVerificationCode();
				jedisClient.set("USER_LOGIN_CODE:" + telephone, code);
				// 设置过期时间
				jedisClient.expire("USER_LOGIN_CODE:" + telephone, CODE_CACHE_EXPIRE);
				SMSUtils.sendSMSVerificationCode(telephone, SIGN_NAME, TEMPLATE_CODE, code);
				return HuishoucatResult.HuishoucatResultOK(null, null);
			} else {
				return HuishoucatResult.HuishoucatResultError("该手机号未注册！", null);
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return HuishoucatResult.HuishoucatResultError("未知错误！", null);
	}

	@Override
	public HuishoucatResult userLoginByTelephone(String telephone, String verificationCode) {
		String code = jedisClient.get("USER_LOGIN_CODE:" + telephone);
		if (verificationCode.equals(code)) {
			TUserExample userExample = new TUserExample();
			Criteria criteria = userExample.createCriteria();
			criteria.andTelephoneEqualTo(telephone).andStateEqualTo(1).andIsDeletedEqualTo(false);
			List<TUser> list = userMapper.selectByExample(userExample);
			if (list.size() == 1) {
				// 生成token
				String token = IDUtils.getUUID();
				jedisClient.set("USER_SESSION:" + token, JsonUtils.objectToJson(list.get(0)));
				// 设置Session的过期时间
				jedisClient.expire("USER_SESSION:" + token, SESSION_EXPIRE);
				// 把token返回
				return HuishoucatResult.HuishoucatResultOK(null, token);
			} else {
				return HuishoucatResult.HuishoucatResultError("登录失败！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("验证码错误！", null);
	}
}
