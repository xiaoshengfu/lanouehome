<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page" data-name="user-address-details">
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="left">
				<a class="link back" href="#"> <i class="icon icon-back"> </i> <span
					class="ios-only"> 返回 </span>
				</a>
			</div>
			<div class="title">地址详情</div>
		</div>
	</div>
	<div class="page-content">
		<div class="list inline-labels no-hairlines-md"
			style="margin-top: 0; margin-bottom: 0">
			<ul>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">联系人</div>
						<div class="item-input-wrap">
							<input id="user_address_name" placeholder="名字" type="text"
								value="${userAddress.name}" readonly="readonly" />
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">性别</div>
						<div class="item-input-wrap">
							<label class="radio"><input class="user-address-sex"
								type="radio" name="sex" value="1" readonly="readonly"
								<c:if test="${userAddress.sex==1}"> checked</c:if> /><i
								class="icon-radio"></i></label>先生&nbsp;&nbsp;&nbsp; <label class="radio"><input
								class="user-address-sex" type="radio" name="sex" value="2" readonly="readonly"
								<c:if test="${userAddress.sex==2}"> checked</c:if> /><i
								class="icon-radio"></i></label>女士
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">手机号码</div>
						<div class="item-input-wrap">
							<input id="user_address_telephone" placeholder="11位手机号"
								type="text" value="${userAddress.telephone}" readonly="readonly" />
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">地区</div>
						<div class="item-input-wrap">
							<input id="user_address_district_name" placeholder="地区信息"
								type="text" value="${userAddress.districtName}" readonly="readonly"/>
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">详细地址</div>
						<div class="item-input-wrap">
							<textarea id="user_address_address" class="value-not-null"
								placeholder="街道门牌号信息" readonly="readonly">${userAddress.address}</textarea>
						</div>
					</div>
				</li>
				<li class="item-content item-input" style="padding-left: 0;">
					<div class="item-media"></div>
					<div class="item-inner">
						<div class="item-title item-label">默认地址</div>
						<div class="item-after">
							<label class="toggle toggle-init color-blue"> <input
								id="default_user_address" type="checkbox" readonly="readonly"
								<c:if test="${userAddress.state==2}"> checked</c:if> /> <span
								class="toggle-icon"></span>
							</label>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>