package com.huishoucat.message.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TNotificationMapper;
import com.huishoucat.manager.pojo.TNotification;
import com.huishoucat.manager.pojo.TNotificationExample;
import com.huishoucat.manager.pojo.TNotificationExample.Criteria;
import com.huishoucat.messsage.service.INotificationService;

/**
 * 用户通知Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月21日 上午12:10:28
 * @version V1.0
 */
@Service
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	TNotificationMapper notificationMapper;

	@Override
	public PageBean<TNotification> getNotificationList(int pageNum, int limit, String sort, String order,
			String search) {
		PageHelper.startPage(pageNum, limit);
		TNotificationExample notificationExample = new TNotificationExample();
		notificationExample.setOrderByClause(sort + " " + order);
		Criteria criteria = notificationExample.createCriteria();
		criteria.andIsDeleteEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andUserIdEqualTo(Long.parseLong(search));
		}
		List<TNotification> list = notificationMapper.selectByExample(notificationExample);
		PageInfo<TNotification> pageInfo = new PageInfo<TNotification>(list);
		PageBean<TNotification> pageBean = new PageBean<TNotification>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult deleteNotificationByNotificationId(Long[] notificationIds) {
		TNotification notification = new TNotification();
		Date date = new Date();
		for (int i = 0; i < notificationIds.length; i++) {
			notification.setNotificationId(notificationIds[i]);
			notification.setIsDelete(true);
			notification.setUpdateTime(date);
			notificationMapper.updateByPrimaryKeySelective(notification);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult addNotification(TNotification notification) {
		Date date = new Date();
		notification.setCreateTime(date);
		notification.setUpdateTime(date);
		int i = notificationMapper.insertSelective(notification);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, notification);
		}
		return HuishoucatResult.HuishoucatResultOK("添加用户通知失败！", null);
	}

	@Override
	public HuishoucatResult findNotificationListByUserId(Long userId) {
		TNotificationExample notificationExample = new TNotificationExample();
		notificationExample.setOrderByClause("is_read asc,create_time desc");
		notificationExample.createCriteria().andIsDeleteEqualTo(false).andUserIdEqualTo(userId);
		List<TNotification> list = notificationMapper.selectByExample(notificationExample);
		return HuishoucatResult.HuishoucatResultOK(null, list);
	}

	@Override
	public HuishoucatResult countNotReadNotificationByUserId(Long userId) {
		TNotificationExample notificationExample = new TNotificationExample();
		notificationExample.createCriteria().andIsDeleteEqualTo(false).andUserIdEqualTo(userId).andIsReadEqualTo(false);
		return HuishoucatResult.HuishoucatResultOK(null, notificationMapper.countByExample(notificationExample));
	}

	@Override
	public HuishoucatResult userReadAllNotification(Long userId) {
		TNotificationExample notificationExample = new TNotificationExample();
		notificationExample.createCriteria().andIsDeleteEqualTo(false).andUserIdEqualTo(userId).andIsReadEqualTo(false);
		int i = notificationMapper.updateByExampleSelective(new TNotification(true, new Date()), notificationExample);
		if (i > 0) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("通知全部设为已读失败！", null);
	}

	@Override
	public HuishoucatResult userfindNotificationByNotificationId(Long notificationId) {
		TNotification notification = notificationMapper.selectByPrimaryKey(notificationId);
		if (notification != null) {
			if (!notification.getIsRead()) {
				notification.setIsRead(true);
				notification.setUpdateTime(new Date());
				notificationMapper.updateByPrimaryKeySelective(notification);
			}
			return HuishoucatResult.HuishoucatResultOK(null, notification);
		}
		return HuishoucatResult.HuishoucatResultError("无效通知ID！", null);
	}
}
