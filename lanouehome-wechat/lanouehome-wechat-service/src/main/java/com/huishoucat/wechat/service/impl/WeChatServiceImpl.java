package com.huishoucat.wechat.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huishoucat.wechat.service.IWeChatService;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * 微信操作Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年4月7日 下午12:54:10
 * @version V1.0
 */
@Service
public class WeChatServiceImpl implements IWeChatService {

	@Autowired
	private WxMpService wxMpService;

	@Override
	public boolean checkSignature(String timestamp, String nonce, String signature) {
		return wxMpService.checkSignature(timestamp, nonce, signature);
	}

	@Override
	public void sendTempleMessage(String openid, String templeid, Map<String, String> templeContent, String url)
			throws WxErrorException {
		WxMpTemplateMessage templateMessage = null;
		if (StringUtils.isNotBlank(url)) {
			templateMessage = WxMpTemplateMessage.builder().toUser(openid).templateId(templeid).url(url).build();
		} else {
			templateMessage = WxMpTemplateMessage.builder().toUser(openid).templateId(templeid).build();
		}
		templateMessage.addData(new WxMpTemplateData("first", templeContent.get("first")));
		for (int i = 1; i < templeContent.size() - 1; i++) {
			templateMessage.addData(new WxMpTemplateData("k" + i, templeContent.get("k" + 1)));
		}
		templateMessage.addData(new WxMpTemplateData("remark", templeContent.get("remark")));
		wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
	}

	@Override
	public void sendActiveMessage(WxMpKefuMessage message) throws WxErrorException {
		wxMpService.getKefuService().sendKefuMessage(message);
	}

	@Override
	public WxMpUser getUserInfoByOpenid(String openid, String lang) throws WxErrorException {
		return wxMpService.getUserService().userInfo(openid, lang);
	}

	@Override
	public WxMpOAuth2AccessToken getAccessToken(String code) throws WxErrorException {
		return wxMpService.oauth2getAccessToken(code);
	}

	@Override
	public WxMpOAuth2AccessToken refreshAccessToken(WxMpOAuth2AccessToken wxMpOAuth2AccessToken)
			throws WxErrorException {
		return wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
	}

	@Override
	public boolean validateAccessToken(WxMpOAuth2AccessToken wxMpOAuth2AccessToken) {
		return wxMpService.oauth2validateAccessToken(wxMpOAuth2AccessToken);
	}

	@Override
	public WxMpUser getUserInfoByAccessToken(WxMpOAuth2AccessToken wxMpOAuth2AccessToken) throws WxErrorException {
		return wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
	}

	@Override
	public WxMpUser getUserInfoByCode(String code) throws WxErrorException {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		return wxMpUser;
	}

	@Override
	public WxMpUserList getUserList(String next_openid) throws WxErrorException {
		return wxMpService.getUserService().userList(next_openid);
	}

	@Override
	public List<WxMpUser> geUserListInfo(List<String> openids) throws WxErrorException {
		return wxMpService.getUserService().userInfoList(openids);
	}

	@Override
	public WxMpQrCodeTicket getQrCodeTmpTicket(String scene, int expire_seconds) throws WxErrorException {
		return wxMpService.getQrcodeService().qrCodeCreateTmpTicket(scene, expire_seconds);
	}

	@Override
	public WxMpQrCodeTicket getQrCodeLastTicket(String scene) throws WxErrorException {
		return wxMpService.getQrcodeService().qrCodeCreateLastTicket(scene);
	}

	@Override
	public File getQrCodePicture(WxMpQrCodeTicket ticket) throws WxErrorException {
		return wxMpService.getQrcodeService().qrCodePicture(ticket);
	}

	@Override
	public String getShortUrl(String url) throws WxErrorException {
		return wxMpService.shortUrl(url);
	}

	@Override
	public void creatMenu(WxMenu wxMenu) throws WxErrorException {
		wxMpService.getMenuService().menuCreate(wxMenu);
	}

	@Override
	public void deleteMenu() throws WxErrorException {
		wxMpService.getMenuService().menuDelete();
	}

	@Override
	public WxMpMenu getMenu() throws WxErrorException {
		return wxMpService.getMenuService().menuGet();
	}
}
