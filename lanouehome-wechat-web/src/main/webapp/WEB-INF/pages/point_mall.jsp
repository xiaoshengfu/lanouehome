<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page" data-name="point-mall">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="title">积分商城</div>
		</div>
	</div>
	<div class="page-content infinite-scroll-content">
		<div class="list media-list no-hairlines no-hairlines-between"
			style="margin-top: 20px;">
			<div class="row" style="padding-left: 0.65rem">
				<c:forEach items="${itemDescList}" var="itemDesc"
					varStatus="varState">
					<div class="item-inner col-50"
						style="padding-top: 0px;<c:if test="${varState.index%2==1}">margin-right: 0.5rem;</c:if>">
						<div class="item-media"
							style="padding-top: 0px;">
							<a href="/point/mall/item/${itemDesc.itemId}/"> <img alt=""
								src="${imageServiceUrl}${fn:split(itemDesc.pictureUrl, ',')[0]}"
								width="165" height="165" class="lazy lazy-fade-in" />
							</a>
						</div>
						<div class="item-subtitle" style="font-size: 13px;">${itemDesc.name}</div>
						<div class="item-title-row">
							<div class="item-title" style="color: #007aff;font-size: 15px;">
								<span style="font-size: 10px;">￥</span>00.00
							</div>
							<div class="item-after"
								style="font-size: 10px;font-weight: bold;">
								<fmt:formatNumber type="number" value="${itemDesc.price}"
									pattern="#0.00" />
								积分
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="preloader infinite-scroll-preloader"></div>
</div>
</div>