<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page" data-name="user-information">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a href="#" class="link back back_index"> <i class="icon icon-back"></i> <span
					class="ios-only">返回</span>
				</a>
			</div>
			<div class="title">个人信息</div>
			<div class="right"></div>
		</div>
	</div>
	<div class="page-content" style="margin-top: -1rem">
		<div class="list">
			<ul>
				<li>
					<div class="item-inner">
						<div class="item-title" style="padding-left: 1rem">头像</div>
						<div class="item-after">
							<img alt="" src="${wxMpUser.headImgUrl}" height="60rem" />
						</div>
					</div>
				</li>
				<li>
					<div class="item-inner">
						<div class="item-title" style="padding-left: 1rem">账号</div>
						<div class="item-after">${user.userId}</div>
					</div>
				</li>
				<li>
					<div class="item-inner">
						<div class="item-title" style="padding-left: 1rem">昵称</div>
						<div class="item-after">${wxMpUser.nickname}</div>
					</div>
				</li>
				<li>
					<div class="item-inner">
						<div class="item-title" style="padding-left: 1rem">电话</div>
						<div class="item-after">${user.telephone}</div>
					</div>
				</li>
			</ul>
		</div>
		<button id="user_sign_out" class="col button button-fill color-red back_index"
			style="width:248px;height:38px;margin:auto;border-radius:30px;color:#fff;">退出登录</button>
	</div>
</div>