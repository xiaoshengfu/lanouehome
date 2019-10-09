<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page" data-name="user-address-list">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="link back back_index" href="#"> <i
					class="icon icon-back"> </i> <span class="ios-only"> 返回 </span>
				</a>
			</div>
			<div class="title">地址管理</div>
			<div class="right"></div>
		</div>
	</div>
	<div class="page-content">
		<div class="address-body">
			<c:forEach var="userAddress" items="${userAddressList}">
				<div class="list media-list" style="margin: 3px 0;">
					<ul>
						<li><a href="/user/user_address/details/${userAddress.userAddressId}/" class="item-link item-content">
								<div class="item-inner">
									<div class="item-title-row">
										<div class="item-title">${userAddress.name}</div>
										<div class="item-after" style="color: black;">${userAddress.telephone}</div>
									</div>
									<div class="item-text" style="font-size: 12px;">${userAddress.address}</div>
								</div>
						</a></li>
						<li>
							<div class="item-content">
								<label class="radio"><input
									class="setdefault-user-address" name="default_user_address"
									type="radio"
									<c:if test="${userAddress.state==2}">checked</c:if>
									value="${userAddress.userAddressId}" /><i
									style="height: 16px; width: 16px; margin-right: 6px;"
									class="icon-radio"></i></label>
								<div class="item-inner"
									style="padding-top: 14px; padding-bottom: 14px; font-size: 12px;">
									<div class="item-title-row">
										<div class="item-title">默认地址</div>
										<div class="item-after" style="color: black;">
											<span class="user-address-edit"
												address_id="${userAddress.userAddressId}"><i
												class="f7-icons" style="font-size: 14px;">compose</i> 编辑</span>
											&nbsp;&nbsp;&nbsp; <span class="user-address-delete"
												address_id="${userAddress.userAddressId}"><i
												class="f7-icons" style="font-size: 14px;">trash</i> 删除</span>
										</div>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</c:forEach>
		</div>
		<div class="footer">
			<p class="row">
				<a href="/user/user_address/add/page/"
					style="border-radius: 30px;height: 39px;line-height: 39px;font-size: 15px"
					class="col button button-big button-fill">添加新地址</a>
			</p>
		</div>
	</div>
</div>