package com.huishoucat.wechat.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.basket.service.IBasketService;
import com.huishoucat.cms.service.IWasteService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.CookieUtils;
import com.huishoucat.manager.pojo.TRecycler;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.manager.pojo.TUserAddress;
import com.huishoucat.manager.pojo.TWaste;
import com.huishoucat.manager.pojo.TWasteOrder;
import com.huishoucat.manager.pojo.TWasteOrderItem;
import com.huishoucat.order.service.IWasteOrderItemService;
import com.huishoucat.order.service.IWasteOrderService;
import com.huishoucat.ucenter.service.IRecyclerService;
import com.huishoucat.ucenter.service.IUserAddressService;

/**
 * 用户废品订单Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 上午8:10:21
 * @version V1.0
 */
@Controller
public class WeChatWasteOrderController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@Autowired
	private IRecyclerService recyclerService;
	@Autowired
	private IWasteOrderService wasteOrderService;
	@Autowired
	private IBasketService basketService;
	@Autowired
	private IUserAddressService userAddressService;
	@Autowired
	private IWasteOrderItemService wasteOrderItemService;
	@Autowired
	private IWasteService wasteService;

	/**
	 * 用户提交废品订单
	 * @param request
	 * @param model
	 * @param openid
	 * @param wasteIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wechat/user/waste_order/submit")
	public String userSubmitWasteOrderPage(HttpServletRequest request, Model model, Long[] wasteOrderItemIds) {
		String openid = CookieUtils.getCookieValue(request, "userOpenid", true);
		if (wasteOrderItemIds != null && StringUtils.isNotBlank(openid)) {
			TUser user = (TUser) request.getAttribute("loginUser");
			HuishoucatResult basketResult = basketService.findWasteListInBasketWaste(openid, wasteOrderItemIds);
			if (basketResult.getStateCode() == HuishoucatResult.SUCCESS) {
				TWasteOrder wasteOrder = new TWasteOrder();
				wasteOrder.setUserId(user.getUserId());
				List<TWasteOrderItem> itemList = (List<TWasteOrderItem>) basketResult.getData();
				TWasteOrderItem wasteOrderItem = null;
				wasteOrder.setWasteOrderItems(itemList);
				long totalPrice = 0L;
				long totalPoint = 0L;
				for (int i = 0; i < itemList.size(); i++) {
					wasteOrderItem = itemList.get(i);
					totalPrice += (wasteOrderItem.getEstimateUnitPrice() + wasteOrderItem.getAttributePrice())
							* wasteOrderItem.getEstimateNum();
					totalPoint += wasteOrderItem.getUnitPoint() * wasteOrderItem.getEstimateNum();
				}
				wasteOrder.setEstimatePrice(totalPrice);
				wasteOrder.setEstimatePoint(totalPoint);
				wasteOrder.setWasteOrderItems(itemList);
				HuishoucatResult wasteOrderResult = wasteOrderService.userSubmitWasteOrder(wasteOrder);
				model.addAttribute("wasteOrder", wasteOrderResult.getData());
				model.addAttribute("userAddressList",
						userAddressService.findUserAddressListByUserId(user.getUserId()).getData());
				model.addAttribute("defaultUserAddress",
						userAddressService.findDefaultUserAddress(user.getUserId()).getData());
				model.addAttribute("wasteOrderItemList", itemList);
				basketService.deleteBasketWaste(openid, wasteOrderItemIds);
			}
		}
		return "confirm_waste_order";
	}

	/**
	 * 用户确认废品订单页面
	 * @param request
	 * @param model
	 * @param wasteOrderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wechat/user/waste_order/confirm/page")
	public String userConfirmWasteOrderPage(HttpServletRequest request, Model model, Long wasteOrderId) {
		if (wasteOrderId != null) {
			TUser user = (TUser) request.getAttribute("loginUser");
			HuishoucatResult wasteOrderResult = wasteOrderService.findWasteOrderByWasteOrderId(wasteOrderId);
			if (wasteOrderResult.getStateCode() == HuishoucatResult.SUCCESS) {
				TWasteOrder wasteOrder = (TWasteOrder) wasteOrderResult.getData();
				if (wasteOrder.getUserId().equals(user.getUserId())) {
					HuishoucatResult itemListResult = wasteOrderItemService
							.findWasteOrderItemListByWasteOrderId(wasteOrderId);
					if (itemListResult.getStateCode() == HuishoucatResult.SUCCESS) {
						List<TWasteOrderItem> list = (List<TWasteOrderItem>) itemListResult.getData();
						TWasteOrderItem wasteOrderItem = null;
						TWaste waste = null;
						HuishoucatResult wasteResult = null;
						for (int i = 0; i < list.size(); i++) {
							wasteOrderItem = list.get(i);
							wasteResult = wasteService.findWasteByWasteId(wasteOrderItem.getWasteId());
							if (wasteResult.getStateCode() == HuishoucatResult.SUCCESS) {
								waste = (TWaste) wasteResult.getData();
								wasteOrderItem.setWaste(waste);
							}
						}
						model.addAttribute("wasteOrder", wasteOrder);
						model.addAttribute("defaultUserAddress",
								userAddressService.findDefaultUserAddress(user.getUserId()).getData());
						model.addAttribute("wasteOrderItemList", list);
					}
				}
			}
		}
		return "confirm_waste_order";
	}

	/**
	 * 用户确认废品订单
	 * @param model
	 * @param wasteOrder
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/wechat/user/waste_order/confirm")
	public String userConfirmWasteOrder(Model model, TWasteOrder wasteOrder) throws UnsupportedEncodingException {
		if (wasteOrder != null && wasteOrder.getWasteOrderId() != null && wasteOrder.getReserveTime() != null
				&& wasteOrder.getUserAddressId() != null) {
			/*
			 * if (StringUtils.isNotBlank(wasteOrder.getMessage())) {
			 * wasteOrder.setMessage(new
			 * String(wasteOrder.getMessage().getBytes("ISO8859-1"), "UTF-8"));
			 * }
			 */
			HuishoucatResult addressResult = userAddressService
					.findUserAddressByUserAddressId(wasteOrder.getUserAddressId());
			if (addressResult.getStateCode() == HuishoucatResult.SUCCESS) {
				TUserAddress userAddress = (TUserAddress) addressResult.getData();
				HuishoucatResult recyclerResult = recyclerService.findRecyclerByDistrictId(userAddress.getDistrictId());
				wasteOrder.setRecyclerId(null);
				if (recyclerResult.getStateCode() == HuishoucatResult.SUCCESS) {
					TRecycler recycler = (TRecycler) recyclerResult.getData();
					wasteOrder.setRecyclerId(recycler.getRecyclerId());
				}
				wasteOrder.setIsDeleted(null);
				wasteOrderService.userConfirmWasteOrder(wasteOrder);
				model.addAttribute("wasteOrder",
						wasteOrderService.findWasteOrderByWasteOrderId(wasteOrder.getWasteOrderId()).getData());
			}
		}
		return "confirm_waste_order_success";
	}

	/**
	 * 查看废品订单列表(待确认、未完成、已完成、已失效)
	 * @param request
	 * @param model
	 * @param state
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wechat/user/waste_order/list")
	public String showNewWasteOrderList(HttpServletRequest request, Model model) {
		TUser user = (TUser) request.getAttribute("loginUser");
		List<Integer> states = new ArrayList<Integer>(4);
		states.add(1);
		states.add(2);
		states.add(3);
		states.add(4);
		List<TWasteOrder> list = (List<TWasteOrder>) wasteOrderService.findUserWasteOrderList(user.getUserId(), states)
				.getData();
		if (list != null && list.size() > 0) {
			TWasteOrder wasteOrder = null;
			HuishoucatResult addressResult = null;
			HuishoucatResult itemResult = null;
			HuishoucatResult wasteResult = null;
			HuishoucatResult recyclerResult = null;
			TUserAddress userAddress = null;
			TWaste waste = null;
			TRecycler recycler = null;
			Integer state = null;
			List<TWasteOrderItem> itemList = null;
			TWasteOrderItem orderItem = null;
			List<TWasteOrder> list1 = new ArrayList<TWasteOrder>();
			List<TWasteOrder> list2 = new ArrayList<TWasteOrder>();
			List<TWasteOrder> list3 = new ArrayList<TWasteOrder>();
			List<TWasteOrder> list4 = new ArrayList<TWasteOrder>();
			for (int i = 0; i < list.size(); i++) {
				wasteOrder = list.get(i);
				addressResult = userAddressService.findUserAddressByUserAddressId(wasteOrder.getUserAddressId());
				if (addressResult.getStateCode() == HuishoucatResult.SUCCESS) {
					userAddress = (TUserAddress) addressResult.getData();
					wasteOrder.setUserAddress(userAddress);
				}
				recyclerResult = recyclerService.findRecyclerByRecyclerId(wasteOrder.getRecyclerId());
				if (recyclerResult.getStateCode() == HuishoucatResult.SUCCESS) {
					recycler = (TRecycler) recyclerResult.getData();
					wasteOrder.setRecycler(recycler);
				}
				itemResult = wasteOrderItemService.findWasteOrderItemListByWasteOrderId(wasteOrder.getWasteOrderId());
				if (itemResult.getStateCode() == HuishoucatResult.SUCCESS) {
					itemList = (List<TWasteOrderItem>) itemResult.getData();
					for (int j = 0; j < itemList.size(); j++) {
						orderItem = itemList.get(j);
						wasteResult = wasteService.findWasteByWasteId(orderItem.getWasteId());
						if (wasteResult.getStateCode() == HuishoucatResult.SUCCESS) {
							waste = (TWaste) wasteResult.getData();
							orderItem.setWaste(waste);
						}
					}
					wasteOrder.setWasteOrderItems(itemList);
				}
				state = wasteOrder.getState();
				if (state == 1) {
					list1.add(wasteOrder);
				} else if (state == 2) {
					list2.add(wasteOrder);
				} else if (state == 3) {
					list3.add(wasteOrder);
				} else if (state == 4) {
					list4.add(wasteOrder);
				}
			}
			model.addAttribute("wasteOrder1List", list1);
			model.addAttribute("wasteOrder2List", list2);
			model.addAttribute("wasteOrder3List", list3);
			model.addAttribute("wasteOrder4List", list4);
		}
		return "waste_order_list";
	}

	/**
	 * 用户取消废品订单
	 * @param request
	 * @param model
	 * @param wasteOrderId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/wechat/user/waste_order/cancel")
	@ResponseBody
	public HuishoucatResult userCancelWasteOrder(HttpServletRequest request, Model model, Long wasteOrderId,
			String invalidReason) throws UnsupportedEncodingException {
		if (wasteOrderId != null && StringUtils.isNotBlank(invalidReason)) {
			TUser user = (TUser) request.getAttribute("loginUser");
			return wasteOrderService.userCancelWasteOrder(wasteOrderId, user.getUserId(), invalidReason);
		}
		return HuishoucatResult.HuishoucatResultError("信息不完整！", null);
	}

	/**
	 * 查看待废品订单详情
	 * @param request
	 * @param model
	 * @param wasteOrderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wechat/user/waste_order/details")
	public String showWasteOrderDetails(HttpServletRequest request, Model model, Long wasteOrderId) {
		if (wasteOrderId != null) {
			TUser user = (TUser) request.getAttribute("loginUser");
			HuishoucatResult wasteOrderResult = wasteOrderService.findWasteOrderByWasteOrderId(wasteOrderId);
			if (wasteOrderResult.getStateCode() == HuishoucatResult.SUCCESS) {
				TWasteOrder wasteOrder = (TWasteOrder) wasteOrderResult.getData();
				HuishoucatResult itemResult = wasteOrderItemService.findWasteOrderItemListByWasteOrderId(wasteOrderId);
				if (itemResult.getStateCode() == HuishoucatResult.SUCCESS) {
					List<TWasteOrderItem> list = (List<TWasteOrderItem>) itemResult.getData();
					TWasteOrderItem item = null;
					TWaste waste = null;
					HuishoucatResult wasteResult = null;
					for (int i = 0; i < list.size(); i++) {
						item = list.get(i);
						wasteResult = wasteService.findWasteByWasteId(item.getWasteId());
						if (wasteResult.getStateCode() == HuishoucatResult.SUCCESS) {
							waste = (TWaste) wasteResult.getData();
							waste.setPictureUrl(IMAGE_SERVER_URL + waste.getPictureUrl());
							item.setWaste(waste);
						}
					}
					wasteOrder.setWasteOrderItems(list);
					Integer state = wasteOrder.getState();
					if (state == 1) {
						HuishoucatResult defaultAddressResult = userAddressService
								.findDefaultUserAddress(user.getUserId());
						if (defaultAddressResult.getStateCode() == HuishoucatResult.SUCCESS) {
							TUserAddress userAddress = (TUserAddress) defaultAddressResult.getData();
							wasteOrder.setUserAddress(userAddress);
						}
						model.addAttribute("wasteOrder", wasteOrder);
						return "confirm_waste_order";
					} else {
						HuishoucatResult addressResult = userAddressService
								.findUserAddressByUserAddressId(wasteOrder.getUserAddressId());
						if (addressResult.getStateCode() == HuishoucatResult.SUCCESS) {
							TUserAddress userAddress = (TUserAddress) addressResult.getData();
							wasteOrder.setUserAddress(userAddress);
						}
						model.addAttribute("wasteOrder", wasteOrder);
						if (state == 4) {
							return "waste_order_deails_finished";
						} else {
							return "waste_order_deails_invalid";
						}
					}
				}
			}
		}
		return "error_page";
	}
}
