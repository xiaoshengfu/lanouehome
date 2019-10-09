package com.huishoucat.pay.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TUserBalance;

/**
 * 用户余额Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月21日 下午8:45:33
 * @version V1.0
 */
public interface IUserBalanceService {

	/**
	 * 获取用户余额列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TUserBalance> getUserBalanceList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新用户余额总支出，总收入
	 * @param userId 用户ID
	 * @param money 金额（分）
	 * @param type 类型:1支出,2收入
	 * @param source 交易来源:1平台支付用户订单,2用户余额提现,3用户余额购买积分商品
	 * @return
	 */
	public HuishoucatResult updateUserBalance(Long userId, Long money, Integer type, Integer source);
	
	/**
	 * 通过用户ID查找用户余额
	 * @param userId
	 * @return
	 */
	public HuishoucatResult findUserBalanceByUserId(Long userId);
}
