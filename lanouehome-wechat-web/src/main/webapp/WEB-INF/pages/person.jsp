<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page" data-name="person">
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="title">个人中心</div>
		</div>
	</div>
	<div class="page-content" style="margin-top: -1rem">
		<div class="list">
			<ul>
				<li><a
					href="<c:choose><c:when test="${empty user}">/user/login/page/</c:when><c:otherwise>/user/user_information/?uuid=</c:otherwise></c:choose>"
					class="item-link item-content">
						<div class="item-media">
							<img alt="" src="${wxMpUser.headImgUrl}" height="60rem" />
						</div>
						<div class="item-inner">
							<div class="item-title">
								<c:choose>
									<c:when test="${empty user}">未登录</c:when>
									<c:otherwise> 用户${user.telephone}</c:otherwise>
								</c:choose>
							</div>
						</div>
				</a></li>
			</ul>
		</div>
		<div class="list" style="margin-top: -1rem">
			<ul>
				<li><a href="/user/balance/" class="item-link item-content">
						<div class="item-media">
							<i class="material-icons ios-only" style="color:#1874CD">account_balance_wallet</i>
						</div>
						<div class="item-inner">
							<div class="item-title">我的钱包</div>
						</div>
				</a></li>
			</ul>
		</div>
		<div class="list" style="margin-top: -1rem">
			<ul>
				<li><a href="/user/waste_order/list/"
					class="item-link item-content">
						<div class="item-media">
							<i class="material-icons ios-only" style="color:#CDAD00">event_note</i>
						</div>
						<div class="item-inner">
							<div class="item-title">订单管理</div>
							<div class="item-after">
								<c:if test="${!empty newWasteOrderNum&&newWasteOrderNum!=0}">
									<span id="new_waste_order_num" class="badge color-red">${newWasteOrderNum}</span>
								</c:if>
							</div>
						</div>
				</a></li>
				<li><a href="/user/point/" class="item-link item-content">
						<div class="item-media">
							<i class="material-icons ios-only" style="color:#458B74">credit_card</i>
						</div>
						<div class="item-inner">
							<div class="item-title">积分余额</div>
						</div>
				</a></li>
				<li><a href="/user/user_address/list/"
					class="item-link item-content">
						<div class="item-media">
							<i class="material-icons ios-only" style="color:#1b6d85">business</i>
						</div>
						<div class="item-inner">
							<div class="item-title">地址管理</div>
						</div>
				</a></li>
				<li><a href="/user/notification/list/"
					class="item-link item-content">
						<div class="item-media">
							<i class="material-icons ios-only" style="color:#CD4F39">notifications</i>
						</div>
						<div class="item-inner">
							<div class="item-title">系统通知</div>
							<div class="item-after">
								<c:if test="${!empty notReadNotificationNum&&notReadNotificationNum!=0}">
									<span id="not_read_notification_num" class="badge color-red">${notReadNotificationNum}</span>
								</c:if>
							</div>
						</div>
				</a></li>
			</ul>
		</div>
		<div class="list" style="margin-top: -1rem">
			<ul>
				<li><a href="/user/settings/" class="item-link item-content">
						<div class="item-media">
							<i class="material-icons ios-only" style="color:#515151">settings</i>
						</div>
						<div class="item-inner">
							<div class="item-title">设置</div>
						</div>
				</a></li>
			</ul>
		</div>
	</div>
</div>
