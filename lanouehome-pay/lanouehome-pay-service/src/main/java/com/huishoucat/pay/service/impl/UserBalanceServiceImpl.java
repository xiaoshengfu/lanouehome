package com.huishoucat.pay.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatMessage;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TUserBalanceMapper;
import com.huishoucat.manager.mapper.TUserTransactionDetailsMapper;
import com.huishoucat.manager.pojo.TUserBalance;
import com.huishoucat.manager.pojo.TUserBalanceExample;
import com.huishoucat.manager.pojo.TUserBalanceExample.Criteria;
import com.huishoucat.manager.pojo.TUserTransactionDetails;
import com.huishoucat.pay.service.IUserBalanceService;

/**
 * 用户余额Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月21日 下午8:53:33
 * @version V1.0
 */
@Service
public class UserBalanceServiceImpl implements IUserBalanceService {

	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination userMessageQueueDestination;
	@Autowired
	private TUserBalanceMapper userBalanceMapper;
	@Autowired
	private TUserTransactionDetailsMapper userTransactionDetailsMapper;

	@Override
	public PageBean<TUserBalance> getUserBalanceList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TUserBalanceExample userBalanceExample = new TUserBalanceExample();
		userBalanceExample.setOrderByClause(sort + " " + order);
		Criteria criteria = userBalanceExample.createCriteria();
		if (StringUtils.isNotBlank(search)) {
			criteria.andUserIdEqualTo(Long.parseLong(search));
		}
		List<TUserBalance> list = userBalanceMapper.selectByExample(userBalanceExample);
		PageInfo<TUserBalance> pageInfo = new PageInfo<TUserBalance>(list);
		PageBean<TUserBalance> pageBean = new PageBean<TUserBalance>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult findUserBalanceByUserId(Long userId) {
		TUserBalance userBalance = userBalanceMapper.selectByPrimaryKey(userId);
		if (userBalance != null) {
			return HuishoucatResult.HuishoucatResultOK(null, userBalance);
		}
		return HuishoucatResult.HuishoucatResultError("无效用户ID！", null);
	}

	@Override
	public HuishoucatResult updateUserBalance(Long userId, Long money, Integer type, Integer source) {
		TUserBalance userBalance = userBalanceMapper.selectByPrimaryKey(userId);
		if (userBalance != null) {
			if (type != null && type.equals(1) && userBalance.getBalance() < money) {
				return HuishoucatResult.HuishoucatResultError("账户余额不足！", null);
			}
			TUserBalance newUserBalance = new TUserBalance();
			newUserBalance.setUserId(userId);
			newUserBalance.setUpdateTime(new Date());
			if (type.equals(1)) {
				newUserBalance.setTotalOutlay(userBalance.getTotalOutlay() + money);
				newUserBalance.setBalance(userBalance.getBalance() - money);
			} else if (type.equals(2)) {
				newUserBalance.setTotalIncome(userBalance.getTotalIncome() + money);
				newUserBalance.setBalance(userBalance.getBalance() + money);
			} else {
				return HuishoucatResult.HuishoucatResultError("无效类型！", null);
			}
			int i = userBalanceMapper.updateByPrimaryKeySelective(newUserBalance);
			if (i == 1) {
				TUserTransactionDetails userTransactionDetails = new TUserTransactionDetails(userId, source, type,
						money, new Date());
				int j = userTransactionDetailsMapper.insertSelective(userTransactionDetails);
				if (j == 1) {
					String message = null;
					if (source == 1) {
						message = "您的废品订单所获账户余额已到帐，本次余额+" + money / 100.0 + "元，剩余余额"
								+ newUserBalance.getBalance() / 100.0 + "元";
					} else if (source == 2) {
						message = "您提现的余额已到帐，本次余额-" + money / 100.0 + "元，剩余余额" + newUserBalance.getBalance() / 100.0
								+ "元";
					} else if (source == 3) {
						message = "您成功购买积分商城商品，本次余额-" + money / 100.0 + "元，剩余余额" + newUserBalance.getBalance() / 100.0
								+ "元";
					}
					if (StringUtils.isNotBlank(message)) {
						final String userMessage = message;
						jmsTemplate.send(userMessageQueueDestination, new MessageCreator() {
							@Override
							public Message createMessage(Session session) throws JMSException {
								TextMessage textMessage = session.createTextMessage(
										JsonUtils.objectToJson(new HuishoucatMessage(userId, 1, userMessage)));
								return textMessage;
							}
						});
					}
					return HuishoucatResult.HuishoucatResultOK(null, newUserBalance);
				} else {
					return HuishoucatResult.HuishoucatResultError("操作失败！", null);
				}
			}
		}
		return HuishoucatResult.HuishoucatResultError("无效用户ID！", null);
	}
}
