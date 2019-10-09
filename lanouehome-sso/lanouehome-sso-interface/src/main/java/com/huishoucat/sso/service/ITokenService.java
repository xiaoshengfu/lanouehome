package com.huishoucat.sso.service;

import com.huishoucat.common.pojo.HuishoucatResult;

/**
 * Token操作Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 下午4:56:46
 * @version V1.0
 */
public interface ITokenService {

	/**
	 * 根据token取用户信息
	 * @param token
	 * @return
	 */
	public HuishoucatResult getUserByToken(String token);

	/**
	 * 根据token取回收员信息
	 * @param token
	 * @return
	 */
	public HuishoucatResult getRecyclerByToken(String token);
}
