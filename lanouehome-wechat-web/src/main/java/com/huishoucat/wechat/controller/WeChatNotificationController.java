package com.huishoucat.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.messsage.service.INotificationService;

/**
 * 系统消息Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 上午8:10:21
 * @version V1.0
 */
@Controller
public class WeChatNotificationController {

	@Autowired
	private INotificationService notificationService;

	/**
	 * 用户系统消息全部标为已读
	 * @param request
	 * @return
	 */
	@RequestMapping("/wechat/user/notification/read_all")
	@ResponseBody
	public HuishoucatResult readAllNotification(HttpServletRequest request) {
		TUser user = (TUser) request.getAttribute("loginUser");
		return notificationService.userReadAllNotification(user.getUserId());
	}

	/**
	 * 用户查看通知详情
	 * @param notificationId
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/user/notification/details/{notificationId}")
	public String findNotificationDetails(@PathVariable Long notificationId, Model model) {
		if (notificationId != null) {
			model.addAttribute("notification",
					notificationService.userfindNotificationByNotificationId(notificationId).getData());
		}
		return "notification_details";
	}
}
