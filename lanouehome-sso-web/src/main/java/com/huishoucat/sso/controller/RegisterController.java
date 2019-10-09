package com.huishoucat.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.sso.service.IUserRegisterService;

/**
 * 用户注册Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 下午5:36:25
 * @version V1.0
 */
@Controller
public class RegisterController {

	@Autowired
	private IUserRegisterService userRegisterService;

	/**
	 * 发送用户注册短信验证码
	 * @param telephone
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/sso/user_register/send_sms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String SendUserLoginSMS(String telephone, String callback) {
		if (StringUtils.isNotBlank(telephone)) {
			String json = JsonUtils.objectToJson(userRegisterService.userRegister(telephone));
			if (StringUtils.isNotBlank(callback)) {
				return callback + "(" + json + ")";
			}
			return json;
		}
		return JsonUtils.objectToJson(HuishoucatResult.HuishoucatResultError("请填写完整信息！", null));
	}

	/**
	 * 用户注册激活
	 * @param user
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/sso/user_register/activate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String userLoginByTelephone(TUser user, String callback) {
		if (StringUtils.isNotBlank(user.getTelephone()) && StringUtils.isNotBlank(user.getVerificationCode())
				&& StringUtils.isNotBlank(user.getOpenid())) {
			String json = JsonUtils.objectToJson(userRegisterService.userActivate(user.getTelephone(), "123456",
					user.getOpenid(), user.getVerificationCode()));
			if (StringUtils.isNotBlank(callback)) {
				return callback + "(" + json + ")";
			}
			return json;
		}
		return JsonUtils.objectToJson(HuishoucatResult.HuishoucatResultError("请填写完整信息！", null));
	}
}
