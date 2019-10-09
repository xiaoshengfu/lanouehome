<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page" data-name="user-balance-details">
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="left">
				<a class="link back" href="#"> <i
					class="icon icon-back"> </i> <span class="ios-only">返回</span>
				</a>
			</div>
			<div class="title">余额明细</div>
		</div>
	</div>
	<div class="page-content">
		<div class="list media-list no-hairlines no-hairlines-between"
			style="margin-top: 21px">
			<ul>
				<c:forEach var="userBalanceDetails"
					items="${userBalanceDetailsList}">
					<li>
						<div class="item-content">

							<div class="item-inner">
								<div class="item-title-row">
									<div class="item-title">
										<c:choose>
											<c:when test="${userBalanceDetails.source==1}">
											完成废品回收订单
											</c:when>
											<c:otherwise>
											余额提现
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="item-subtitle">
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${userBalanceDetails.createTime}" />
								</div>
							</div>
							<div class="item-media" style="margin-right: 15px">
								<c:choose>
									<c:when test="${userBalanceDetails.type==1}">
										<p style="margin-top: 13px;margin-bottom: 8px;">
											-${userBalanceDetails.num/100}
									</c:when>
									<c:otherwise>
										<p style="margin-top: 13px;margin-bottom: 8px; color: #ff3b30">
											+${userBalanceDetails.num/100}
									</c:otherwise>
								</c:choose>
								</p>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>