package com.huishoucat.miniprogram.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.cms.service.IWasteService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.manager.pojo.TRecycler;
import com.huishoucat.manager.pojo.TUserAddress;
import com.huishoucat.manager.pojo.TWaste;
import com.huishoucat.manager.pojo.TWasteOrder;
import com.huishoucat.manager.pojo.TWasteOrderItem;
import com.huishoucat.order.service.IWasteOrderItemService;
import com.huishoucat.order.service.IWasteOrderService;
import com.huishoucat.sso.service.ITokenService;
import com.huishoucat.ucenter.service.IUserAddressService;

/**
 * 回收员订单Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 上午8:10:21
 * @version V1.0
 */
@Controller
public class RecyclerOrderController {

	@Autowired
	private IWasteService wasteService;
	@Autowired
	private IWasteOrderService wasteOrderService;
	@Autowired
	private IWasteOrderItemService wasteOrderItemService;
	@Autowired
	private IUserAddressService userAddressService;
	@Autowired
	private ITokenService tokenService;

	/**
	 * 获取回收员信息
	 * @param token
	 * @return
	 */
	@RequestMapping("/miniprogram/recycler/find/{token}")
	@ResponseBody
	public HuishoucatResult getRecycler(@PathVariable String token) {
		if (StringUtils.isNotBlank(token)) {
			HuishoucatResult huishoucatResult = tokenService.getRecyclerByToken(token);
			if (huishoucatResult.getStateCode() == HuishoucatResult.SUCCESS) {
				return huishoucatResult;
			} else {
				return HuishoucatResult.HuishoucatResultUnAuth("身份认证失败，请重新登录！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("操作失败！", null);
	}

	/**
	 * 回收员查找订单列表(按照创建时间升序排序)
	 * @param token
	 * @param state 状态码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/miniprogram/recycler_order/list/{token}")
	@ResponseBody
	public HuishoucatResult getRecyclerOldOrder(@PathVariable String token, Integer state) {
		if (StringUtils.isNotBlank(token) && state != null) {
			HuishoucatResult huishoucatResult = tokenService.getRecyclerByToken(token);
			if (huishoucatResult.getStateCode() == HuishoucatResult.SUCCESS) {
				TRecycler recycler = (TRecycler) huishoucatResult.getData();
				HuishoucatResult result = wasteOrderService.findRecyclerWasteOrderList(recycler.getRecyclerId(), state);
				if (result.getStateCode() == HuishoucatResult.SUCCESS) {
					List<TWasteOrder> wasteOrderList = (List<TWasteOrder>) result.getData();
					for (int i = 0; i < wasteOrderList.size(); i++) {
						HuishoucatResult userAddressResult = userAddressService
								.findUserAddressByUserAddressId(wasteOrderList.get(i).getUserAddressId());
						if (userAddressResult.getStateCode() == HuishoucatResult.SUCCESS) {
							wasteOrderList.get(i).setUserAddress((TUserAddress) userAddressResult.getData());
						}
					}
					return HuishoucatResult.HuishoucatResultOK(null, wasteOrderList);
				}
			} else {
				return HuishoucatResult.HuishoucatResultUnAuth("身份认证失败，请重新登录！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("操作失败！", null);
	}

	/**
	 * 回收员查看废品订单详情(按照创建时间升序排序)
	 * @param token
	 * @param wasteOrderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/miniprogram/recycler_order/find_order_details/{token}")
	@ResponseBody
	public HuishoucatResult findWasteOrderDetails(@PathVariable String token, Long wasteOrderId) {
		if (StringUtils.isNotBlank(token)) {
			HuishoucatResult huishoucatResult = tokenService.getRecyclerByToken(token);
			if (huishoucatResult.getStateCode() == HuishoucatResult.SUCCESS) {
				if (wasteOrderId != null) {
					HuishoucatResult result = wasteOrderService.findWasteOrderByWasteOrderId(wasteOrderId);
					if (result.getStateCode() == HuishoucatResult.SUCCESS) {
						TWasteOrder wasteOrder = (TWasteOrder) result.getData();
						HuishoucatResult userAddressResult = userAddressService
								.findUserAddressByUserAddressId(wasteOrder.getUserAddressId());
						if (userAddressResult.getStateCode() == HuishoucatResult.SUCCESS) {
							wasteOrder.setUserAddress((TUserAddress) userAddressResult.getData());
						}
						HuishoucatResult wasteOrderItemResult = wasteOrderItemService
								.findWasteOrderItemListByWasteOrderId(wasteOrderId);
						if (wasteOrderItemResult.getStateCode() == HuishoucatResult.SUCCESS) {
							List<TWasteOrderItem> list = (List<TWasteOrderItem>) wasteOrderItemResult.getData();
							HuishoucatResult wasteResult = null;
							TWaste waste = null;
							for (int i = 0; i < list.size(); i++) {
								wasteResult = wasteService.findWasteByWasteId(list.get(i).getWasteId());
								if (wasteResult.getStateCode() == HuishoucatResult.SUCCESS) {
									waste = (TWaste) wasteResult.getData();
									list.get(i).setWaste(waste);
								}
							}
							wasteOrder.setWasteOrderItems(list);
						}
						return HuishoucatResult.HuishoucatResultOK(null, wasteOrder);
					}
				}
			} else {
				return HuishoucatResult.HuishoucatResultUnAuth("身份认证失败，请重新登录！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("操作失败！", null);
	}

	/**
	 * 查找回收员月完成订单数、当前剩余订单数、日完成订单数。
	 * @param token
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/miniprogram/recycler_achievement/find/{token}")
	@ResponseBody
	public HuishoucatResult getRecyclerAchievement(@PathVariable String token) throws ParseException {
		if (StringUtils.isNotBlank(token)) {
			HuishoucatResult huishoucatResult = tokenService.getRecyclerByToken(token);
			if (huishoucatResult.getStateCode() == HuishoucatResult.SUCCESS) {
				TRecycler recycler = (TRecycler) huishoucatResult.getData();
				return wasteOrderService.findRecyclerAchievementByRecyclerId(recycler.getRecyclerId());
			} else {
				return HuishoucatResult.HuishoucatResultUnAuth("身份认证失败，请重新登录！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("操作失败！", null);
	}

	/**
	 * 回收员编辑废品订单最终废品数量和单价
	 * @param token
	 * @param wasteOrder
	 * @return
	 */
	@RequestMapping("/miniprogram/recycler_order/edit_order_price/{token}")
	@ResponseBody
	public HuishoucatResult recyclerEditWasteOrderItemPrice(@PathVariable String token,
			@RequestBody TWasteOrder wasteOrder) {
		if (StringUtils.isNotBlank(token)) {
			HuishoucatResult huishoucatResult = tokenService.getRecyclerByToken(token);
			if (huishoucatResult.getStateCode() == HuishoucatResult.SUCCESS) {
				if (wasteOrder != null && wasteOrder.getWasteOrderId() != null
						&& wasteOrder.getWasteOrderItems() != null) {
					TRecycler recycler = (TRecycler) huishoucatResult.getData();
					wasteOrder.setRecyclerId(recycler.getRecyclerId());
					return wasteOrderService.recyclerEditWasteOrderPrice(wasteOrder);
				}
			} else {
				return HuishoucatResult.HuishoucatResultUnAuth("身份认证失败，请重新登录！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("操作失败！", null);
	}

	/**
	 * 回收员取消废品订单
	 * @param token
	 * @param wasteOrderId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/miniprogram/recycler_order/cancel_order/{token}")
	@ResponseBody
	public HuishoucatResult recyclerCancelWasteOrder(@PathVariable String token, Long wasteOrderId,
			String invalidReason) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(token)) {
			HuishoucatResult huishoucatResult = tokenService.getRecyclerByToken(token);
			if (huishoucatResult.getStateCode() == HuishoucatResult.SUCCESS) {
				if (wasteOrderId != null && StringUtils.isNotBlank(invalidReason)) {
					TRecycler recycler = (TRecycler) huishoucatResult.getData();
					return wasteOrderService.recyclerCancelWasteOrder(wasteOrderId, recycler.getRecyclerId(),
							invalidReason);
				}
			} else {
				return HuishoucatResult.HuishoucatResultUnAuth("身份认证失败，请重新登录！", null);
			}
		}
		return HuishoucatResult.HuishoucatResultError("请填写完整信息！", null);
	}
}
