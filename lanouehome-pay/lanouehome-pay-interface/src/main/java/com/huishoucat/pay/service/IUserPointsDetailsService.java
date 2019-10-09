package com.huishoucat.pay.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TUserPointsDetails;

/**
 * 用户绿色积分明细Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午11:56:48
 * @version V1.0
 */
public interface IUserPointsDetailsService {

	/**
	 * 获取用户绿色积分明细列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TUserPointsDetails> getUserPointsDetailsList(int pageNum, int limit, String sort, String order,
			String search);

	/**
	 * 获取用户积分交易明细
	 * @param userId
	 * @return
	 */
	public HuishoucatResult getUserPointsDetailsListByUserId(Long userId);
}
