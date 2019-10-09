package com.huishoucat.pay.listener;

import java.util.Date;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TUserPointsDetailsMapper;
import com.huishoucat.manager.mapper.TUserPointsMapper;
import com.huishoucat.manager.pojo.TUserPoints;
import com.huishoucat.manager.pojo.TUserPointsDetails;

/**
 * 监听用户积分余额变动并更新到数据库
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月23日 下午6:14:10
 * @version V1.0
 */
public class UserPointChangeMessageListener implements MessageListener {

	@Autowired
	private TUserPointsMapper userPointsMapper;
	@Autowired
	private TUserPointsDetailsMapper userPointsDetailsMapper;

	@Override
	public void onMessage(Message message) {
		try {
			// 从消息中取用户ID
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			TUserPointsDetails userPointsDetails = JsonUtils.jsonToPojo(text, TUserPointsDetails.class);
			if (userPointsDetails != null) {
				userPointsDetailsMapper.insertSelective(userPointsDetails);
				TUserPoints userPoints = userPointsMapper.selectByPrimaryKey(userPointsDetails.getUserId());
				if (userPoints != null) {
					TUserPoints newUserPoints = new TUserPoints();
					newUserPoints.setUserId(userPointsDetails.getUserId());
					newUserPoints.setUpdateTime(new Date());
					if (userPointsDetails.getType() == 1) {
						newUserPoints.setPoints(userPoints.getPoints() - userPointsDetails.getNum());
						newUserPoints.setTotalOutlay(userPoints.getTotalOutlay() + userPointsDetails.getNum());
					} else if (userPointsDetails.getType() == 2) {
						newUserPoints.setPoints(userPoints.getPoints() + userPointsDetails.getNum());
						newUserPoints.setTotalIncome(userPoints.getTotalIncome() + userPointsDetails.getNum());
					}
					userPointsMapper.updateByPrimaryKeySelective(newUserPoints);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
