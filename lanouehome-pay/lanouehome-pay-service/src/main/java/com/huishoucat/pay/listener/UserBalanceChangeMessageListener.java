package com.huishoucat.pay.listener;

import java.util.Date;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TUserBalanceMapper;
import com.huishoucat.manager.mapper.TUserTransactionDetailsMapper;
import com.huishoucat.manager.pojo.TUserBalance;
import com.huishoucat.manager.pojo.TUserTransactionDetails;

/**
 * 监听用户余额变动并更新到数据库
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午6:14:10
 * @version V1.0
 */
public class UserBalanceChangeMessageListener implements MessageListener {

	@Autowired
	private TUserBalanceMapper userBalanceMapper;
	@Autowired
	private TUserTransactionDetailsMapper userTransactionDetailsMapper;

	@Override
	public void onMessage(Message message) {
		try {
			// 从消息中取用户ID
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			TUserTransactionDetails userTransactionDetails = JsonUtils.jsonToPojo(text, TUserTransactionDetails.class);
			if (userTransactionDetails != null) {
				userTransactionDetailsMapper.insertSelective(userTransactionDetails);
				TUserBalance userBalance = userBalanceMapper.selectByPrimaryKey(userTransactionDetails.getUserId());
				if (userBalance != null) {
					TUserBalance newUserBalance = new TUserBalance();
					newUserBalance.setUserId(userBalance.getUserId());
					newUserBalance.setUpdateTime(new Date());
					if (userTransactionDetails.getType() == 1) {
						newUserBalance.setBalance(userBalance.getBalance() - userTransactionDetails.getNum());
						newUserBalance.setTotalOutlay(userBalance.getTotalOutlay() + userTransactionDetails.getNum());
					} else if (userTransactionDetails.getType() == 2) {
						newUserBalance.setBalance(userBalance.getBalance() + userTransactionDetails.getNum());
						newUserBalance.setTotalIncome(userBalance.getTotalIncome() + userTransactionDetails.getNum());
					}
					userBalanceMapper.updateByPrimaryKeySelective(newUserBalance);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
