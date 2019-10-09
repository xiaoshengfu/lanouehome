package com.huishoucat.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.manager.pojo.TUserAddress;
import com.huishoucat.ucenter.service.IUserAddressService;

/**
 * 用户上门地址Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 上午8:10:21
 * @version V1.0
 */
@Controller
public class WeChatUserAddressController {

	@Autowired
	private IUserAddressService userAddressService;

	/**
	 * 展示用户地址列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/user/user_address/list")
	public String showUserAddressList(HttpServletRequest request, Model model) {
		TUser user = (TUser) request.getAttribute("loginUser");
		model.addAttribute("userAddressList",
				userAddressService.findUserAddressListByUserId(user.getUserId()).getData());
		return "user_address_list";
	}

	/**
	 * 获取用户地址列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/wechat/user/user_address/get_list")
	@ResponseBody
	public HuishoucatResult getUserAddressList(HttpServletRequest request) {
		TUser user = (TUser) request.getAttribute("loginUser");
		return userAddressService.findUserAddressListByUserId(user.getUserId());

	}

	/**
	 * 展示用户地址详情
	 * @param model
	 * @param userAddressId
	 * @return
	 */
	@RequestMapping("/wechat/user/user_address/details/{userAddressId}")
	public String showUserAddressDetails(Model model, @PathVariable Long userAddressId) {
		if (userAddressId != null) {
			model.addAttribute("userAddress",
					userAddressService.findUserAddressByUserAddressId(userAddressId).getData());
		}
		return "user_address_details";
	}

	/**
	 * 设置默认上门回收地址
	 * @param request
	 * @param model
	 * @param userAddressId
	 * @return
	 */
	@RequestMapping("/wechat/user/user_address/set_default")
	@ResponseBody
	public HuishoucatResult setDefaultUserAddress(HttpServletRequest request, Model model, Long userAddressId) {
		if (userAddressId != null) {
			TUser user = (TUser) request.getAttribute("loginUser");
			return userAddressService.setDefaultUserAddress(user.getUserId(), userAddressId);
		}
		return HuishoucatResult.HuishoucatResultError("用户地址ID不能为空！", null);
	}

	/**
	 * 编辑上门回收地址信息页面
	 * @param request
	 * @param model
	 * @param userAddressId
	 * @return
	 */
	@RequestMapping("/wechat/user/user_address/edit/page")
	public String editUserAddressPage(Model model, Long userAddressId) {
		if (userAddressId != null) {
			model.addAttribute("userAddress",
					userAddressService.findUserAddressByUserAddressId(userAddressId).getData());
		}
		return "user_address_edit";
	}

	/**
	 * 编辑上门回收地址信息
	 * @param request
	 * @param model
	 * @param userAddress
	 * @return
	 */
	@RequestMapping("/wechat/user/user_address/edit")
	@ResponseBody
	public HuishoucatResult editUserAddress(HttpServletRequest request, Model model,
			@RequestBody TUserAddress userAddress) {
		if (userAddress != null && userAddress.getUserAddressId() != null) {
			TUser user = (TUser) request.getAttribute("loginUser");
			userAddress.setUserId(user.getUserId());
			userAddress.setIsDeleted(null);
			return userAddressService.updateUserAddress(userAddress);
		}
		return HuishoucatResult.HuishoucatResultError("数据不完整！", null);
	}

	/**
	 * 添加上门回收地址信息页面
	 * @return
	 */
	@RequestMapping("/wechat/user/user_address/add/page")
	public String addUserAddressPage() {
		return "user_address_add";
	}

	/**
	 * 添加上门回收地址信息
	 * @param request
	 * @param model
	 * @param userAddress
	 * @return
	 */
	@RequestMapping("/wechat/user/user_address/add")
	@ResponseBody
	public HuishoucatResult addUserAddress(HttpServletRequest request, Model model,
			@RequestBody TUserAddress userAddress) {
		if (userAddress != null && userAddress.getDistrictId() != null
				&& StringUtils.isNoneBlank(userAddress.getAddress())
				&& StringUtils.isNoneBlank(userAddress.getDistrictName())
				&& StringUtils.isNoneBlank(userAddress.getName()) && userAddress.getSex() != null) {
			TUser user = (TUser) request.getAttribute("loginUser");
			userAddress.setUserId(user.getUserId());
			userAddress.setIsDeleted(null);
			return userAddressService.addUserAddress(userAddress);
		}
		return HuishoucatResult.HuishoucatResultError("数据不完整！", null);
	}

	/**
	 * 删除用户地址
	 * @param userAddressId
	 * @return
	 */
	@RequestMapping("/wechat/user/user_address/delete")
	@ResponseBody
	public HuishoucatResult showUserAddressList(Long userAddressId) {
		if (userAddressId != null) {
			return userAddressService.deleteUserAddress(userAddressId);
		}
		return HuishoucatResult.HuishoucatResultError("用户地址ID不能为空！", null);
	}
}
