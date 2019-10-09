<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page" data-name="confirm-waste-order-success">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="title">下单成功</div>
			<div class="right">帮助</div>
		</div>
	</div>
	<div class="page-content">
		<div class="list media-list no-hairlines no-hairlines-between"
			style="margin-top: 0px">
			<ul>
				<li class="item-content">
					<div class="item-inner">
						<p class="text-align-center">
							<i class="f7-icons" style="font-size: 100px; color: #007aff">check_round</i>
						<p>
					</div>
				</li>
				<li class="item-content">
					<div class="item-inner text-align-center" style="padding-top: 0px">
						<div class="item-title">下单成功</div>
						<div class="item-subtitle" style="color: #8e8e93;font-size: 13px">订单号:${wasteOrder.wasteOrderId}</div>
						<div class="item-subtitle" style="color: #8e8e93;font-size: 13px">
							下单时间:
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
								value="${wasteOrder.confirmTime}" />
						</div>
						<div class="item-subtitle" style="color: #8e8e93;font-size: 13px">预计金额:￥${wasteOrder.estimatePrice/100}&nbsp;&nbsp;&nbsp;&nbsp;预计积分:${wasteOrder.estimatePoint}</div>
					</div>
				</li>
				<li class="item-content">
					<div class="item-inner text-align-center">
						<div class="row">
							<div class="col-10">&nbsp;</div>
							<div class="col-35">
								<button id="confirm_success_find_order"
									class="col button color-black">查看订单</button>
							</div>
							<div class="col-10">&nbsp;</div>
							<div class="col-35">
								<a class="external" href="/wechat/index"><button
										class="col button color-black">返回首页</button></a>
							</div>
							<div class="col-10">&nbsp;</div>
						</div>
					</div>
				</li>
				<li class="item-content">
					<div class="item-inner text-align-center"
						style="padding-top: 0px;line-height: 2.5">
						<div class="item-title">
							<i class="f7-icons" style="font-size: 15px; color: #007aff;">heart_fill</i>&nbsp;积分商品推荐
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-inner">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/00/wKgBe1rNy0aAOrOcAAJgxTJTdi8273.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
						<div class="item-inner">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNy0yAIcj3AAJ-NtzspLI182.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNy9mAU7zhAADGYjvJd_c299.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNzCyAKxQ-AAFAz5GW-xw414.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNzEqAVG2KAATbF-yLkUg630.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
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
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-inner">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/00/wKgBe1rNy0aAOrOcAAJgxTJTdi8273.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
						<div class="item-inner">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNy0yAIcj3AAJ-NtzspLI182.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNy9mAU7zhAADGYjvJd_c299.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNzCyAKxQ-AAFAz5GW-xw414.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-content">
						<div class="item-inner" style="padding-top: 0px;">
							<div class="item-media" style="padding-top: 0px;">
								<img alt=""
									data-src="http://39.107.226.68/group1/M00/00/01/wKgBe1rNzEqAVG2KAATbF-yLkUg630.png"
									width="165" height="165" class="lazy lazy-fade-in" />
							</div>
							<div class="item-subtitle" style="font-size: 13px;">三只松鼠</div>
							<div class="item-title-row">
								<div class="item-title" style="color: #007aff;font-size: 15px;">
									<span style="font-size: 10px;">￥</span>50.00
								</div>
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
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
								<div class="item-after"
									style="font-size: 10px;font-weight: bold;">600积分</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>
