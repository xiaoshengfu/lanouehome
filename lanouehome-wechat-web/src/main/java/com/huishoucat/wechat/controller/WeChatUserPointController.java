package com.huishoucat.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.pay.service.IUserPointsDetailsService;
import com.huishoucat.pay.service.IUserPointsService;

/**
 * 用户绿色积分Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 上午8:10:21
 * @version V1.0
 */
@Controller
public class WeChatUserPointController {
	
	@Autowired
	private IUserPointsService userPointsService;
	@Autowired
	private IUserPointsDetailsService userPointsDetailsService;

	/**
	 * 展示用户绿色积分信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/user/point")
	public String showUserPointsPage(HttpServletRequest request, Model model) {
		TUser user = (TUser) request.getAttribute("loginUser");
		model.addAttribute("userPoint", userPointsService.findUserPointsByUserId(user.getUserId()).getData());
		return "user_point";
	}

	/**
	 * 展示用户绿色积分明细
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/user/point/details/list")
	public String showUserPointsDetails(HttpServletRequest request, Model model) {
		TUser user = (TUser) request.getAttribute("loginUser");
		model.addAttribute("userPointsDetailsList",
				userPointsDetailsService.getUserPointsDetailsListByUserId(user.getUserId()).getData());
		return "user_point_details";
	}
}
