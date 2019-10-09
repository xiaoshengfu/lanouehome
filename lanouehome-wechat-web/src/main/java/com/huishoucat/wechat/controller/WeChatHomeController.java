package com.huishoucat.wechat.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishoucat.basket.service.IBasketService;
import com.huishoucat.cms.service.IBulletinService;
import com.huishoucat.cms.service.IContentService;
import com.huishoucat.cms.service.IItemDescService;
import com.huishoucat.cms.service.IWasteAttributeService;
import com.huishoucat.cms.service.IWastePriceService;
import com.huishoucat.cms.service.IWasteService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.utils.CookieUtils;
import com.huishoucat.manager.pojo.TUser;
import com.huishoucat.manager.pojo.TWaste;
import com.huishoucat.manager.pojo.TWasteOrderItem;
import com.huishoucat.manager.pojo.TWastePrice;
import com.huishoucat.messsage.service.INotificationService;
import com.huishoucat.order.service.IWasteOrderService;
import com.huishoucat.sso.service.ITokenService;
import com.huishoucat.wechat.service.IWeChatService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 蓝鸥e家首页Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月7日 上午8:10:21
 * @version V1.0
 */
@Controller
public class WeChatHomeController {

	@Value("${CAROUSEL_FIGURE_ID}")
	private Long CAROUSEL_FIGURE_ID;
	@Value("${COOKIE_USER_OPENID_EXPIRE}")
	private Integer COOKIE_USER_OPENID_EXPIRE;
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@Autowired
	private ITokenService tokenService;
	@Autowired
	private IWeChatService weChatService;
	@Autowired
	private IBasketService basketService;
	@Autowired
	private IWasteService wasteService;
	@Autowired
	private IContentService contentService;
	@Autowired
	private IBulletinService bulletinService;
	@Autowired
	private IWastePriceService wastePriceService;
	@Autowired
	private IWasteAttributeService wasteAttributeService;
	@Autowired
	private INotificationService notificationService;
	@Autowired
	private IItemDescService itemDescService;
	@Autowired
	private IWasteOrderService wasteOrderService;

	/**
	 * 404页面
	 * @return
	 */
	@RequestMapping("/wechat/404")
	public String show404Page() {
		return "404";
	}

	/**
	 * 用户登录页面
	 * @return
	 */
	@RequestMapping("/wechat/user/login/page")
	public String userLoginPage() {
		return "user_login";
	}

	/**
	 * 用户注册页面
	 * @return
	 */
	@RequestMapping("/wechat/user/register/page")
	public String userRegisterPage() {
		return "user_register";
	}

