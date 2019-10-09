<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page" data-name="waste-details">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a href="#"
					class="link back<c:if test="${waste.level==1}"> back_index</c:if>">
					<i class="icon icon-back"></i> <span class="ios-only">返回</span>
				</a>
			</div>
			<div class="title">${waste.name}</div>
		</div>
	</div>
	<div class="page-content">
		<div class="row no-gap" style="text-align: center;margin-top: 3px;">
			<div class="col-100" style="margin-top: 2rem">
				<img class="waste-image3" src="${waste.pictureUrl}">
				<div>${waste.name}</div>
			</div>
		</div>
		<input id="waste-id" type="hidden" value="${waste.wasteId}" /> <input
			id="unit-point" type="hidden" value="${waste.unitPoint}" /> <input
			id="estimate-unit-price" type="hidden"
			value="${waste.estimateUnitPrice}" />
		<div class="list inline-labels no-hairlines-md">
			<ul>
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-title item-label">
							<input id="unit" type="hidden" value="${waste.unit}"> 数量(
							<c:choose>
								<c:when test="${waste.unit==1}">斤</c:when>
								<c:when test="${waste.unit==2}">公斤</c:when>
								<c:when test="${waste.unit==3}">吨</c:when>
								<c:when test="${waste.unit==4}">台</c:when>
								<c:when test="${waste.unit==5}">部</c:when>
								<c:when test="${waste.unit==6}">个</c:when>
								<c:otherwise>斤</c:otherwise>
							</c:choose>
							)
						</div>
						<div class="item-input-wrap">
							<input id="estimate-num" type="text" placeholder="请填写数量"
								value="1"> <span class="input-clear-button"></span>
						</div>
					</div>
				</li> ${wasteAttribute.attributeContent}
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-title item-label">绿色积分</div>
						<div class="item-input-wrap">
							<input id="estimate-point" style="color: #ff3b30;" type="text"
								value="${waste.unitPoint}" readonly="readonly">
						</div>
					</div>
				</li>
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-title item-label">预估价格(元)</div>
						<div class="item-input-wrap">
							<input id="estimate-price" style="color: #ff3b30;" type="text"
								value="${waste.estimateUnitPrice/100}" readonly="readonly">
						</div>
					</div>
				</li>
			</ul>
		</div>
		<button id="add_basket_button"
			class="col button button-fill color-red"
			style="width:248px;height:38px;margin:auto;border-radius:30px;background-color:#007aff;color:#fff;">加入废品筐</button>
	</div>
</div>