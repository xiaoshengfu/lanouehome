<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page" data-name="point-item">
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="left">
				<a href="#" class="link back"> <i class="icon icon-back"></i> <span
					class="ios-only">返回</span>
				</a>
			</div>
			<div class="title">商品详情</div>
		</div>
	</div>
	<div class="page-content">
		<div data-pagination='{"el": ".swiper-pagination"}'
			data-space-between="50"
			class="swiper-container swiper-init demo-swiper">
			<div class="swiper-pagination"></div>
			<div class="swiper-wrapper">
				<c:forEach items="${fn:split(itemDesc.pictureUrl, ',') }"
					var="pic_url">
					<div class="swiper-slide">
						<img src="${imageServiceUrl}${pic_url}" width="100%" />
					</div>
				</c:forEach>
			</div>
		</div>
		<div style="margin-top: -20px;font-size: 15px">${itemDesc.name}</div>
		<div
			style="margin-top: 10px;margin-left:6px; margin-bottom:10px; font-size: 23px;font-family: Helvetica Neue;color: #ff5000">
			<span style="margin-left: 3px">${itemDesc.price}</span><span
				style="margin-left: 3px">F</span>
		</div>
		<div class="row module-adds">
			<div class="col-33" style="text-align: left;">快递: 0.00</div>
			<div class="col-33" style="text-align: center;">月兑换160件</div>
			<div class="col-33" style="text-align: right;">库存${itemDesc.stock}件</div>
		</div>
		<div class="description">${itemDesc.description}</div>
		<div style="position:fixed; width:100%; bottom:0rem; z-index:9999;">
			<div class="row no-gap">
				<div class="col-70" style="background-color:#fff">
					<p class="text-align-right">
						合计：<span style="color:#ff3b30;font-weight:700;font-size:14px;">
							<fmt:formatNumber type="number" value="${itemDesc.price}"
								pattern="#0.00" />积分
						</span>
					</p>
				</div>
				<div class="col-5" style="background-color:#fff">
					<p class="text-align-right">&nbsp;</p>
				</div>
				<c:choose>
					<c:when test="${itemDesc.state==1}">
						<div id="item_confirm" class="col-25"
							style="background-color:#2196f3">
							<p class="text-align-center" style="color:#fff;">确认兑换</p>
						</div>
					</c:when>
					<c:when test="${itemDesc.state==2}">
						<div id="item_confirm" class="col-25"
							style="background-color:#8e8e93">
							<p class="text-align-center" style="color:#fff;">已下架</p>
						</div>
					</c:when>
					<c:when test="${itemDesc.state==3}">
						<div id="item_confirm" class="col-25"
							style="background-color:#8e8e93">
							<p class="text-align-center" style="color:#fff;">库存不足</p>
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</div>