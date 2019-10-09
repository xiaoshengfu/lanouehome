package com.huishoucat.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.MyStringUtils;
import com.huishoucat.manager.pojo.TUserBalance;
import com.huishoucat.manager.pojo.TUserPoints;
import com.huishoucat.manager.pojo.TUserPointsDetails;
import com.huishoucat.manager.pojo.TUserTransactionDetails;
import com.huishoucat.pay.service.IUserBalanceService;
import com.huishoucat.pay.service.IUserPointsDetailsService;
import com.huishoucat.pay.service.IUserPointsService;
import com.huishoucat.pay.service.IUserTransactionDetailsService;

/**
 * 支付管理Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午9:38:00
 * @version V1.0
 */
@Controller
public class PayController {

	@Autowired
	private IUserBalanceService userBalanceService;
	@Autowired
	private IUserTransactionDetailsService userTransactionDetailsService;
	@Autowired
	private IUserPointsService userPointsService;
	@Autowired
	private IUserPointsDetailsService userPointsDetailsService;

	/**
	 * 查询用户余额信息列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/pay/user_balance/list")
	@ResponseBody
	public PageBean<TUserBalance> getUserBalanceList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return userBalanceService.getUserBalanceList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort),
				order, search);
	}

	/**
	 * 查询用户余额交易明细
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/pay/user_transaction_details/list")
	@ResponseBody
	public PageBean<TUserTransactionDetails> getUserTransactionDetailsList(
			@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return userTransactionDetailsService.getUserTransactionDetailsList(offset / limit + 1, limit,
				MyStringUtils.underscoreminName(sort), order, search);
	}

	/**
	 * 查询用户绿色积分列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/pay/user_points/list")
	@ResponseBody
	public PageBean<TUserPoints> getUserPointsList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return userPointsService.getUserPointsList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort),
				order, search);
	}

	/**
	 * 查询用户余额交易明细
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/pay/user_points_details/list")
	@ResponseBody
	public PageBean<TUserPointsDetails> getUserPointsDetailsList(
			@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return userPointsDetailsService.getUserPointsDetailsList(offset / limit + 1, limit,
				MyStringUtils.underscoreminName(sort), order, search);
	}
}
