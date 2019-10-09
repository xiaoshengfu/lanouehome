package com.huishoucat.sso.service;

import com.huishoucat.common.pojo.HuishoucatResult;

/**
 * 回收员登录Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月10日 下午5:48:44
 * @version V1.0
 */
public interface IRecyclerLoginService {

	/**
	 * 回收员通过账号密码登录
	 * @param telephone
	 * @param password
	 * @return
	 */
	public HuishoucatResult recyclerLoginByPassword(String telephone, String password);

	/**
	 * 回收员通过微信号登录
	 * @param openid
	 * @return
	 */
	public HuishoucatResult recyclerLoginByOpenid(String openid);

	/**
	 * 回收员通过手机号登录 
	 * @param telephone
	 * @param verificationCode
	 * @return
	 */
	public HuishoucatResult recyclerLoginByTelephone(String telephone, String verificationCode);

	/**
	 * 发送回收员手机号登录验证码
	 * @param telephone
	 * @return
	 */
	public HuishoucatResult sendRecyclerLoginSMSCode(String telephone);
}
