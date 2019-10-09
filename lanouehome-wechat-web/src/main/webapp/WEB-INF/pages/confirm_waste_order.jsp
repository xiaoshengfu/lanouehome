<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page" data-name="confirm-waste-order">
	<div class="popup popup-user-address" id="user_address_popup">
		<div class="navbar">
			<div class="navbar-inner sliding">
				<div class="left">
					<a class="popup-close" href="#"> <span class="ios-only">
							取消 </span>
					</a>
				</div>
				<div class="title">选择上门回收地址</div>
				<div class="right">
					<a id="link_add_user_address" href="#">新增地址</a>
				</div>
			</div>
		</div>
		<div class="list media-list" style="margin: 0 0;">
			<ul id="select_address_list">
			</ul>
		</div>
	</div>
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="left">
				<a href="#" id="back_basket" class="link back"> <i
					class="icon icon-back"></i> <span class="ios-only">返回</span>
				</a>
			</div>
			<div class="title">确认订单</div>
		</div>
	</div>
	<div class="page-content">
		<input id="waste_order_id_confirm" type="hidden"
			value="${wasteOrder.wasteOrderId}" /> <input
			id="user_address_id_selected" type="hidden"
			value="${defaultUserAddress.userAddressId}" /> <a
			style="display: none;" class="popup-open"
			data-popup=".popup-user-address" href="#"
			id="open_user_address_popup"></a>
		<!-- 地址栏 -->
		<div style="padding-bottom:3.0rem;">
			<div class="list media-list"
				style="margin-top:0px;margin-bottom:0px ;">
				<ul>
					<li><a id="address_popup" class="item-link item-content"
						href="#"> <i class="material-icons"> add_location </i>
							<div class="item-inner">
								<div class="row no-gap">
									<div class="col-5"></div>
									<div class="col-90">
										<div class="item-title-row">
											<div class="item-title" id="setname">
												卖家姓名：<span>${defaultUserAddress.name}</span>
											</div>
											<div class="item-after" id="setphone">${defaultUserAddress.telephone}</div>
										</div>
										<div class="item-subtitle" id="setdz">上门地址：${defaultUserAddress.address}</div>
										<div class="item-text">(请确认信息准确以确保回收员能联系到您)</div>
									</div>
									<div class="col-5"></div>
								</div>
							</div>
					</a></li>
				</ul>
			</div>
			<!-- 商品列表 -->
			<div class="list accordion-list" style="margin-top:0;margin-bottom:0">
				<ul>
					<li class="accordion-item accordion-item-opened"><a
						class="item-link item-content" href="#">
							<div class="item-inner">
								<div class="item-title">废品列表</div>
							</div>
					</a>
						<div class="accordion-item-content">
							<div class="list media-list">
								<ul>
									<c:forEach var="wasteOrderItem" items="${wasteOrderItemList}">
										<li><label class="item-checkbox item-content">
												<div class="item-inner">
													<div class="row no-gap">
														<div class="col-25">
															<img alt="" src="${wasteOrderItem.waste.pictureUrl}"
																width="70px" height="70px" />
														</div>
														<div class="col-75">
															<div class="item-title">${wasteOrderItem.waste.name}</div>
															<div class="item-texts" style="font-size: 12px;">
																${wasteOrderItem.description}</div>
															<div class="item-row item_foot">
																<div class="item-title">￥${(wasteOrderItem.estimateUnitPrice+wasteOrderItem.attributePrice)/100}</div>
																<div class="item-after" style="color: #f00">x${wasteOrderItem.estimateNum}</div>
															</div>
														</div>
													</div>
												</div>
										</label></li>
									</c:forEach>
								</ul>
							</div>
						</div></li>
				</ul>
			</div>
			<div class="list inline-labels no-hairlines-md"
				style="margin-top:0;margin-bottom:0">
				<ul>
					<li class="item-content item-input" style="padding-left: 0;">
						<div class="item-media"></div>
						<div class="item-inner">
							<div class="item-title item-label">预约时间:</div>
							<div class="item-input-wrap">
								<input id="picker-reserve-time-toolbar" placeholder="请选择预约上门日期"
									readonly="readonly" type="text" />
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="list inline-labels no-hairlines-md"
				style="margin-top:0;margin-bottom:0">
				<ul>
					<li class="item-content item-input" style="padding-left: 0;">
						<div class="item-media"></div>
						<div class="item-inner">
							<div class="item-title item-label">卖家留言:</div>
							<div class="item-input-wrap">
								<textarea id="user_message" placeholder="选填"></textarea>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div style="position:fixed; width:100%; bottom:0rem; z-index:9999;">
				<div class="row no-gap">
					<div class="col-70" style="background-color:#fff">
						<p class="text-align-right">
							合计：￥ <span style="color:#ff3b30;font-weight:700;font-size:14px;">
								${wasteOrder.estimatePrice/100} </span>
						</p>
					</div>
					<div class="col-5" style="background-color:#fff">
						<p class="text-align-right">&nbsp;</p>
					</div>
					<div id="waste_order_confirm" class="col-25"
						style="background-color:#8e8e93">
						<p class="text-align-center" style="color:#fff;">确认订单</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>