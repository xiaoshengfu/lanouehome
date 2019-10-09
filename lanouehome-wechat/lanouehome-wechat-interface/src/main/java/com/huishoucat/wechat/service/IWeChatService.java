package com.huishoucat.wechat.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;

/**
 * 微信操作Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年4月7日 下午12:47:18
 * @version V1.0
 */
public interface IWeChatService {

	/**
	 * 验证消息的确来自微信服务器
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @return
	 */
	public boolean checkSignature(String timestamp, String nonce, String signature);
	
	/**
	 * 通过openid获取用户信息
	 * @param openid
	 * @param lang  中文 -->zh_CN 
	 * @return
	 * @throws WxErrorException
	 */
	public WxMpUser getUserInfoByOpenid(String openid, String lang) throws WxErrorException;

	/**
	 * 通过网页授权code获取AccessToken
	 * @param code
	 * @return
	 */
	public WxMpOAuth2AccessToken getAccessToken(String code) throws WxErrorException;

	/**
	 * 刷新access token
	 * @param wxMpOAuth2AccessToken
	 * @return
	 */
	public WxMpOAuth2AccessToken refreshAccessToken(WxMpOAuth2AccessToken wxMpOAuth2AccessToken)
			throws WxErrorException;

	/**
	 * 验证access token
	 * @param wxMpOAuth2AccessToken
	 * @return
	 */
	public boolean validateAccessToken(WxMpOAuth2AccessToken wxMpOAuth2AccessToken);

	/**
	 * 通过AccessToken获取用户信息
	 * @param wxMpOAuth2AccessToken
	 * @return
	 */
	public WxMpUser getUserInfoByAccessToken(WxMpOAuth2AccessToken wxMpOAuth2AccessToken) throws WxErrorException;

	/**
	 * 通过网页授权code获取用户信息
	 * @param code 
	 * @return WxMpUser
	 * @throws WxErrorException
	 */
	public WxMpUser getUserInfoByCode(String code) throws WxErrorException;

	/**
	 * 获取用户基本列表
	 * @param next_openid
	 * @return
	 */
	public WxMpUserList getUserList(String next_openid) throws WxErrorException;

	/**
	 * 获取用户详细列表
	 * @param openids
	 * @return
	 */
	public List<WxMpUser> geUserListInfo(List<String> openids) throws WxErrorException;

	/**
	 * 获取临时二维码Ticket
	 * @param scene
	 * @param expire_seconds
	 * @return
	 */
	public WxMpQrCodeTicket getQrCodeTmpTicket(String scene, int expire_seconds) throws WxErrorException;

	/**
	 * 获取永久二维码Ticket
	 * @param scene
	 * @return
	 */
	public WxMpQrCodeTicket getQrCodeLastTicket(String scene) throws WxErrorException;

	/**
	 * 换取二维码图片
	 * @param ticket
	 * @return
	 */
	public File getQrCodePicture(WxMpQrCodeTicket ticket) throws WxErrorException;

	/**
	 * 获取超短链接
	 * @param url
	 * @return
	 */
	public String getShortUrl(String url) throws WxErrorException;

	/**
	 * 发送模板消息
	 * @param openid
	 * @param templeid
	 * @param templeContent
	 */
	public void sendTempleMessage(String openid, String templeid, Map<String, String> templeContent, String url)
			throws WxErrorException;

	/**
	 * 主动发送消息(客服消息)
	 * @param message (WxMpKeFuServiceUtils)
	 * @throws WxErrorException
	 */
	public void sendActiveMessage(WxMpKefuMessage message) throws WxErrorException;

	/**
	 * 创建自定义菜单
	 */
	public void creatMenu(WxMenu wxMenu) throws WxErrorException;

	/**
	 * 删除自定义菜单
	 */
	public void deleteMenu() throws WxErrorException;

	/**
	 * 获得自定义菜单
	 * @return
	 */
	public WxMpMenu getMenu() throws WxErrorException;
}
