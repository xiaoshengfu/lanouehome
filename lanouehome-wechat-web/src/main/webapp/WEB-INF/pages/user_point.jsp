<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page" data-name="user-point">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="link back back_index" href="#"> <i class="icon icon-back"> </i> <span
					class="ios-only"> 返回 </span>
				</a>
			</div>
			<div class="title">绿色积分</div>
			<div class="right">
				<a href="/user/point/details/list/">积分明细</a>
			</div>
		</div>
	</div>
	<div class="page-content" style="background-color: #f7f7f8;">
		<div class="row"
			style="margin-left: 20px; margin-right: 20px;margin-top: 15px; font-size: 50px;">
			<div class="col">
				<p class="text-align-center"
					style="margin-top:10px;margin-bottom: 0px;">
					<img alt="" src="/resources/images/point.png" style="height: 103px;width: 103px;" />
				</p>
			</div>
		</div>
		<div class="row"
			style="margin-left: 20px; margin-right: 20px;font-size: 50px;">
			<div class="col">
				<p class="text-align-center"
					style="margin-top: 0px;margin-bottom: 0px;font-size: 18px;">
					绿色积分</p>
				<p class="text-align-center"
					style="margin-top: 5px;margin-bottom: 0px;font-size: 45px;font-family: '微软雅黑';">
					${userPoint.points}F</p>
				<p class="row text-align-center"
					style="margin-top: 10px;margin-bottom: 0px;font-size: 45px;font-family: '微软雅黑';">
					<button class="col button color-black">积分兑换</button>
					<button class="col button color-black">赚取积分</button>
				</p>
			</div>
		</div>
		<div class="list  no-hairlines no-hairlines-between"
			style="margin-top: 20px">
			<ul>
				<li><a href="#" class="item-link  item-content">
						<div class="item-inner">
							<div class="item-title">热门积分商品兑换</div>
							<div class="item-after">更多</div>
						</div>
				</a></li>
				<li class="media-list">
					<div class="item-content">
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt="" data-src="http://39.107.226.68/group1/M00/00/00/wKgBe1rNy0aAOrOcAAJgxTJTdi8273.png" width="165" height="165"
									class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after" style="font-size: 10px;font-weight: bold;font-weight: bold;">600积分</div>
							</div>
						</div>
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt="" data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNy0yAIcj3AAJ-NtzspLI182.png" width="165" height="165"
									class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after" style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
					</div>
				</li>
				<li class="media-list">
					<div class="item-content">
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt="" data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNy9mAU7zhAADGYjvJd_c299.png" width="165" height="165"
									class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after" style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt="" data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNzCyAKxQ-AAFAz5GW-xw414.png" width="165" height="165"
									class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after" style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
					</div>
				</li>
				<li class="media-list">
					<div class="item-content">
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt="" data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNzEqAVG2KAATbF-yLkUg630.png" width="165" height="165"
									class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after" style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNzGKAcflPAAHqtxY3itU334.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after" style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>

