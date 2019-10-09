package com.huishoucat.ucenter.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.manager.pojo.TUserAddress;

/**
 * 用户地址管理Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月13日 上午10:35:32
 * @version V1.0
 */
public interface IUserAddressService {

	/**
	 * 更新用户地址信息
	 * @param userAddress
	 * @return
	 */
	public HuishoucatResult updateUserAddress(TUserAddress userAddress);

	/**
	 * 添加用户地址
	 * @param userAddress
	 * @return
	 */
	public HuishoucatResult addUserAddress(TUserAddress userAddress);

	/**
	 * 通过用户地址ID查找用户地址信息
	 * @param userAddressId
	 * @return
	 */
	public HuishoucatResult findUserAddressByUserAddressId(Long userAddressId);

	/**
	 * 通过用户ID查找用户地址信息
	 * @param userId
	 * @return
	 */
	public HuishoucatResult findUserAddressListByUserId(Long userId);

	/**
	 * 设置用户默认上门回收地址信息
	 * @param userId
	 * @param userAddressId
	 * @return
	 */
	public HuishoucatResult setDefaultUserAddress(Long userId, Long userAddressId);

	/**
	 * 查找用户默认上门回收地址信息
	 * @param userId
	 * @return
	 */
	public HuishoucatResult findDefaultUserAddress(Long userId);

	/**
	 * 用户删除上门地址
	 * @param userAddressId
	 * @return
	 */
	public HuishoucatResult deleteUserAddress(Long userAddressId);
}