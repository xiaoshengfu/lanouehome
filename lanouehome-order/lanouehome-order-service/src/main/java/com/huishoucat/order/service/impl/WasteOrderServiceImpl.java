package com.huishoucat.order.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.huishoucat.common.utils.DateUtils;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TWasteOrderItemMapper;
import com.huishoucat.manager.mapper.TWasteOrderMapper;
import com.huishoucat.manager.pojo.TUserPointsDetails;
import com.huishoucat.manager.pojo.TUserTransactionDetails;
import com.huishoucat.manager.pojo.TWasteOrder;
import com.huishoucat.manager.pojo.TWasteOrderExample;
import com.huishoucat.manager.pojo.TWasteOrderExample.Criteria;
import com.huishoucat.manager.pojo.TWasteOrderItem;
import com.huishoucat.order.service.IWasteOrderService;

/**
 * 废品订单Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月19日 下午9:35:01
 * @version V1.0
 */
@Service
public class WasteOrderServiceImpl implements IWasteOrderService {

	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination balanceChangeTopicDestination;
	@Resource
	private Destination pointChangeTopicDestination;
	@Resource
	private Destination userMessageQueueDestination;
	@Autowired
	private TWasteOrderMapper wasteOrderMapper;
	@Autowired
	private TWasteOrderItemMapper wasteOrderItemMapper;

