package com.huishoucat.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.pay.service.IUserBalanceService;
import com.huishoucat.pay.service.IUserTransactionDetailsService;

/**
 * 用户余额Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 上午8:10:21
 * @version V1.0
 */
@Controller
public class WeChatUserBalanceController {

	@Autowired
	private IUserBalanceService userBalanceService;
	@Autowired
	private IUserTransactionDetailsService userTransactionDetailsService;

	/**
	 * 获取用户余额
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/user/balance")
	public String showUserBalancePage(HttpServletRequest request, Model model) {
		TUser user = (TUser) request.getAttribute("loginUser");
		model.addAttribute("userBalance", userBalanceService.findUserBalanceByUserId(user.getUserId()).getData());
		return "user_balance";
	}

	/**
	 * 获取用户余额交易明细
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/user/balance/details/list")
	public String showUserBalanceDetails(HttpServletRequest request, Model model) {
		TUser user = (TUser) request.getAttribute("loginUser");
		model.addAttribute("userBalanceDetailsList",
				userTransactionDetailsService.getUserTransactionDetailsListByUserId(user.getUserId()).getData());
		return "user_balance_details";
	}

	/**
	 * 用户余额提现页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/user/balance/withdrawals/page")
	public String userWithdrawalsPage(HttpServletRequest request, Model model) {
		TUser user = (TUser) request.getAttribute("loginUser");
		model.addAttribute("userBalance", userBalanceService.findUserBalanceByUserId(user.getUserId()).getData());
		return "balance_withdrawals";
	}

	/**
	 * 用户余额提现
	 * @param request
	 * @param model
	 * @param money
	 * @return
	 */
	@RequestMapping("/wechat/user/balance/withdrawals")
	public String userWithdrawals(HttpServletRequest request, Model model, Long money) {
		if (money != null) {
			TUser user = (TUser) request.getAttribute("loginUser");
			userBalanceService.updateUserBalance(user.getUserId(), money, 1, 2);
			model.addAttribute("money", money);
		}
		return "withdrawals_success";
	}
}
