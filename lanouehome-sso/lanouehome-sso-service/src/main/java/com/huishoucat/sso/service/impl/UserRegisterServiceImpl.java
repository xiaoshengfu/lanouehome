package com.huishoucat.sso.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
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
import com.huishoucat.sso.service.IUserRegisterService;

/**
 * 注册Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月9日 下午8:21:04
 * @version V1.0
 */
@Service
public class UserRegisterServiceImpl implements IUserRegisterService {

	@Value("${CODE_CACHE_EXPIRE}")
	private Integer CODE_CACHE_EXPIRE;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	@Value("${SIGN_NAME}")
	private String SIGN_NAME;
	@Value("${TEMPLATE_CODE}")
	private String TEMPLATE_CODE;

	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination topicDestination;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;

	@Override
	public HuishoucatResult userRegister(String telephone) {
		try {
			TUserExample userExample = new TUserExample();
			userExample.createCriteria().andTelephoneEqualTo(telephone);
			int i = userMapper.countByExample(userExample);
			if (i == 0) {
				String code = IDUtils.getVerificationCode();
				jedisClient.set("USER_REGISTER_CODE:" + telephone, code);
				// 设置过期时间
				jedisClient.expire("USER_REGISTER_CODE:" + telephone, CODE_CACHE_EXPIRE);
				SMSUtils.sendSMSVerificationCode(telephone, SIGN_NAME, TEMPLATE_CODE, code);
				return HuishoucatResult.HuishoucatResultOK(null, null);
			} else {
				return HuishoucatResult.HuishoucatResultError("该手机号码已注册！", null);
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return HuishoucatResult.HuishoucatResultError("未知错误！", null);
	}

	@Override
	public HuishoucatResult userActivate(String telephone, String password, String openid, String verificationCode) {
		String code = jedisClient.get("USER_REGISTER_CODE:" + telephone);
		if (verificationCode.equals(code)) {
			TUser user = new TUser();
			user.setTelephone(telephone);
			user.setPassword(MD5Utils.md5("h" + password + "s"));
			user.setOpenid(openid);
			Date date = new Date();
			user.setState(1);
			user.setCreateTime(date);
			user.setUpdateTime(date);
			int i = userMapper.insertSelective(user);
			if (i == 1) {
				jmsTemplate.send(topicDestination, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage textMessage = session.createTextMessage(user.getUserId() + "");
						return textMessage;
					}
				});
				String token = UUID.randomUUID().toString();
				// 密码置为null
				user.setPassword(null);
				jedisClient.set("USER_SESSION:" + token, JsonUtils.objectToJson(user));
				// 设置Session的过期时间
				jedisClient.expire("USER_SESSION:" + token, SESSION_EXPIRE);
				// 把token返回
				return HuishoucatResult.HuishoucatResultOK(null, token);
			} else {
				return HuishoucatResult.HuishoucatResultError("未知错误！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("验证码输入错误！", null);
	}
}
