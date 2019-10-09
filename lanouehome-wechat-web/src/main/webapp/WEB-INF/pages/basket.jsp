<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page" data-name="basket">
	<div class="navbar">
		<div class="navbar-inner sliding">
			<div class="title">回收筐</div>
			<div class="right">
				<a class="deleteall" href="#"><span id="basket_manager"
					class="ios-only">管理</span> </a>
			</div>
		</div>
	</div>
	<div class="page-content">
		<div style="padding-bottom: 3.0rem;">
			<div class="card">
				<div class="card-content">
					<div class="list media-list">
						<ul>
							<li><label class="item-checkbox item-content"> <input
									id="qxi" name="demo-media-checkbox" type="checkbox" value="1" />
									<i class="icon icon-checkbox"> </i>
									<div class="item-inner">
										<div class="row no-gap">
											<div class="item-subtitle">全选</div>
										</div>
									</div>
							</label></li>
						</ul>
					</div>
				</div>
			</div>
			<c:forEach var="wasteOrderItem" items="${wasteOrderItemList}">
				<div class="card">
					<div class="card-content">
						<div class="list media-list">
							<ul>
								<li class="swipeout">
									<div class="swipeout-content">
										<label class="item-checkbox item-content"> <input
											class="dx" name="demo-media-checkbox" type="checkbox"
											value="${wasteOrderItem.orderItemId}"
											estimateNum="${wasteOrderItem.estimateNum}"
											estimateUnitPrice="${wasteOrderItem.estimateUnitPrice}" /> <i
											class="icon icon-checkbox"> </i>
											<div class="item-inner">
												<div class="row no-gap">
													<div class="col-33">
														<div class="item-media">
															<img alt="" src="${wasteOrderItem.waste.pictureUrl}"
																width="80px" height="80px" />
														</div>
													</div>
													<div class="col-66" style="margin-top: 10px">
														<div class="item-title">${wasteOrderItem.waste.name}</div>
														<div class="item-texts"
															style="font-size: 12px;margin-top: 5px">
															${wasteOrderItem.description}</div>
														<div class="item-row item_foot" style="margin-top: 11px">
															<div class="item-title">￥${(wasteOrderItem.estimateUnitPrice+wasteOrderItem.attributePrice)/100}</div>
															<div class="item-after"
																style="color:#ff0000;font-weight:700;">x${wasteOrderItem.estimateNum}</div>
														</div>
													</div>
												</div>
											</div>
										</label>
									</div>
									<div class="swipeout-actions-right">
										<a id="delete${wasteOrderItem.orderItemId}"
											itemId="${wasteOrderItem.orderItemId}"
											class="swipeout-delete order-item-delete" href="#"> 删除 </a>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</c:forEach>
			<div id="footer"
				style="position:fixed; width:100%; bottom:3rem; z-index:9999;">
				<div class="row no-gap">
					<div class="col-70" style="background-color:#fff">
						<p class="text-align-right">
							合计：￥ <span id="totalMoney"
								style="color:#ff3b30;font-weight:700;font-size:14px;">0.00</span>
						</p>
					</div>
					<div class="col-5" style="background-color:#fff">
						<p class="text-align-right">&nbsp;</p>
					</div>
					<div id="waste_order_submit" class="col-25"
						style="background-color:#8e8e93">
						<p id="settlement_num" class="text-align-center"
							style="color:#fff;">结算(0)</p>
					</div>
				</div>
			</div>
			<div id="footer2"
				style="display: none;position:fixed; width:100%; bottom:3rem; z-index:9999;">
				<div class="row no-gap">
					<div class="col-75" style="background-color: #fff">
						<p class="text-align-right">&nbsp;</p>
					</div>
					<div id="waste_order_item_delete" class="col-25"
						style="background-color: #ff3b30">
						<p id="settlement_delete" class="text-align-center"
							style="color: #fff;">删除(0)</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>