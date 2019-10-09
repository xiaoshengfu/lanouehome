package com.huishoucat.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.pojo.TRecycler;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.sso.service.IRecyclerLoginService;
import com.huishoucat.sso.service.IUserLoginService;

/**
 * 单点登录Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 下午5:36:25
 * @version V1.0
 */
@Controller
public class LoginController {

	@Autowired
	private IUserLoginService userLoginService;
	@Autowired
	private IRecyclerLoginService recyclerLoginService;

	/**
	 * 回收员密码登录
	 * @param telephone
	 * @param password
	 * @return
	 */
	@RequestMapping("/sso/recycler_login/password")
	@ResponseBody
	public HuishoucatResult recyclerLoginByPassword(String telephone, String password) {
		if (StringUtils.isNotBlank(telephone) && StringUtils.isNotBlank(password)) {
			return recyclerLoginService.recyclerLoginByPassword(telephone, password);
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整信息！", null);
	}

	/**
	 * 回收员微信登录
	 * @param openid
	 * @return
	 */
	@RequestMapping("/sso/recycler_login/openid")
	@ResponseBody
	public HuishoucatResult recyclerLoginByOpenid(String openid) {
		if (StringUtils.isNotBlank(openid)) {
			return recyclerLoginService.recyclerLoginByOpenid(openid);
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整信息！", null);
	}

	/**
	 * 回收员手机号登录
	 * @param telephone
	 * @param verificationCode
	 * @return
	 */
	@RequestMapping("/sso/recycler_login/telephone")
	@ResponseBody
	public HuishoucatResult recyclerLoginByTelephone(@RequestBody TRecycler recycler) {
		if (StringUtils.isNotBlank(recycler.getTelephone()) && StringUtils.isNotBlank(recycler.getVerificationCode())) {
			return recyclerLoginService.recyclerLoginByTelephone(recycler.getTelephone(),
					recycler.getVerificationCode());
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整信息！", null);
	}

	/**
	 * 发送回收员登录短信验证码
	 * @param telephone
	 * @return
	 */
	@RequestMapping("/sso/recycler_login/send_sms")
	@ResponseBody
	public HuishoucatResult SendRecyclerLoginSMS(String telephone) {
		if (StringUtils.isNotBlank(telephone)) {
			return recyclerLoginService.sendRecyclerLoginSMSCode(telephone);
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整信息！", null);
	}

	/**
	 * 发送用户登录短信验证码
	 * @param telephone
	 * @return
	 */
	@RequestMapping(value = "/sso/user_login/send_sms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String SendUserLoginSMS(String telephone, String callback) {
		if (StringUtils.isNotBlank(telephone)) {
			String json = JsonUtils.objectToJson(userLoginService.sendUserLoginSMSCode(telephone));
			if (StringUtils.isNotBlank(callback)) {
				return callback + "(" + json + ")";
			}
			return json;
		}
		return JsonUtils.objectToJson(HuishoucatResult.HuishoucatResultError("请填写完整信息！", null));
	}

	/**
	 * 用户手机号登录
	 * @param telephone
	 * @return
	 */
	@RequestMapping(value = "/sso/user_login/telephone", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String userLoginByTelephone(TUser user, String callback) {
		if (StringUtils.isNotBlank(user.getTelephone()) && StringUtils.isNotBlank(user.getVerificationCode())) {
			String json = JsonUtils.objectToJson(
					userLoginService.userLoginByTelephone(user.getTelephone(), user.getVerificationCode()));
			if (StringUtils.isNotBlank(callback)) {
				return callback + "(" + json + ")";
			}
			return json;
		}
		return JsonUtils.objectToJson(HuishoucatResult.HuishoucatResultError("请填写完整信息！", null));
	}

	/**
	 * 用户密码登录
	 * @param telephone
	 * @param password
	 * @return
	 */
	@RequestMapping("/sso/user_login/password")
	@ResponseBody
	public HuishoucatResult userLoginByPassword(@RequestBody TUser user) {
		if (StringUtils.isNotBlank(user.getTelephone()) && StringUtils.isNotBlank(user.getPassword())) {
			return userLoginService.userLoginByPassword(user.getTelephone(), user.getPassword());
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整信息！", null);
	}
}
