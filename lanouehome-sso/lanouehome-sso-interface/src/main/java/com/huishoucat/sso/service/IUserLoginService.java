package com.huishoucat.sso.service;

import com.huishoucat.common.pojo.HuishoucatResult;

/**
 * 用户登录Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月9日 下午8:21:45
 * @version V1.0
 */
public interface IUserLoginService {

	/**
	 * 用户通过账号密码登录
	 * @param telephone
	 * @param password
	 * @return
	 */
	public HuishoucatResult userLoginByPassword(String telephone, String password);
	
	/**
	 * 用户通过微信号登录
	 * @param openid
	 * @return
	 */
	public HuishoucatResult userLoginByOpenid(String openid);
	
	/**
	 * 发送用户手机号登录验证码
	 * @param telephone
	 * @return
	 */
	public HuishoucatResult sendUserLoginSMSCode(String telephone);

	/**
	 * 用户通过手机号登录 
	 * @param telephone
	 * @param verificationCode
	 * @return
	 */
	public HuishoucatResult userLoginByTelephone(String telephone, String verificationCode);
}
