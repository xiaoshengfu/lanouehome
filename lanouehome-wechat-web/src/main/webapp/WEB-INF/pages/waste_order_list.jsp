<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page" data-name="waste-order-list">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="link back back_index" href="#"> <i
					class="icon icon-back"> </i> <span class="ios-only"> 返回 </span>
				</a>
			</div>
			<div class="title">订单管理</div>
			<div class="right"></div>
			<div class="subnavbar">
				<div class="subnavbar-inner">
					<div class="segmented segmented-raised">
						<c:choose>
							<c:when test="${fn:length(wasteOrder2List)!=0}">
								<a class="button tab-link" href="#tab1">待确认</a>
								<a class="button tab-link tab-link-active" href="#tab2">待回收</a>
							</c:when>
							<c:otherwise>
								<a class="button tab-link tab-link-active" href="#tab1">待确认</a>
								<a class="button tab-link" href="#tab2">待回收</a>
							</c:otherwise>
						</c:choose>
						<a class="button tab-link" href="#tab3">已完成</a> <a
							class="button tab-link" href="#tab4">已失效</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="delete_waste_order_id" value="" />
	<div class="page-content hide-navbar-on-scroll">
		<div class="tabs">
			<c:choose>
				<c:when test="${fn:length(wasteOrder2List)!=0}">
					<div class="tab" id="tab1">
				</c:when>
				<c:otherwise>
					<div class="tab tab-active" id="tab1">
				</c:otherwise>
			</c:choose>

			<c:forEach var="wasteOrder1" items="${wasteOrder1List}">
				<div class="card" id="waste_order${wasteOrder1.wasteOrderId}">
					<div class="list accordion-list inline-labels no-harilines-md">
						<ul>
							<li>
								<div class="item-content">
									<div class="item-inner" style="font-size: 15px;">
										<div class="item-title item-label">订单编号</div>
										<div class="item-title">${wasteOrder1.wasteOrderId}</div>
										<div class="item-after">待确认</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item-content">
									<div class="item-inner">
										<div class="item-title item-label">创建时间</div>
										<div class="item-input-wrap">
											<div class="item-text">
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
													value="${wasteOrder1.createTime}" />
											</div>
										</div>
									</div>
								</div>
								<div class="item-content">
									<div class="item-inner">
										<div class="item-title item-label">预估价格</div>
										<div class="item-input-wrap">
											<div class="item-text">￥${wasteOrder1.estimatePrice/100}</div>
										</div>
									</div>
								</div>
								<div class="item-content">
									<div class="item-inner">
										<div class="item-title item-label">绿色积分</div>
										<div class="item-input-wrap">
											<div class="item-text">${wasteOrder1.estimatePoint}</div>
										</div>
									</div>
								</div>
							</li>
							<li class="accordion-item"><a class="item-link item-content"
								href="#">
									<div class="item-inner">
										<div class="item-title">废品列表</div>
									</div>
							</a>
								<div class="accordion-item-content">
									<div class="list media-list">
										<ul>
											<c:forEach var="wasteOrderItem1"
												items="${wasteOrder1.wasteOrderItems}">
												<li><label class="item-checkbox item-content">
														<div class="item-inner">
															<div class="row no-gap">
																<div class="col-25">
																	<img alt="" src="${wasteOrderItem1.waste.pictureUrl}"
																		width="70px" height="70px"/>
																</div>
																<div class="col-75">
																	<div class="item-title">
																		${wasteOrderItem1.waste.name}</div>
																	<div class="item-texts" style="font-size: 12px;">
																		${wasteOrderItem1.description}
																		<c:forEach var="i" begin="1"
																			end="${50-fn:length(wasteOrderItem1.description)}">
																				&nbsp;
																			</c:forEach>
																	</div>
																	<div class="item-row item_foot">
																		<div class="item-title">￥${(wasteOrderItem1.estimateUnitPrice+wasteOrderItem1.attributePrice)/100}</div>
																		<div class="item-after"
																			style="color:#ff0000;font-weight:700;">x${wasteOrderItem1.estimateNum}</div>
																	</div>
																</div>
															</div>
														</div>
												</label></li>
											</c:forEach>
										</ul>
									</div>
								</div></li>
							<li>
								<div class="item-content">
									<div class="item-inner">
										<div class="item-after">
											<button waste_order_id="${wasteOrder1.wasteOrderId}"
												class="button button-fill waste_order_list_confirm"
												style="font-size: 12px;">确认订单</button>
											<button waste_order_id="${wasteOrder1.wasteOrderId}"
												class="button button-fill waste_order_list_cancel"
												style="font-size: 12px; margin-left: 10px;background-color: #ff3b30">取消订单</button>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:choose>
			<c:when test="${fn:length(wasteOrder2List)!=0}">
				<div class="tab tab-active" id="tab2">
			</c:when>
			<c:otherwise>
				<div class="tab" id="tab2">
			</c:otherwise>
		</c:choose>
		<c:forEach var="wasteOrder2" items="${wasteOrder2List}">
			<div class="card">
				<div class="list accordion-list inline-labels no-harilines-md">
					<ul>
						<li>
							<div class="item-content">
								<div class="item-inner" style="font-size: 15px;">
									<div class="item-title item-label">订单编号</div>
									<div class="item-title">${wasteOrder2.wasteOrderId}</div>
									<div class="item-after">待回收</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">确认时间</div>
									<div class="item-input-wrap">
										<div class="item-text">
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${wasteOrder2.confirmTime}" />
										</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">预约时间</div>
									<div class="item-input-wrap">
										<div class="item-text">
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${wasteOrder2.reserveTime}" />
										</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">下单用户</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder2.userAddress.name}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">下单地址</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder2.userAddress.address}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">留言</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder2.message}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">预估价格</div>
									<div class="item-input-wrap">
										<div class="item-text">￥${wasteOrder2.estimatePrice/100}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">绿色积分</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder2.estimatePoint}</div>
									</div>
								</div>
							</div>
						</li>
						<li class="accordion-item"><a class="item-link item-content"
							href="#">
								<div class="item-inner">
									<div class="item-title">废品列表</div>
								</div>
						</a>
							<div class="accordion-item-content">
								<div class="list media-list">
									<ul>
										<c:forEach var="wasteOrderItem2"
											items="${wasteOrder2.wasteOrderItems}">
											<li><label class="item-checkbox item-content">
													<div class="item-inner">
														<div class="row no-gap">
															<div class="col-25">
																<img alt="" src="${wasteOrderItem2.waste.pictureUrl}"
																	width="70px" height="70px"/>
															</div>
															<div class="col-75">
																<div class="item-title">
																	${wasteOrderItem2.waste.name}</div>
																<div class="item-texts" style="font-size: 12px;">
																	${wasteOrderItem2.description}
																	<c:forEach var="i" begin="1"
																		end="${50-fn:length(wasteOrderItem2.description)}">
																				&nbsp;
																			</c:forEach>
																</div>
																<div class="item-row item_foot">
																	<div class="item-title">￥${(wasteOrderItem2.estimateUnitPrice+wasteOrderItem2.attributePrice)/100}</div>
																	<div class="item-after"
																		style="color:#ff0000;font-weight:700;">x${wasteOrderItem2.estimateNum}</div>
																</div>
															</div>
														</div>
													</div>
											</label></li>
										</c:forEach>
									</ul>
								</div>
							</div></li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">已分派回收员</div>
									<div class="item-input-wrap">
										<div class="item-text">
											<a href="#" class="tel-recycler" style="color: #007aff"
												telephone="${wasteOrder2.recycler.telephone}">
												${wasteOrder2.recycler.name}
												${wasteOrder2.recycler.telephone}</a>
										</div>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-after">
										<button waste_order_id="${wasteOrder2.wasteOrderId}"
											id="waste_order_list_confirm" class="button button-fill"
											style="font-size: 12px;">联系客服</button>
										<button waste_order_id="${wasteOrder2.wasteOrderId}"
											class="button button-fill waste_order_list_cancel"
											style="font-size: 12px; margin-left: 10px;background-color: #ff3b30">取消订单</button>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="tab" id="tab3">
		<c:forEach var="wasteOrder4" items="${wasteOrder4List}">
			<div class="card">
				<div class="list accordion-list inline-labels no-harilines-md">
					<ul>
						<li>
							<div class="item-content">
								<div class="item-inner" style="font-size: 15px;">
									<div class="item-title item-label">订单编号</div>
									<div class="item-title">${wasteOrder4.wasteOrderId}</div>
									<div class="item-after">已完成</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">确认时间</div>
									<div class="item-input-wrap">
										<div class="item-text">
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${wasteOrder4.confirmTime}" />
										</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">预约时间</div>
									<div class="item-input-wrap">
										<div class="item-text">
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${wasteOrder4.reserveTime}" />
										</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">下单用户</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder4.userAddress.name}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">下单地址</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder4.userAddress.address}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">留言</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder4.message}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">预估价格</div>
									<div class="item-input-wrap">
										<div class="item-text">￥${wasteOrder4.estimatePrice/100}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">绿色积分</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder4.estimatePoint}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">实际价格</div>
									<div class="item-input-wrap">
										<div class="item-text">￥${wasteOrder4.realPrice/100}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">实际所获积分</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder4.realPoint}</div>
									</div>
								</div>
							</div>
						</li>
						<li class="accordion-item"><a class="item-link item-content"
							href="#">
								<div class="item-inner">
									<div class="item-title">废品列表</div>
								</div>
						</a>
							<div class="accordion-item-content">
								<div class="list media-list">
									<ul>
										<c:forEach var="wasteOrderItem4"
											items="${wasteOrder4.wasteOrderItems}">
											<li><label class="item-checkbox item-content">
													<div class="item-inner">
														<div class="row no-gap">
															<div class="col-25">
																<img alt="" src="${wasteOrderItem4.waste.pictureUrl}"
																	width="70px" height="70px"/>
															</div>
															<div class="col-75">
																<div class="item-title">
																	${wasteOrderItem4.waste.name}</div>
																<div class="item-texts" style="font-size: 12px;">
																	${wasteOrderItem4.description}
																	<c:forEach var="i" begin="1"
																		end="${50-fn:length(wasteOrderItem4.description)}">
																				&nbsp;
																			</c:forEach>
																</div>
																<div class="item-row item_foot">
																	<div class="item-title">￥${(wasteOrderItem4.realUnitPrice)/100}</div>
																	<div class="item-after"
																		style="color:#ff0000;font-weight:700;">x${wasteOrderItem4.realNum}</div>
																</div>
															</div>
														</div>
													</div>
											</label></li>
										</c:forEach>
									</ul>
								</div>
							</div></li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-after">
										<a href="#" class="button" style="font-size: 12px;">联系客服</a> <a
											href="/" class="button button-fill"
											style="font-size: 12px; margin-left: 10px;">再来一单</a>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="tab" id="tab4">
		<c:forEach var="wasteOrder3" items="${wasteOrder3List}">
			<div class="card">
				<div class="list accordion-list inline-labels no-harilines-md">
					<ul>
						<li>
							<div class="item-content">
								<div class="item-inner" style="font-size: 15px;">
									<div class="item-title item-label">订单编号</div>
									<div class="item-title">${wasteOrder3.wasteOrderId}</div>
									<div class="item-after">已失效</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">失效原因</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder3.invalidReason}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">确认时间</div>
									<div class="item-input-wrap">
										<div class="item-text">
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${wasteOrder3.confirmTime}" />
										</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">预约时间</div>
									<div class="item-input-wrap">
										<div class="item-text">
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${wasteOrder3.reserveTime}" />
										</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">下单用户</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder3.userAddress.name}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">下单地址</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder3.userAddress.address}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">留言</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder3.message}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">预估价格</div>
									<div class="item-input-wrap">
										<div class="item-text">￥${wasteOrder3.estimatePrice/100}</div>
									</div>
								</div>
							</div>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-title item-label">绿色积分</div>
									<div class="item-input-wrap">
										<div class="item-text">${wasteOrder3.estimatePoint}</div>
									</div>
								</div>
							</div>
						</li>
						<li class="accordion-item"><a class="item-link item-content"
							href="#">
								<div class="item-inner">
									<div class="item-title">废品列表</div>
								</div>
						</a>
							<div class="accordion-item-content">
								<div class="list media-list">
									<ul>
										<c:forEach var="wasteOrderItem3"
											items="${wasteOrder3.wasteOrderItems}">
											<li><label class="item-checkbox item-content">
													<div class="item-inner">
														<div class="row no-gap">
															<div class="col-25">
																<img alt="" src="${wasteOrderItem3.waste.pictureUrl}"
																	width="70px" height="70px"/>
															</div>
															<div class="col-75">
																<div class="item-title">
																	${wasteOrderItem3.waste.name}</div>
																<div class="item-texts" style="font-size: 12px;">
																	${wasteOrderItem3.description}
																	<c:forEach var="i" begin="1"
																		end="${50-fn:length(wasteOrderItem4.description)}">
																				&nbsp;
																			</c:forEach>
																</div>
																<div class="item-row item_foot">
																	<div class="item-title">￥${(wasteOrderItem3.estimateUnitPrice+wasteOrderItem3.attributePrice)/100}</div>
																	<div class="item-after"
																		style="color:#ff0000;font-weight:700;">x${wasteOrderItem3.estimateNum}</div>
																</div>
															</div>
														</div>
													</div>
											</label></li>
										</c:forEach>
									</ul>
								</div>
							</div></li>
						<li>
							<div class="item-content">
								<div class="item-inner">
									<div class="item-after">
										<a href="#" class="button" style="font-size: 12px;">联系客服</a> <a
											href="/" class="button button-fill"
											style="font-size: 12px; margin-left: 10px;">再来一单</a>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
</div>
</div>