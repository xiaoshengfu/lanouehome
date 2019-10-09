<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page" data-name="user-balance">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
				<a class="link back back_index" href="#"> <i class="icon icon-back"> </i> <span
					class="ios-only"> 返回 </span>
				</a>
			</div>
			<div class="title">余额</div>
			<div class="right">
				<a href="/user/balance/details/list/">余额明细</a>
			</div>
		</div>
	</div>
	<div class="page-content" style="background-color: #f7f7f8;">
		<div class="row"
			style="margin-left: 20px; margin-right: 20px;margin-top: 15px; font-size: 50px;">
			<div class="col">
				<p class="text-align-center" style="margin-bottom: 0px;">
					<img alt="" src="/resources/images/balance.png"
						style="height: 103px;width: 103px;" />
				</p>
			</div>
		</div>
		<div class="row"
			style="margin-left: 20px; margin-right: 20px;font-size: 50px;">
			<div class="col">
				<p class="text-align-center"
					style="margin-top: 0px;margin-top: 0px;font-size: 18px;">
					我的余额</p>
				<p class="text-align-center"
					style="margin-top: 5px;margin-bottom: 0px;font-size: 45px;font-family: '微软雅黑';">
					￥
					<fmt:formatNumber type="number" value="${userBalance.balance/100}"
						pattern="#0.00" />
				</p>
				<p class="text-align-center"
					style="margin-top: 10px;margin-bottom: 0px;font-size: 45px;font-family: '微软雅黑';">
					<button class="col button button-big button-fill">充值</button>
				</p>
				<p class="text-align-center"
					style="margin-top: 10px;margin-bottom: 0px;font-size: 45px;font-family: '微软雅黑';">
					<a href="/user/balance/withdrawals/page/" class="col button button-big">提现</a>
				</p>
				<p class="text-align-center"
					style="margin-top: 25px;margin-bottom: 0px;font-size: 15px;font-family: '微软雅黑';">
					<a href="/user/balance/details/list/">余额明细></a>
				</p>
			</div>
		</div>
	</div>
</div>