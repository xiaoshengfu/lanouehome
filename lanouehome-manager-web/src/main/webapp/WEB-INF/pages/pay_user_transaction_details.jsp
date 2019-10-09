<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<title>用户积分交易明细管理</title>

<link href="/resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="/resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css"
	rel="stylesheet" />
<link
	href="/resources/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css"
	rel="stylesheet" />
<link href="/resources/plugins/waves-0.7.5/waves.min.css"
	rel="stylesheet" />
<link href="/resources/plugins/jquery-confirm/jquery-confirm.min.css"
	rel="stylesheet" />
<link
	href="/resources/plugins/x-editable-develop/dist/bootstrap3-editable/css/bootstrap-editable.css"
	rel="stylesheet" />

<link href="/resources/css/common.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<div id="toolbar"></div>
		<table id="table"></table>
	</div>
	<script src="/resources/plugins/jquery.1.12.4.min.js"></script>
	<script src="/resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
	<script
		src="/resources/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
	<script
		src="/resources/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="/resources/plugins/waves-0.7.5/waves.min.js"></script>
	<script src="/resources/plugins/jquery-confirm/jquery-confirm.min.js"></script>
	<script
		src="/resources/plugins/x-editable-develop/dist/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
	<script
		src="/resources/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-editable.min.js"></script>

	<script src="/resources/js/common.js"></script>
	<script>
		var $table = $('#table');
		$(function() {
			$(document).on('focus', 'input[type="text"]', function() {
				$(this).parent().find('label').addClass('active');
			}).on('blur', 'input[type="text"]', function() {
				if ($(this).val() == '') {
					$(this).parent().find('label').removeClass('active');
				}
			});
			$table.bootstrapTable({
				url : '/pay/user_points_details/list',
				height : getHeight(),
				striped : true,
				search : true,
				searchOnEnterKey : true,
				showRefresh : true,
				showToggle : true,
				showColumns : true,
				minimumCountColumns : 2,
				showPaginationSwitch : true,
				clickToSelect : true,
				detailView : true,
				detailFormatter : 'detailFormatter',
				pagination : true,
				paginationLoop : false,
				sidePagination : 'server',
				silentSort : false,
				smartDisplay : false,
				idField : 'transactionId',
				sortName : 'createTime',
				sortOrder : 'desc',
				escape : true,
				searchOnEnterKey : true,
				maintainSelected : true,
				toolbar : '#toolbar',
				columns : [
					{
						field : 'pointsDetailId',
						title : '交易ID',
						align : 'center',
						halign : 'center'
					},
					{
						field : 'userId',
						title : '用户ID',
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return value / 100;
						},
					},
					{
						field : 'source',
						title : '交易原因',
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							if (value == 1) {
								return "平台支付用户订单";
							} else if (value == 2) {
								return "用户余额提现";
							} else {
								return "未知";
							}
						},
					},
					{
						field : 'type',
						title : '交易类型',
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							if (value == 1) {
								return value = "支出";
							} else if (value == 2) {
								return value = "收入";
							} else {
								return value = "未知";
							}
						},
					},
					{
						field : 'num',
						title : '交易积分数量',
						align : 'center',
						halign : 'center',
					},
					{
						field : 'createTime',
						title : '创建时间',
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
					{
						field : 'updateTime',
						title : '更新时间',
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
				],
			}).on('all.bs.table', function(e, name, args) {
				$('[data-toggle="tooltip"]').tooltip();
				$('[data-toggle="popover"]').popover();
			});
			$(".search").children("input").attr("placeholder", "用户ID搜索");
		});
		//转换日期格式(时间戳转换为datetime格式)
		function changeDateFormat(cellval) {
			var dateVal = cellval + "";
			if (cellval != null) {
				var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
				var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
				var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	
				var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
				var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
				var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	
				return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
			}
		}
		function detailFormatter(index, row) {
			var html = [];
			$.each(row, function(key, value) {
				var visible = false;
				if (key == "transactionId") {
					visible = true;
					key = "交易ID"
				} else if (key == "userId") {
					visible = true;
					key = "用户ID"
				} else if (key == "source") {
					visible = true;
					key = "交易原因";
					if (value == 1) {
						value = "平台支付用户订单";
					} else if (value == 2) {
						value = "用户余额提现";
					} else {
						value = "未知";
					}
				} else if (key == "type") {
					visible = true;
					key = "交易金额";
					if (value == 1) {
						value = "支出";
					} else if (value == 2) {
						value = "收入";
					} else {
						value = "未知";
					}
				} else if (key == "num") {
					visible = true;
					key = "交易金额(元)";
					value = value / 100;
				} else if (key == "createTime") {
					visible = true;
					key = "创建日期"
					value = changeDateFormat(value);
				} else if (key == "updateTime") {
					visible = true;
					key = "信息更新日期";
					value = changeDateFormat(value);
				}
				if (visible) {
					html.push('<p><b>' + key + ':</b> ' + value + '</p>');
				}
			});
			return html.join('');
		}
	</script>
</body>
</html>