	/**
	 * 回收猫微信首页
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/wechat/index")
	public String showIndexPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		request.getSession().setAttribute("imageServiceUrl", IMAGE_SERVER_URL);
		String userOpenid = CookieUtils.getCookieValue(request, "userOpenid");
		if (StringUtils.isNotBlank(userOpenid)) {
			model.addAttribute("basketWasteOrderItemNum",
					((List<TWasteOrderItem>) basketService.getBasketList(userOpenid).getData()).size());
		}
		model.addAttribute("carouselFigureList",
				contentService.getContentListByCategoryId(CAROUSEL_FIGURE_ID).getData());
		model.addAttribute("wasteList", wasteService.findChildWaste(0L).getData());
		model.addAttribute("bulletin", bulletinService.findNewestBulletin().getData());
		return "index";
	}

	/**
	 * 获取废品列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/waste/find_child/list/{wasteId}")
	public String showWasteListByWasteId(@PathVariable Long wasteId, Model model) {
		model.addAttribute("parentWaste", wasteService.findWasteByWasteId(wasteId).getData());
		model.addAttribute("wasteList", wasteService.findChildWaste(wasteId).getData());
		return "waste_list";
	}

	/**
	 * 获取废品详情页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/waste/details/{wasteId}")
	public String showWasteDetails(@PathVariable Long wasteId, Model model) {
		HuishoucatResult wasteResult = wasteService.findWasteByWasteId(wasteId);
		if (wasteResult.getStateCode() == HuishoucatResult.SUCCESS) {
			TWaste waste = (TWaste) wasteResult.getData();
			HuishoucatResult priceResult = wastePriceService.getNewestWastePriceByWasteId(wasteId);
			if (priceResult.getStateCode() == HuishoucatResult.SUCCESS) {
				TWastePrice wastePrice = (TWastePrice) priceResult.getData();
				waste.setUnit(wastePrice.getUnit());
				waste.setEstimateUnitPrice((wastePrice.getPriceCeiling() + wastePrice.getPriceFloor()) / 2);
			}
			model.addAttribute("waste", waste);
		}
		model.addAttribute("wasteAttribute", wasteAttributeService.findWasteAttributeByWasteId(wasteId).getData());
		return "waste_details";
	}

	/**
	 * 用户个人中心
	 * @param request
	 * @param model
	 * @return
	 * @throws WxErrorException 
	 */
	@RequestMapping("/wechat/person/center")
	public String showUserCenter(HttpServletRequest request, Model model) throws WxErrorException {
		String openid = CookieUtils.getCookieValue(request, "userOpenid", true);
		if (StringUtils.isNotBlank(openid)) {
			WxMpUser wxMpUser = weChatService.getUserInfoByOpenid(openid, "zh_CN");
			model.addAttribute("wxMpUser", wxMpUser);
			String token = CookieUtils.getCookieValue(request, "token");
			if (StringUtils.isNotBlank(token)) {
				HuishoucatResult result = tokenService.getUserByToken(token);
				if (result.getStateCode() == HuishoucatResult.SUCCESS) {
					TUser user = (TUser) result.getData();
					model.addAttribute("user", user);
					model.addAttribute("notReadNotificationNum",
							notificationService.countNotReadNotificationByUserId(user.getUserId()).getData());
					model.addAttribute("newWasteOrderNum",
							wasteOrderService.countNewWasteOrderByUserId(user.getUserId()).getData());
				}
			}
		}
		return "person";
	}

	/**
	 * 获取积分商品列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/point/mall")
	public String showPointMall(Model model) {
		model.addAttribute("itemDescList",itemDescService.findItemDescList().getData());
		return "point_mall";
	}

	/**
	 * 获取用户通知列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/user/notification/list")
	public String showNotificationPage(HttpServletRequest request, Model model) {
		TUser user = (TUser) request.getAttribute("loginUser");
		model.addAttribute("notificationList",
				notificationService.findNotificationListByUserId(user.getUserId()).getData());
		return "notification_list";
	}

	/**
	 * 用户信息详情
	 * @param request
	 * @param model
	 * @return
	 * @throws WxErrorException 
	 */
	@RequestMapping("/wechat/user/user_information")
	public String userInformation(HttpServletRequest request, Model model) throws WxErrorException {
		String openid = CookieUtils.getCookieValue(request, "userOpenid", true);
		if (StringUtils.isNotBlank(openid)) {
			WxMpUser wxMpUser = weChatService.getUserInfoByOpenid(openid, "zh_CN");
			model.addAttribute("wxMpUser", wxMpUser);
		}
		TUser user = (TUser) request.getAttribute("loginUser");
		model.addAttribute("user", user);
		return "user_information";
	}

	/**
	 * 设置页面
	 * @return
	 */
	@RequestMapping("/wechat/user/settings")
	public String showSettingsPage() {
		return "settings";
	}

	/**
	 * 获取废品历史价格列表
	 * @return
	 */
	@RequestMapping("/wechat/historical_waste_price/list")
	public String historicalWastePriceList() {
		return "historical_waste_price";
	}

	/**
	 * 账户与安全
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/wechat/user/settings/account_and_security")
	public String accountAndSecurity(HttpServletRequest request, Model model) {
		TUser user = (TUser) request.getAttribute("loginUser");
		model.addAttribute("user", user);
		return "account_and_security";
	}

	/**
	 * 支付密码操作页面
	 * @return
	 */
	@RequestMapping("/wechat/user/settings/payment_password")
	public String showPaymentPasswordPage() {
		return "payment_password";
	}

	/**
	 * 系统通知提示操作页面
	 * @return
	 */
	@RequestMapping("/wechat/user/settings/notification_prompt")
	public String showNotificationPromptPage() {
		return "notification_prompt";
	}

	/**
	 * 关于回收猫
	 * @return
	 */
	@RequestMapping("/wechat/user/settings/about_huishoucat")
	public String aboutHuishoucat() {
		return "about_huishoucat";
	}
}
