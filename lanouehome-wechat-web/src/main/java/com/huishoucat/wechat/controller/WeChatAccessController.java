package com.huishoucat.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huishoucat.common.utils.CookieUtils;
import com.huishoucat.wechat.service.IWeChatService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 微信接入Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年4月7日 下午2:43:21
 * @version V1.0
 */
@Controller
public class WeChatAccessController {

	@Value("${COOKIE_USER_OPENID_EXPIRE}")
	private Integer COOKIE_USER_OPENID_EXPIRE;

	@Autowired
	private IWeChatService weChatService;

	/**
	 * 微信第三方接入
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/wechat/access")
	public void wechatCore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		if (request.getMethod().equals("GET")) {
			String signature = request.getParameter("signature");
			String nonce = request.getParameter("nonce");
			String timestamp = request.getParameter("timestamp");
			if (weChatService.checkSignature(timestamp, nonce, signature)) {
				String echoStr = request.getParameter("echostr");
				if (StringUtils.isNotBlank(echoStr)) {
					// 说明是一个仅仅用来验证的请求，回显echostr
					String echoStrOut = String.copyValueOf(echoStr.toCharArray());
					response.getWriter().println(echoStrOut);
				}
			}
		} else if (request.getMethod().equals("POST")) {
			WxMpXmlMessage wxMpXmlMessage = WxMpXmlMessage.fromXml(request.getInputStream());
			String msgType = wxMpXmlMessage.getMsgType();
			String fromUser = wxMpXmlMessage.getFromUser();
			String touser = wxMpXmlMessage.getToUser();
			// String content = wxMpXmlMessage.getContent();
			if (msgType.equals("event")) {
				if ("xcx".equals(wxMpXmlMessage.getEventKey())) {
					WxMpXmlOutImageMessage image = WxMpXmlOutTextMessage.IMAGE().toUser(fromUser).fromUser(touser)
							.mediaId("K4GBihghRRygmxu9aiStQE_nh0biDnH3rfIk5ZlHkZc").build();
					System.out.println(image.toXml());
					PrintWriter out = null;
					try {
						out = response.getWriter();
						out.print(image.toXml());
					} catch (IOException e) {
						out = null;
						e.printStackTrace();
					}
					out.close();
					out = null;
				}
			}
		}
	}

	/**
	 * 微信网页授权
	 * @param request
	 * @param response
	 * @param code
	 * @return
	 * @throws WxErrorException
	 */
	@RequestMapping("/wechat/page_authorize")
	public String wechatPageAuthorize(HttpServletRequest request, HttpServletResponse response, String code)
			throws WxErrorException {
		if (StringUtils.isNotBlank(code)) {
			WxMpUser wxMpUser = weChatService.getUserInfoByCode(code);
			if (wxMpUser != null) {
				CookieUtils.setCookie(request, response, "userOpenid", wxMpUser.getOpenId(), COOKIE_USER_OPENID_EXPIRE,
						true);
			}
		}
		return "redirect:/wechat/index";
	}
}
