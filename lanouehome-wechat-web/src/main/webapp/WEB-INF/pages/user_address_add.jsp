<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page" data-name="user-address-add">
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="left">
				<a class="link back" href="#"> <i class="icon icon-back"> </i> <span
					class="ios-only"> 返回 </span>
				</a>
			</div>
			<div class="title">新增地址</div>
			<div class="right">
				<a id="user_address_add_save" class="link" href="#"><span
					class="ios-only"> 保存 </span> </a>
			</div>
		</div>
	</div>
	<div class="page-content">
		<input id="user_address_district_id" type="hidden" />
		<div class="list inline-labels no-hairlines-md"
			style="margin-top: 0; margin-bottom: 0">
			<ul>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">联系人</div>
						<div class="item-input-wrap">
							<input id="user_address_name" placeholder="名字" type="text" /> <span
								class="input-clear-button"> </span>
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">性别</div>
						<div class="item-input-wrap">
							<label class="radio"><input class="user-address-sex"
								type="radio" name="sex" value="1" checked /><i
								class="icon-radio"></i></label>先生&nbsp;&nbsp;&nbsp; <label class="radio"><input
								class="user-address-sex" type="radio" name="sex" value="2" /><i
								class="icon-radio"></i></label>女士
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">手机号码</div>
						<div class="item-input-wrap">
							<input id="user_address_telephone" placeholder="11位手机号" type="text" /> <span
								class="input-clear-button"> </span>
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">选择地区</div>
						<div class="item-input-wrap">
							<input id="user_address_district_name" placeholder="地区信息"
								type="text" /> <span class="input-clear-button"> </span>
						</div>
						<div class="item-after" id="getMapInfo">
							<a class="popup-open" data-popup="#my-popup" href="#"> <i
								class="material-icons"> add_location </i>
							</a>
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">详细地址</div>
						<div class="item-input-wrap">
							<textarea id="user_address_address" placeholder="街道门牌号信息"></textarea>
							<span class="input-clear-button"> </span>
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">设为默认地址</div>
						<div class="item-after">
							<label class="toggle toggle-init color-blue"> <input
								id="default_user_address" type="checkbox" /> <span
								class="toggle-icon"></span>
							</label>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<div class="popup" id="my-popup">
			<div class="view">
				<div class="page">
					<div class="navbar">
						<div class="navbar-inner">
							<div class="left">
								<a @click="onDeleted" class="link popup-close" href="#"> 取消
								</a>
							</div>
							<div class="title">地理定位</div>
							<div class="right">
								<a class="link popup-close" href="#"> 确定 </a>
							</div>
						</div>
					</div>
					<div class="page-content">
						<iframe frameborder="0" height="100%" id="mapPage"
							src="http://apis.map.qq.com/tools/locpicker?search=1&type=1&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp"
							width="100%"> </iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>