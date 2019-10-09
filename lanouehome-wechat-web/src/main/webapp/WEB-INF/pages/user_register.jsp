<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="page" data-name="user-register">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="link back" href="#"> <i class="icon icon-back"> </i> <span
					class="ios-only"> 返回 </span>
				</a>
			</div>
			<div class="title">注册</div>
			<div class="right">帮助</div>
		</div>
	</div>
	<div class="page-content" style="background-color: #f7f7f8;">
		<div class="list no-hairlines-md">
			<div class="item-content item-input item-link">
				<div class="item-inner">
					<div class="item-row">
						<div class="item-input-wrap">
							<input id="zhuce_tel" type="text" placeholder="请输入手机号码">
							<span class="input-clear-button"></span>
						</div>
						<div class="item-after">
							<p style="margin: 3px 0;color: #000;">+86</p>
						</div>
					</div>
				</div>
			</div>
			<div class="item-content item-input">
				<div class="item-inner">
					<div class="item-row">
						<div class="item-input-wrap">
							<input id="zhuce_yzm" type="text" placeholder="请输入验证码"> <span
								class="input-clear-button"></span>
						</div>
						<div class="item-after">
							<button id="zhuce_getcode" class="button col button-round">获取验证码</button>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="row" style="margin-left: 20px;margin-right: 20px;">
			<button id="zhuce_dl"
				style="border-radius: 30px;height: 39px;font-size: 14px;line-height: 37px;"
				class="col button button-big button-fill">同意协议并注册</button>
		</div>

		<div class="row"
			style="margin-left: 20px;margin-right: 20px;font-size: 12px;margin-top: 20px;color: #8e8e93">
			<div class="col">已阅读并同意以下协议</div>
		</div>
		<div class="row"
			style="margin-left: 20px;margin-right: 20px;font-size: 12px;">
			<div class="col">
				<a href="#">《回收猫服务协议》</a> <a href="#">《隐私权政策》</a> <a href="#">《微信支付协议》</a>
			</div>
		</div>
	</div>
</div>

