package com.huishoucat.pay.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TUserTransactionDetails;

/**
 * 用户余额交易明细Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午9:58:40
 * @version V1.0
 */
public interface IUserTransactionDetailsService {

	/**
	 * 获取用户余额交易明细列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TUserTransactionDetails> getUserTransactionDetailsList(int pageNum, int limit, String sort,
			String order, String search);

	/**
	 * 通过用户ID获取用户余额交易明细
	 * @param userId
	 * @return
	 */
	public HuishoucatResult getUserTransactionDetailsListByUserId(Long userId);
}
