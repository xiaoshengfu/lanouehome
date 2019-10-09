<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page" data-name="user-point-details">
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="left">
				<a class="link back" href="#"> <i class="icon icon-back"> </i> <span
					class="ios-only">返回</span>
				</a>
			</div>
			<div class="title">积分明细</div>
		</div>
	</div>
	<div class="page-content">
		<div class="list media-list no-hairlines no-hairlines-between"
			style="margin-top: 21px">
			<ul>
				<c:forEach var="userPointsDetails" items="${userPointsDetailsList}">
					<li>
						<div class="item-content">
							<div class="item-inner">
								<div class="item-title-row">
									<div class="item-title">
										<c:choose>
											<c:when test="${userPointsDetails.source==1}">
											废品订单完成奖励
											</c:when>
											<c:otherwise>
											积分商城购买商品
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="item-subtitle">
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${userPointsDetails.createTime}" />
								</div>
							</div>
							<div class="item-media" style="margin-right: 15px">
								<c:choose>
									<c:when test="${userPointsDetails.type==1}">
										<p style="margin-top: 13px;margin-bottom: 8px;">
											-${userPointsDetails.num}
									</c:when>
									<c:otherwise>
										<p style="margin-top: 13px;margin-bottom: 8px; color: #ff3b30">
											+${userPointsDetails.num}
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