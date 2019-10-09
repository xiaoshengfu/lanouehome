package com.huishoucat.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishoucat.cms.service.IItemDescService;

/**
 * @ClassName: WeChatPointMallController  
 * @Description: 积分商城Controller  
 * @author xiaoshengfu(2439323118@qq.com)  
 * @date: 2018年7月9日 下午2:13:43
 */
@Controller
public class WeChatPointMallController {

	@Autowired
	private IItemDescService itemDescService;

	/**
	 * @Title: showWasteListByWasteId  
	 * @Description: 积分商品详情
	 * @param itemDescId
	 * @param model
	 * @return String
	 */
	@RequestMapping("/wechat/point/mall/{itemDescId}")
	public String showWasteListByWasteId(@PathVariable Long itemDescId, Model model) {
		model.addAttribute("itemDesc", itemDescService.findItemDescByItemId(itemDescId).getData());
		return "point_item";
	}

}
