package com.huishoucat.message.listener;

import java.util.Date;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.huishoucat.common.pojo.HuishoucatMessage;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TNotificationMapper;
import com.huishoucat.manager.pojo.TNotification;

/**
 * 监听用户添加消息，接收消息后，将对应的用户余额信息添加到数据库
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午6:14:10
 * @version V1.0
 */
public class SendNotificationMessageListener implements MessageListener {

	@Autowired
	TNotificationMapper notificationMapper;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			HuishoucatMessage huishoucatMessage = JsonUtils.jsonToPojo(text, HuishoucatMessage.class);
			TNotification notification = new TNotification();
			Date date = new Date();
			notification.setUserId(huishoucatMessage.getUserId());
			notification.setNotificationContent(huishoucatMessage.getMessage());
			notification.setLevel(huishoucatMessage.getLevel());
			notification.setCreateTime(date);
			notification.setUpdateTime(date);
			notificationMapper.insertSelective(notification);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
