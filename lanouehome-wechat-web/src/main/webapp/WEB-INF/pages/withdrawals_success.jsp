<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="nowDate" class="java.util.Date" scope="page" />
<div class="page" data-name="withdrawals-success">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="left">
			</div>
			<div class="title">提现成功</div>
			<div class="right"></div>
		</div>
	</div>
	<div class="page-content" style="background-color: #f7f7f8;">
		<div class="timeline">
			<div class="timeline-item">
				<div class="timeline-item-date">
					<fmt:formatDate pattern="MM" value="${nowDate}" />
					<small><fmt:formatDate pattern="MMMMM" value="${nowDate}" /></small>
				</div>
				<div class="timeline-item-divider"></div>
				<div class="timeline-item-content" style="color: #8e8e93">发起提现申请</div>
			</div>
			<div class="timeline-item">
				<div class="timeline-item-date">
					<div class="timeline-item-date">
						<fmt:formatDate pattern="MM" value="${nowDate}" />
						<small><fmt:formatDate pattern="MMMM" value="${nowDate}" /></small>
					</div>
				</div>
				<div class="timeline-item-divider"></div>
				<div class="timeline-item-content">
					<div class="timeline-item-subtitle" style="color: #007aff">微信处理中</div>
					<div class="timeline-item-text"
						style="color: #bbb; font-size: 13px">
						预计
						<fmt:formatDate pattern="MM-dd HH:mm" value="${nowDate}" />
						前到账
					</div>
				</div>
			</div>
			<div class="timeline-item">
				<div class="timeline-item-date">
					<fmt:formatDate pattern="MM" value="${nowDate}" />
					<small><fmt:formatDate pattern="MMMM" value="${nowDate}" /></small>
				</div>
				<div class="timeline-item-divider"></div>
				<div class="timeline-item-content" style="color: #8e8e93">到账成功</div>
			</div>
		</div>

		<div class="list media-list no-hairlines no-hairlines-between">
			<ul>
				<li>
					<div class="item-content" style="background-color: #f7f7f8;">
						<div class="item-inner">
							<div class="item-title-row">
								<div class="item-title"
									style="color: #8e8e93;font-size: 13px;font-weight: 500">提现金额</div>
								<div class="item-after" style="color: #000;font-size: 13px">
									￥
									<fmt:formatNumber type="number" value="${money/100}"
										pattern="#0.00" />
								</div>
							</div>
							<div class="item-title-row" style="margin-top: 5px">
								<div class="item-title"
									style="color: #8e8e93;font-size: 13px;font-weight: 500">手续费</div>
								<div class="item-after" style="color: #000;font-size: 13px">￥0.00</div>
							</div>
							<div class="item-title-row" style="margin-top: 5px">
								<div class="item-title"
									style="color: #8e8e93;font-size: 13px;font-weight: 500">到账银行</div>
								<div class="item-after" style="color: #000;font-size: 13px">邮政储蓄
									尾号xxxx</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<div class="block">
			<p class="row" style="margin: 50px 80px">
				<button id="back_balance_button" class="button col">完成</button>
			</p>
		</div>
	</div>
</div>
