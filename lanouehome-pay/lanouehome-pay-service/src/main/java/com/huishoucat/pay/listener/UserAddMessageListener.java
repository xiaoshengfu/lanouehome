package com.huishoucat.pay.listener;

import java.util.Date;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.huishoucat.manager.mapper.TUserBalanceMapper;
import com.huishoucat.manager.mapper.TUserPointsMapper;
import com.huishoucat.manager.pojo.TUserBalance;
import com.huishoucat.manager.pojo.TUserPoints;

/**
 * 监听用户添加消息，接收消息后，将对应的用户余额添加到数据库
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午6:14:10
 * @version V1.0
 */
public class UserAddMessageListener implements MessageListener {

	@Autowired
	private TUserBalanceMapper userBalanceMapper;
	@Autowired
	private TUserPointsMapper userPointsMapper;

	@Override
	public void onMessage(Message message) {
		try {
			// 从消息中取用户ID
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long userId = new Long(text);
			// 等待事务提交
			Thread.sleep(500);
			Date date = new Date();
			TUserBalance userBalance = new TUserBalance(userId, date, date);
			TUserPoints userPoints = new TUserPoints(userId, date, date);
			userBalanceMapper.insertSelective(userBalance);
			userPointsMapper.insertSelective(userPoints);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
