<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page" data-name="account-and-security">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a href="#" class="link back"> <i class="icon icon-back"></i> <span
					class="ios-only">返回</span>
				</a>
			</div>
			<div class="title">账号与安全</div>
			<div class="right"></div>

		</div>
	</div>
	<div class="page-content" style="margin-top: -1rem">
		<div class="list">
			<ul>
				<li><a href="#" class="item-link item-content">
						<div class="item-inner">
							<div class="item-title">账号</div>
							<div class="item-after">${user.userId}</div>
						</div>
				</a></li>
				<li><a href="#" class="item-link item-content">
						<div class="item-inner">
							<div class="item-title">绑定手机号</div>
							<div class="item-after">${user.telephone}</div>
						</div>
				</a></li>
				<li><a href="/user/settings/payment_password/" class="item-link item-content">
						<div class="item-inner">
							<div class="item-title">支付密码</div>
						</div>
				</a></li>
			</ul>
		</div>
	</div>
</div>