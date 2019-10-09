package com.huishoucat.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.MyStringUtils;
import com.huishoucat.manager.pojo.TNotification;
import com.huishoucat.messsage.service.INotificationService;
import com.huishoucat.ucenter.service.IUserService;

/**
 * 消息管理Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午10:30:16
 * @version V1.0
 */
@Controller
public class MessageController {

	@Autowired
	private INotificationService notificationService;
	@Autowired
	private IUserService userService;

	/**
	 * 查询用户通知列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/message/notification/list")
	@ResponseBody
	public PageBean<TNotification> getNotificationList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search,
			HttpServletRequest request) {
		return notificationService.getNotificationList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort),
				order, search);
	}

	/**
	 * 删除用户通知
	 * @param notificationIds
	 * @return
	 */
	@RequestMapping("/message/notification/delete")
	@ResponseBody
	public HuishoucatResult deleteNotification(@RequestBody Long[] notificationIds) {
		if (notificationIds != null) {
			return notificationService.deleteNotificationByNotificationId(notificationIds);
		}
		return HuishoucatResult.HuishoucatResultError("通知ID不能为空！", null);
	}

	/**
	 * 添加用户通知
	 * @param notification
	 * @return
	 */
	@RequestMapping("/message/notification/add")
	@ResponseBody
	public HuishoucatResult addRecycler(TNotification notification) {
		if (notification != null) {
			if (notification.getUserId() != null && StringUtils.isNotBlank(notification.getNotificationContent())
					&& notification.getLevel() != null) {
				if (userService.findUsersByUserId(notification.getUserId())
						.getStateCode() != HuishoucatResult.SUCCESS) {
					return HuishoucatResult.HuishoucatResultError("无效用户ID！", null);
				}
				notificationService.addNotification(notification);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整的信息！", null);
	}
}
