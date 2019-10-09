<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="page" data-name="user-login">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="link back back_index" href="#"> <i
					class="icon icon-back"> </i> <span class="ios-only"> 返回 </span>
				</a>
			</div>
			<div class="title">登录</div>
			<div class="right">帮助</div>
		</div>
	</div>
	<div class="page-content" style="background-color: #f7f7f8;">
		<div class="row"
			style="margin-left: 20px; margin-right: 20px;margin-top: -30px;font-size: 50px;">
			<div class="col">
				<p style="text-align: center;">
					<img width="120px"
						src="https://image.wujiantao.cn/group1/M00/00/00/wKgBe1rM3HiARnNEAAFPW0h1Eu4406.png" />
				</p>
			</div>
		</div>
		<div class="list no-hairlines-md" style="margin-top: -35px;">
			<div class="item-content item-input item-link">
				<div class="item-inner">
					<div class="item-row">
						<div class="item-input-wrap">
							<input id="denglu_tel" type="tel" placeholder="请输入手机号码">
							<span class="input-clear-button"></span>
						</div>
						<div class="item-after">
							<p style="margin: 3px 0; color: #000;">+86</p>
						</div>
					</div>
				</div>
			</div>
			<div class="item-content item-input">
				<div class="item-inner">
					<div class="item-row">
						<div class="item-input-wrap">
							<input id="denglu_yzm" type="number" placeholder="请输入验证码">
							<span class="input-clear-button"></span>
						</div>
						<div class="item-after">
							<button id="denglu_getcode"
								class="button col button-round denglutm" disabled="disabled">获取验证码</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="margin-left: 20px; margin-right: 20px;">
			<button id="denglu_dl" href="#"
				style="border-radius: 30px; height: 39px; font-size: 14px; line-height: 37px;"
				class="col button button-big button-fill denglutm"
				disabled="disabled">登&nbsp;&nbsp;&nbsp;录</button>
		</div>
		<div class="row"
			style="margin-left: 20px; margin-right: 20px;margin-top: 15px;">
			<div class="col">
				<p>
					<a href="#">账户密码登陆</a>
				</p>
			</div>
			<div class="col">
				<p class="text-align-right">
					<a href="/user/register/page/">注册</a>
				</p>
			</div>
		</div>
	</div>
</div>
