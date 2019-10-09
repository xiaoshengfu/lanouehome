package com.huishoucat.ucenter.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.manager.mapper.TUserAddressMapper;
import com.huishoucat.manager.pojo.TUserAddress;
import com.huishoucat.manager.pojo.TUserAddressExample;
import com.huishoucat.ucenter.service.IUserAddressService;

/**
 * 用户地址管理Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月6日 下午1:57:32
 * @version V1.0
 */
@Service
public class UserAddressServiceImpl implements IUserAddressService {

	@Autowired
	private TUserAddressMapper userAddressMapper;

	@Override
	public HuishoucatResult updateUserAddress(TUserAddress userAddress) {
		TUserAddress oldUserAddress = userAddressMapper.selectByPrimaryKey(userAddress.getUserAddressId());
		if (oldUserAddress.getUserId().equals(userAddress.getUserId()) && !oldUserAddress.getIsDeleted()) {
			oldUserAddress.setIsDeleted(true);
			int i = userAddressMapper.updateByPrimaryKey(oldUserAddress);
			if (i == 1) {
				Date date = new Date();
				if (userAddress.getState() == 2) {
					TUserAddressExample example = new TUserAddressExample();
					example.createCriteria().andUserIdEqualTo(userAddress.getUserId()).andStateEqualTo(2);
					List<TUserAddress> list = userAddressMapper.selectByExample(example);
					if (list.size() > 0) {
						list.get(0).setState(1);
						userAddressMapper.updateByPrimaryKeySelective(list.get(0));
					}
				}
				userAddress.setCreateTime(date);
				userAddress.setUpdateTime(date);
				userAddress.setUserAddressId(null);
				int j = userAddressMapper.insertSelective(userAddress);
				if (j == 1) {
					return HuishoucatResult.HuishoucatResultOK(null, userAddress);
				}
			}
		}
		return HuishoucatResult.HuishoucatResultError("修改地址信息失败！", null);
	}

	@Override
	public HuishoucatResult addUserAddress(TUserAddress userAddress) {
		Date date = new Date();
		userAddress.setCreateTime(date);
		userAddress.setUpdateTime(date);
		TUserAddressExample example = new TUserAddressExample();
		example.createCriteria().andUserIdEqualTo(userAddress.getUserId()).andStateEqualTo(2);
		List<TUserAddress> list = userAddressMapper.selectByExample(example);
		if (userAddress.getState() == 2) {
			if (list.size() != 0) {
				list.get(0).setState(1);
				list.get(0).setUpdateTime(date);
				userAddressMapper.updateByPrimaryKeySelective(list.get(0));
			}
		} else {
			if (list.size() == 0) {
				userAddress.setState(2);
			}
		}
		int i = userAddressMapper.insertSelective(userAddress);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, userAddress);
		}
		return HuishoucatResult.HuishoucatResultError("添加地址信息失败！", null);
	}

	@Override
	public HuishoucatResult findUserAddressByUserAddressId(Long userAddressId) {
		TUserAddress userAddress = userAddressMapper.selectByPrimaryKey(userAddressId);
		if (userAddress != null) {
			return HuishoucatResult.HuishoucatResultOK(null, userAddress);
		}
		return HuishoucatResult.HuishoucatResultError("无效用户地址ID！", null);
	}

	@Override
	public HuishoucatResult findUserAddressListByUserId(Long userId) {
		TUserAddressExample example = new TUserAddressExample();
		example.createCriteria().andUserIdEqualTo(userId).andIsDeletedEqualTo(false);
		return HuishoucatResult.HuishoucatResultOK(null, userAddressMapper.selectByExample(example));
	}

	@Override
	public HuishoucatResult setDefaultUserAddress(Long userId, Long userAddressId) {
		TUserAddress userAddress = userAddressMapper.selectByPrimaryKey(userAddressId);
		if (userAddress.getUserId().equals(userId) && !userAddress.getIsDeleted()) {
			TUserAddressExample example = new TUserAddressExample();
			example.createCriteria().andUserIdEqualTo(userId).andIsDeletedEqualTo(false).andStateEqualTo(2);
			List<TUserAddress> list = userAddressMapper.selectByExample(example);
			if (list.size() == 1) {
				TUserAddress newUserAddress = new TUserAddress();
				newUserAddress.setUpdateTime(new Date());
				newUserAddress.setUserAddressId(list.get(0).getUserAddressId());
				newUserAddress.setState(1);
				int i = userAddressMapper.updateByPrimaryKeySelective(newUserAddress);
				if (i == 1) {
					newUserAddress.setUpdateTime(new Date());
					newUserAddress.setUserAddressId(userAddressId);
					newUserAddress.setState(2);
					int j = userAddressMapper.updateByPrimaryKeySelective(newUserAddress);
					if (j == 1) {
						return HuishoucatResult.HuishoucatResultOK(null, null);
					}
				}
			}
		}
		return HuishoucatResult.HuishoucatResultError("修改失败！", null);
	}

	@Override
	public HuishoucatResult findDefaultUserAddress(Long userId) {
		TUserAddressExample example = new TUserAddressExample();
		example.createCriteria().andUserIdEqualTo(userId).andIsDeletedEqualTo(false).andStateEqualTo(2);
		List<TUserAddress> list = userAddressMapper.selectByExample(example);
		if (list.size() > 0) {
			return HuishoucatResult.HuishoucatResultOK(null, list.get(0));
		}
		return HuishoucatResult.HuishoucatResultError("用户未设置默认地址信息！", null);
	}

	@Override
	public HuishoucatResult deleteUserAddress(Long userAddressId) {
		TUserAddressExample example = new TUserAddressExample();
		example.createCriteria().andStateEqualTo(1);
		TUserAddress userAddress = new TUserAddress();
		userAddress.setUserAddressId(userAddressId);
		userAddress.setIsDeleted(true);
		int i = userAddressMapper.updateByExampleSelective(userAddress, example);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("不能删除默认地址！", null);
	}
}
