package com.huishoucat.ucenter.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TUser;

/**
 * 用户Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月9日 下午8:55:05
 * @version V1.0
 */
public interface IUserService {
	
	/**
	 * 获取用户列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TUser> getUserList(int pageNum, int limit, String sort, String order, String search);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public HuishoucatResult updateUser(TUser user);
	
	/**
	 * 批量删除用户信息
	 * @param userIds
	 * @return
	 */
	public HuishoucatResult deleteUsersByUserId(Long[] userIds);
	
	/**
	 * 通过用户ID查找用户
	 * @param userId
	 * @return
	 */
	public HuishoucatResult findUsersByUserId(Long userId);
}