	@Override
	public PageBean<TWasteOrder> getWasteOrderList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TWasteOrderExample wasteOrderExample = new TWasteOrderExample();
		wasteOrderExample.setOrderByClause(sort + " " + order);
		Criteria criteria = wasteOrderExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andWasteOrderIdEqualTo(Long.parseLong(search));
		}
		List<TWasteOrder> list = wasteOrderMapper.selectByExample(wasteOrderExample);
		PageInfo<TWasteOrder> pageInfo = new PageInfo<TWasteOrder>(list);
		PageBean<TWasteOrder> pageBean = new PageBean<TWasteOrder>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateWasteOrder(TWasteOrder wasteOrder) {
		wasteOrder.setUserId(null);
		wasteOrder.setUpdateTime(new Date());
		int i = wasteOrderMapper.updateByPrimaryKeySelective(wasteOrder);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新废品订单信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteWasteOrderByWasteOrderId(Long[] wasteOrderIds) {
		TWasteOrder wasteOrder = new TWasteOrder();
		for (int i = 0; i < wasteOrderIds.length; i++) {
			wasteOrder.setWasteOrderId(wasteOrderIds[i]);
			wasteOrder.setIsDeleted(true);
			wasteOrder.setUpdateTime(new Date());
			wasteOrderMapper.updateByPrimaryKeySelective(wasteOrder);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult findWasteOrderByWasteOrderId(Long wasteOrderId) {
		TWasteOrder wasteOrder = wasteOrderMapper.selectByPrimaryKey(wasteOrderId);
		if (wasteOrder != null && !wasteOrder.getIsDeleted()) {
			return HuishoucatResult.HuishoucatResultOK(null, wasteOrder);
		}
		return HuishoucatResult.HuishoucatResultError("无效废品ID！", null);
	}

	@Override
	public HuishoucatResult findRecyclerAchievementByRecyclerId(Long recyclerId) throws ParseException {
		TWasteOrderExample wasteOrderExample = new TWasteOrderExample();
		Calendar calendar = Calendar.getInstance();
		wasteOrderExample.createCriteria().andRecyclerIdEqualTo(recyclerId).andIsDeletedEqualTo(false)
				.andStateEqualTo(4).andCreateTimeGreaterThan(
						DateUtils.getFirstDayOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)));
		int monthFinishedOrder = wasteOrderMapper.countByExample(wasteOrderExample);
		wasteOrderExample.clear();
		wasteOrderExample.createCriteria().andStateEqualTo(2).andRecyclerIdEqualTo(recyclerId)
				.andIsDeletedEqualTo(false);
		int unfinishedOrder = wasteOrderMapper.countByExample(wasteOrderExample);
		wasteOrderExample.clear();
		wasteOrderExample.createCriteria().andStateEqualTo(4).andRecyclerIdEqualTo(recyclerId)
				.andIsDeletedEqualTo(false).andEndTimeGreaterThanOrEqualTo(DateUtils.getNowDay());
		int dayFinishedOrder = wasteOrderMapper.countByExample(wasteOrderExample);
		Map<String, Integer> map = new HashMap<String, Integer>(3);
		map.put("monthFinishedOrder", monthFinishedOrder);
		map.put("unfinishedOrder", unfinishedOrder);
		map.put("dayFinishedOrder", dayFinishedOrder);
		return HuishoucatResult.HuishoucatResultOK(null, map);
	}

	@Override
	public HuishoucatResult recyclerEditWasteOrderPrice(TWasteOrder wasteOrder) {
		TWasteOrder oldWasteOrder = wasteOrderMapper.selectByPrimaryKey(wasteOrder.getWasteOrderId());
		if (oldWasteOrder != null && oldWasteOrder.getRecyclerId().equals(wasteOrder.getRecyclerId())) {
			List<TWasteOrderItem> orderItems = wasteOrder.getWasteOrderItems();
			long totalPrice = 0;
			long totalPoint = 0;
			TWasteOrderItem wasteOrderItem = null;
			TWasteOrderItem oldWasteOrderItem = null;
			for (int i = 0; i < orderItems.size(); i++) {
				wasteOrderItem = orderItems.get(i);
				if (wasteOrderItem != null) {
					oldWasteOrderItem = wasteOrderItemMapper.selectByPrimaryKey(wasteOrderItem.getOrderItemId());
					if (oldWasteOrderItem != null) {
						totalPrice += wasteOrderItem.getRealNum().intValue()
								* wasteOrderItem.getRealUnitPrice().longValue();
						totalPoint += wasteOrderItem.getRealNum().intValue() * oldWasteOrderItem.getUnitPoint();
						wasteOrderItem.setUpdateTime(new Date());
						wasteOrderItemMapper.updateByPrimaryKeySelective(wasteOrderItem);
					}
				}
			}
			wasteOrder.setUpdateTime(new Date());
			wasteOrder.setRealPrice(totalPrice);
			wasteOrder.setRealPoint(totalPoint);
			wasteOrder.setState(4);
			Date date = new Date();
			wasteOrder.setUpdateTime(date);
			wasteOrder.setEndTime(date);
			wasteOrder.setUpdateTime(date);
			int i = wasteOrderMapper.updateByPrimaryKeySelective(wasteOrder);
			if (i == 1) {
				jmsTemplate.send(balanceChangeTopicDestination, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage textMessage = session.createTextMessage(
								JsonUtils.objectToJson(new TUserTransactionDetails(oldWasteOrder.getUserId(), 1, 2,
										wasteOrder.getRealPrice(), new Date())));
						return textMessage;
					}
				});
				jmsTemplate.send(pointChangeTopicDestination, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage textMessage = session.createTextMessage(
								JsonUtils.objectToJson(new TUserPointsDetails(oldWasteOrder.getUserId(), 1, 2,
										wasteOrder.getRealPoint(), new Date())));
						return textMessage;
					}
				});
				jmsTemplate.send(userMessageQueueDestination, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage textMessage = session.createTextMessage(
								JsonUtils.objectToJson(new HuishoucatMessage(oldWasteOrder.getUserId(), 1,
										"您的订单" + wasteOrder.getWasteOrderId() + "已被已完成，获得账户余额："
												+ wasteOrder.getRealPrice() / 100.0 + "元,绿色积分："
												+ wasteOrder.getRealPoint())));
						return textMessage;
					}
				});
				return HuishoucatResult.HuishoucatResultOK(null, null);
			} else {
				return HuishoucatResult.HuishoucatResultError("操作失败！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("无效信息！", null);
	}

	@Override
	public HuishoucatResult recyclerCancelWasteOrder(Long wasteOrderId, Long recyclerId, String invalidReason) {
		TWasteOrder wasteOrder = wasteOrderMapper.selectByPrimaryKey(wasteOrderId);
		if (wasteOrder != null && wasteOrder.getRecyclerId().equals(recyclerId)) {
			TWasteOrder newWasteOrder = new TWasteOrder();
			newWasteOrder.setWasteOrderId(wasteOrderId);
			newWasteOrder.setInvalidReason(invalidReason + "(回收员取消)");
			newWasteOrder.setState(3);
			Date date = new Date();
			newWasteOrder.setUpdateTime(date);
			newWasteOrder.setInvalidTime(date);
			int i = wasteOrderMapper.updateByPrimaryKeySelective(newWasteOrder);
			if (i != 1) {
				return HuishoucatResult.HuishoucatResultError("操作失败！", null);
			} else {
				jmsTemplate.send(userMessageQueueDestination, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage textMessage = session
								.createTextMessage(JsonUtils.objectToJson(new HuishoucatMessage(wasteOrder.getUserId(),
										1, "您的订单号为：" + wasteOrder.getWasteOrderId() + "的订单已被回收员取消，如有疑问请致电400666666")));
						return textMessage;
					}
				});
				return HuishoucatResult.HuishoucatResultOK(null, null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("无效信息！", null);
	}

	@Override
	public HuishoucatResult userCancelWasteOrder(Long wasteOrderId, Long userId, String invalidReason) {
		TWasteOrder wasteOrder = wasteOrderMapper.selectByPrimaryKey(wasteOrderId);
		if (wasteOrder != null && wasteOrder.getUserId().equals(userId)) {
			TWasteOrder newWasteOrder = new TWasteOrder();
			newWasteOrder.setWasteOrderId(wasteOrderId);
			newWasteOrder.setInvalidReason(invalidReason + "(用户取消)");
			newWasteOrder.setState(3);
			Date date = new Date();
			newWasteOrder.setUpdateTime(date);
			newWasteOrder.setInvalidTime(date);
			int i = wasteOrderMapper.updateByPrimaryKeySelective(newWasteOrder);
			if (i != 1) {
				return HuishoucatResult.HuishoucatResultError("操作失败！", null);
			} else {
				jmsTemplate.send(userMessageQueueDestination, new MessageCreator() {
					@Override
					public Message createMessage(Session session) throws JMSException {
						TextMessage textMessage = session
								.createTextMessage(JsonUtils.objectToJson(new HuishoucatMessage(wasteOrder.getUserId(),
										1, "您的订单号为：" + wasteOrder.getWasteOrderId() + "的订单已被您取消，如有疑问请致电400666666")));
						return textMessage;
					}
				});
				return HuishoucatResult.HuishoucatResultOK(null, null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("无效信息！", null);
	}

	@Override
	public HuishoucatResult userSubmitWasteOrder(TWasteOrder wasteOrder) {
		Date date = new Date();
		wasteOrder.setIsDeleted(null);
		wasteOrder.setCreateTime(date);
		wasteOrder.setUpdateTime(date);
		int i = wasteOrderMapper.insertSelective(wasteOrder);
		if (i != 1) {
			return HuishoucatResult.HuishoucatResultError("提交废品订单失败！", null);
		}
		List<TWasteOrderItem> list = wasteOrder.getWasteOrderItems();
		TWasteOrderItem wasteOrderItem = null;
		for (int j = 0; j < list.size(); j++) {
			wasteOrderItem = list.get(j);
			wasteOrderItem.setOrderItemId(null);
			wasteOrderItem.setWasteOrderId(wasteOrder.getWasteOrderId());
			wasteOrderItem.setCreateTime(date);
			wasteOrderItem.setUpdateTime(date);
			wasteOrderItemMapper.insertSelective(wasteOrderItem);
		}
		return HuishoucatResult.HuishoucatResultOK(null, wasteOrder);
	}

	@Override
	public HuishoucatResult userConfirmWasteOrder(TWasteOrder wasteOrder) {
		wasteOrder.setState(2);
		Date date = new Date();
		wasteOrder.setConfirmTime(date);
		wasteOrder.setUpdateTime(date);
		int i = wasteOrderMapper.updateByPrimaryKeySelective(wasteOrder);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("确认订单失败！", null);
	}

	@Override
	public HuishoucatResult countNewWasteOrderByUserId(Long userId) {
		TWasteOrderExample wasteOrderExample = new TWasteOrderExample();
		List<Integer> stateList = new ArrayList<Integer>();
		stateList.add(1);
		stateList.add(2);
		wasteOrderExample.createCriteria().andUserIdEqualTo(userId).andIsDeletedEqualTo(false).andStateIn(stateList);
		int i = wasteOrderMapper.countByExample(wasteOrderExample);
		return HuishoucatResult.HuishoucatResultOK(null, i);
	}

	@Override
	public HuishoucatResult findRecyclerWasteOrderList(Long recyclerId, Integer state) {
		TWasteOrderExample wasteOrderExample = new TWasteOrderExample();
		wasteOrderExample.setOrderByClause("confirm_time desc");
		wasteOrderExample.createCriteria().andRecyclerIdEqualTo(recyclerId).andStateEqualTo(state)
				.andIsDeletedEqualTo(false);
		List<TWasteOrder> list = wasteOrderMapper.selectByExample(wasteOrderExample);
		return HuishoucatResult.HuishoucatResultOK(null, list);
	}

	@Override
	public HuishoucatResult findUserWasteOrderList(Long userId, List<Integer> states) {
		TWasteOrderExample wasteOrderExample = new TWasteOrderExample();
		wasteOrderExample.setOrderByClause("create_time desc");
		wasteOrderExample.createCriteria().andUserIdEqualTo(userId).andIsDeletedEqualTo(false).andStateIn(states);
		List<TWasteOrder> list = wasteOrderMapper.selectByExample(wasteOrderExample);
		return HuishoucatResult.HuishoucatResultOK(null, list);
	}
}
