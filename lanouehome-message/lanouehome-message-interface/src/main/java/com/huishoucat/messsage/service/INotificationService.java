package com.huishoucat.messsage.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TNotification;

/**
 * 用户通知Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月20日 下午11:23:52
 * @version V1.0
 */
public interface INotificationService {

	/**
	 * 获取用户通知列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TNotification> getNotificationList(int pageNum, int limit, String sort, String order,
			String search);

	/**
	 * 添加用户通知信息
	 * @param notification
	 * @return
	 */
	public HuishoucatResult addNotification(TNotification notification);

	/**
	 * 批量删除用户通知信息
	 * @param notificationIds
	 * @return
	 */
	public HuishoucatResult deleteNotificationByNotificationId(Long[] notificationIds);

	/**
	 * 通过用户ID获取用户通知列表(按照未读已读顺序排列)
	 * @param userId
	 * @return
	 */
	public HuishoucatResult findNotificationListByUserId(Long userId);

	/**
	 * 通过用户ID获取用户未读通知数量
	 * @param userId
	 * @return
	 */
	public HuishoucatResult countNotReadNotificationByUserId(Long userId);

	/**
	 * 用户系统消息全部标为已读
	 * @param userId
	 * @return
	 */
	public HuishoucatResult userReadAllNotification(Long userId);

	/**
	 * 用户根据系统通知ID查看通知详情
	 * @param notificationId
	 * @return
	 */
	public HuishoucatResult userfindNotificationByNotificationId(Long notificationId);
}
