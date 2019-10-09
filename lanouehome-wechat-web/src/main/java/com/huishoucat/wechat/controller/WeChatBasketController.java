package com.huishoucat.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.basket.service.IBasketService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.CookieUtils;
import com.huishoucat.manager.pojo.TWasteOrderItem;

/**
 * 废品筐Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 上午8:10:21
 * @version V1.0
 */
@Controller
public class WeChatBasketController {

	@Autowired
	private IBasketService basketService;

	/**
	 * 回收筐页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/basket")
	public String showBasket(HttpServletRequest request, Model model) {
		String openid = CookieUtils.getCookieValue(request, "userOpenid", true);
		if (StringUtils.isNotBlank(openid)) {
			model.addAttribute("wasteOrderItemList", basketService.getBasketList(openid).getData());
		}
		return "basket";
	}

	/**
	 * 删除废品筐内废品
	 * @param request
	 * @param wasteOrderItemIds
	 * @return
	 */
	@RequestMapping("/wechat/basket/delete")
	@ResponseBody
	public HuishoucatResult basketDeleteWasteItem(HttpServletRequest request, @RequestBody Long[] wasteOrderItemIds) {
		String openid = CookieUtils.getCookieValue(request, "userOpenid", true);
		if (StringUtils.isNotBlank(openid) && wasteOrderItemIds != null) {
			return basketService.deleteBasketWaste(openid, wasteOrderItemIds);
		}
		return HuishoucatResult.HuishoucatResultError("信息不完整！", null);
	}

	/**
	 * 向废品筐中添加废品
	 * @param request
	 * @param wasteOrderItem
	 * @return
	 */
	@RequestMapping("/wechat/basket/add")
	@ResponseBody
	public HuishoucatResult basketAddWasteItem(HttpServletRequest request,
			@RequestBody TWasteOrderItem wasteOrderItem) {
		String openid = CookieUtils.getCookieValue(request, "userOpenid", true);
		if (StringUtils.isNotBlank(openid) && wasteOrderItem != null && wasteOrderItem.getWasteId() != null
				&& wasteOrderItem.getEstimateNum() != null && wasteOrderItem.getEstimateUnitPrice() != null
				&& wasteOrderItem.getUnit() != null) {
			return basketService.addBasket(openid, wasteOrderItem);
		}
		return HuishoucatResult.HuishoucatResultError("信息不完整！", null);
	}
}
