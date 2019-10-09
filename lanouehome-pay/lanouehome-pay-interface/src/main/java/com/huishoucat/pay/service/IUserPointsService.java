package com.huishoucat.pay.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TUserPoints;

/**
 * 用户绿色积分Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午11:15:17
 * @version V1.0
 */
public interface IUserPointsService {

	/**
	 * 获取用户绿色积分列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TUserPoints> getUserPointsList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新用户绿色积分总支出，总收入
	 * @param userId 用户ID
	 * @param income 收入积分数量
	 * @param outlay 支出积分数量
	 * @return
	 */
	public HuishoucatResult updateUserPoints(Long userId, Long income, Long outlay);

	/**
	 * 通过用户ID查找用户绿色积分
	 * @param userId
	 * @return
	 */
	public HuishoucatResult findUserPointsByUserId(Long userId);
}
