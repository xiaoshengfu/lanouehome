<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page" data-name="notification-details">
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="left">
				<a href="#" id="back_notification_list" class="link"> <i
					class="icon icon-back"></i> <span class="ios-only">返回</span>
				</a>
			</div>
			<div class="title">通知详情</div>
		</div>
	</div>
	<div class="page-content">
		<div class="block block-strong">
			<div class="item-inner">
				<div class="item-title-row">
					<div class="item-title" style="font-size: 17px;font-weight: bold;">通知</div>
				</div>
				<div class="item-subtitle" style="margin-top: 5px">${notification.notificationContent}</div>
				<div class="item-text" style="margin-top: 5px">
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
						value="${notification.createTime}" />
				</div>
			</div>
		</div>
	</div>
</div>
