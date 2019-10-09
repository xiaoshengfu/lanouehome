<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page" data-name="notification-list">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="link back back_index" href="#"> <i
					class="icon icon-back"> </i> <span class="ios-only"> 返回 </span>
				</a>
			</div>
			<div class="title">系统通知</div>
			<div class="right"><a id="all_change_read" class="link" href="#">全部已读</a></div>
		</div>
	</div>
	<div class="page-content" style="background-color: #f7f7f8;">
		<div class="list media-list no-hairlines" style="margin-top: 0px">
			<ul>
				<c:forEach var="notification" items="${notificationList}">
					<li class="swipeout"><a href="/user/notification/details/${notification.notificationId}/"
						class="item-content swipeout-content item-link"> 
						    <c:choose>
								<c:when test="${notification.isRead}">
									<div class="item-inner" style="color: #8e8e93">
										<div class="item-title-row">
											<div class="item-title">通知</div>
											<div class="item-after">已读</div>
										</div>
								</c:when>
								<c:otherwise>
									<div class="item-inner">
										<div class="item-title-row">
											<div class="item-title">通知</div>
											<div class="item-after">未读</div>
										</div>
								</c:otherwise>
							</c:choose>
							<div class="item-subtitle">${notification.notificationContent}</div>
							<div class="item-text">
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
									value="${notification.createTime}" />
							</div>
		</div>
		</a>
		<div class="swipeout-actions-right">
			<a href="#" data-confirm="确定要删除这条通知?" class="swipeout-delete">删除</a>
		</div>
		</li>
		</c:forEach>
		</ul>
	</div>
</div>
</div>