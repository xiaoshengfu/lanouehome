<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Security-Policy"
	content="default-src * 'self' 'unsafe-inline' 'unsafe-eval' data: gap: content:">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui, viewport-fit=cover">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<meta name="theme-color" content="#2196f3">
<meta name="format-detection" content="telephone=no">
<meta name="msapplication-tap-highlight" content="no">
<title>蓝鸥e家</title>
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<link rel="stylesheet"
	href="/resources/plugins/framework7/css/framework7.min.css">
<link rel="stylesheet" href="/resources/css/icons.css">
<link rel="stylesheet"
	href="/resources/plugins/swiper/css/swiper.min.css">
<link rel="stylesheet" type="text/css"
	href="/resources/css/miniMobile.css" />
<link rel="stylesheet" href="/resources/css/app.css">
</head>
<body>
	<div id="app">
		<div class="views tabs ios-edges">
			<div class="toolbar tabbar-labels toolbar-bottom-md"
				id="bottom_toolbar">
				<div class="toolbar-inner">
					<a href="#view-home" class="tab-link tab-link-active"> <i
						class="icon f7-icons ios-only">home</i> <i
						class="icon f7-icons ios-only icon-ios-fill">home_fill</i> <i
						class="icon material-icons md-only">home</i> <span
						class="tabbar-label">首页</span>
					</a> <a href="#view-basket" class="tab-link" id="a_basket"> <i
						class="icon f7-icons ios-only">box
							<div id="basket_waste_num_div">
								<c:if
									test="${!empty basketWasteOrderItemNum&&basketWasteOrderItemNum!=0}">
									<span id="basket_waste_num" class="badge color-red">${basketWasteOrderItemNum}</span>
								</c:if>
							</div>
					</i> <i class="icon f7-icons ios-only icon-ios-fill">box_fill
							<div id="basket_waste_num_div_fill">
								<c:if
									test="${!empty basketWasteOrderItemNum&&basketWasteOrderItemNum!=0}">
									<span id="basket_waste_num_fill" class="badge color-red">${basketWasteOrderItemNum}</span>
								</c:if>
							</div>
					</i> <i class="icon material-icons md-only">view_list</i><span
						class="tabbar-label">回收筐</span>
					</a> <a href="#view-point-mall" class="tab-link"> <i
						class="icon f7-icons ios-only">bag</i> <i
						class="icon f7-icons ios-only icon-ios-fill">bag_fill</i> <i
						class="icon material-icons md-only">bag</i> <span
						class="tabbar-label">积分商城</span>
					</a><a href="#view-person" class="tab-link" id="a_person"> <i
						class="icon f7-icons ios-only">person<span id="totalNewNum"></span>
					</i> <i class="icon f7-icons ios-only icon-ios-fill">person_fill<span
							id="totalNewNum_fill"></span>
					</i> <i class="icon material-icons md-only">person</i> <span
						class="tabbar-label">个人中心</span>
					</a>
				</div>
			</div>
			<div id="view-home" class="view view-main tab tab-active">
				<div class="page" data-name="home">
					<div class="navbar">
						<div class="navbar-inner">
							<div class="title sliding">首页</div>
						</div>
					</div>
					<input id="userOpenid" type="hidden" value="${userOpenid}" />
					<!-- 首页 -->
					<div class="page-content">
						<div class="swiper-container">
							<div class="swiper-wrapper">
								<c:forEach var="carouselFigure" items="${carouselFigureList}">
									<div class="swiper-slide">
										<img src="${carouselFigure.pic}" width="100%" />
									</div>
								</c:forEach>
							</div>
							<div class="swiper-pagination"></div>
						</div>
						<div class="row no-gap"
							style="text-align: center;margin-top: -3px;">
							<c:forEach var="waste" items="${wasteList}"
								varStatus="wasteListVarStatus">
								<div class="col-25 waste-item">
									<c:choose>
										<c:when test="${waste.isParent}">
											<a href="/waste/find_child/list/${waste.wasteId}/">
										</c:when>
										<c:otherwise>
											<a href="/waste/details/${waste.wasteId}/">
										</c:otherwise>
									</c:choose>
									<img class="waste-image" src="${waste.pictureUrl}" />
									<div class="waste-name">${waste.name}</div>
									</a>
								</div>
								<c:if test="${wasteListVarStatus.last}">
									<c:forEach var="i" begin="1"
										end="${4-wasteListVarStatus.count % 4}">
										<div class="col-25"></div>
									</c:forEach>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<!-- 回收筐 -->
			<div id="view-basket" class="view tab"></div>
			<!-- 积分商城 -->
			<div id="view-point-mall" class="view tab"></div>
			<!-- 个人中心-->
			<div id="view-person" class="view tab"></div>
		</div>
	</div>
	<script src="/resources/plugins/jquery/jquery-3.3.1.min.js"></script>
	<script src="/resources/plugins/jquery_cookie/jquery.cookie.js"></script>
	<script src="/resources/plugins/framework7/js/framework7.min.js"></script>
	<script src="/resources/plugins/swiper/js/swiper.min.js"></script>
	<script src="/resources/js/routes.js"></script>
	<script src="/resources/js/webKeyBoard.js"></script>
	<script src="/resources/js/app.js"></script>
	<script>
		var swiper = new Swiper('.swiper-container', {
			pagination : {
				el : '.swiper-pagination',
			},
			autoplay : {
				delay : 2000,
				disableOnInteraction : false,
			},
		});
	</script>
</body>
</html>
