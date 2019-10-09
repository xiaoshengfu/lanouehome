<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page" data-name="balance-withdrawals">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="link back" href="#"> <i class="icon icon-back"> </i> <span
					class="ios-only">取消 </span>
				</a>
			</div>
			<div class="title">余额提现</div>
			<div class="right">
				<span class="ios-only">帮助</span>
			</div>
		</div>
	</div>
	<div class="page-content" style="background-color: #f7f7f8;">
		<div class="card">
			<div class="card-header">
				<div class="list media-list">
					<ul>
						<li>
							<div class="item-content">
								<div class="item-media" style="font-size: 14px">到微信零钱</div>
								<div class="item-inner"
									style="padding-top: 9px; margin-left: 25px;">
									<div class="item-subtitle"
										style="font-size: 14px; color: #007aff">余额</div>

									<div class="item-text" style="font-size: 14px; margin-top: 8px">秒到账</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="card-header">
				<div
					class="list media-list no-hairlines no-hairlines-md no-hairlines-between">
					<ul>
						<li>
							<div class="item-content ">
								<div class="item-media" style="font-size: 14px">提现金额</div>
								<div class="item-inner"
									style="padding-top: 9px; margin-left: 25px;"></div>
							</div>
						</li>
						<li class="item-content item-input">
							<div class="item-media">
								<i class="f7-icons" style="font-size: 30px; font-weight: 700">money_yen</i>
							</div>
							<div class="item-inner">
								<div class="item-input-wrap">
									<input id="tixian_input" type="number" style="font-size: 40px">
									<span class="input-clear-button"></span>
								</div>
							</div>
						</li>
						<li class="item-content">
							<div>
								<div id="im1" class="item-media"
									style="font-size: 12px; color: #8e8e93">
									账户余额 <span>￥</span> <span id="tixian_yue"> <fmt:formatNumber
											type="number" value="${userBalance.balance/100}"
											pattern="#0.00" /></span>,&nbsp;&nbsp;&nbsp; <span id="tixian_all"
										style="color: #007aff;">全部提现</span>
								</div>
								<div id="im2" class="item-media"
									style="font-size: 12px; color:#ff3b30;display: none;">
									输入金额超过账户余额</div>
						</li>
						<li class="item-content">
							<div class="item-inner" style="padding-top: 0px">
								<p class="row" style="margin: 0 0">
									<button id="tixain_submit"
										class="col button button-big button-fill"
										style="opacity: 0.5;" disabled="true">提现</button>
								</p>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>