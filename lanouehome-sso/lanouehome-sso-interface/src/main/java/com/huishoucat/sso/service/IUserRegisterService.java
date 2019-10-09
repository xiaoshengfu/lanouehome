package com.huishoucat.sso.service;

import com.huishoucat.common.pojo.HuishoucatResult;

/**
 * 注册Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月9日 下午8:22:43
 * @version V1.0
 */
public interface IUserRegisterService {

	/**
	 * 用户注册
	 * @param telephone
	 * @return
	 */
	public HuishoucatResult userRegister(String telephone);

	/**
	 * 用户激活
	 * @param telephone
	 * @param password
	 * @param openid
	 * @param verificationCode
	 * @return
	 */
	public HuishoucatResult userActivate(String telephone, String password, String openid, String verificationCode);
}
