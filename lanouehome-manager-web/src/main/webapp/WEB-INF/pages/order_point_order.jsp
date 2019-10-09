<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<title>积分订单管理</title>

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
		<div id="toolbar">
			<a class="waves-effect waves-button" href="javascript:;"
				onclick="deleteAction('/order/point_order/delete', 'pointOrderId')"><i
				class="zmdi zmdi-close"></i> 删除积分订单</a>
		</div>
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
				url : '/order/point_order/list',
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
				idField : 'pointOrderId',
				sortName : 'createTime',
				sortOrder : 'desc',
				escape : true,
				searchOnEnterKey : true,
				maintainSelected : true,
				toolbar : '#toolbar',
				columns : [
					{
						field : 'checkboxState',
						checkbox : true,
					},
					{
						field : 'pointOrderId',
						title : '积分订单ID',
						align : 'center',
						halign : 'center'
					},
					{
						field : 'userId',
						title : '用户ID',
						align : 'center',
						halign : 'center',
					},
					{
						field : 'itemId',
						title : '商品ID',
						align : 'center',
						halign : 'center',
					},
					{
						field : 'message',
						title : '留言',
						align : 'center',
						halign : 'center',
					},
					{
						field : 'courierNumber',
						title : '快递单号',
						align : 'center',
						halign : 'center',
						editable : {
							type : 'text',
							title : '快递单号',
							validate : function isNum(address) {
								if (isNaN(address)) {
									return "无效快递单号！";
								}
							}
						}
					},
					{
						field : 'price',
						title : '价格',
						align : 'center',
						halign : 'center',
					},
					{
						field : 'state',
						title : '状态',
						align : 'center',
						halign : 'center',
						noeditFormatter : function(value, row, index) {
							if (value == 1) {
								return '未发货';
							} else if (value == 2) {
								return '已发货';
							} else {
								return '完成';
							}
						},
						editable : {
							type : 'select',
							title : '状态',
							source : [ {
								value : "1",
								text : '未发货'
							}, {
								value : "2",
								text : '已发货'
							}, {
								value : "3",
								text : '完成'
							},
							]
						},
					},
					{
						field : 'endTime',
						title : '结束时间',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
					{
						field : 'createTime',
						title : '创建时间',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
					{
						field : 'updateTime',
						title : '更新时间',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
				],
				onEditableSave : function editSave(field, row, oldValue, $element) {
					var data = {};
					data[field] = row[field];
					data['pointOrderId'] = row.pointOrderId;
					$.ajax({
						type : "post",
						url : "/order/point_order/edit",
						data : JSON.stringify(data),
						dataType : "json",
						contentType : 'application/json',
						success : function(data, status) {
							if (data.stateCode != 200) {
								$('#table').bootstrapTable('refresh', {
									silent : true,
								});
								$.confirm({
									title : false,
									type : 'red',
									content : data.message,
									backgroundDismiss : true,
									buttons : {
										cancel : {
											text : '确认',
											btnClass : 'waves-effect waves-button'
										}
									}
								});
							}
						},
						error : function() {
							$('#table').bootstrapTable('refresh', {
								silent : true,
							});
							$.confirm({
								title : false,
								type : 'red',
								content : '网络繁忙！',
								backgroundDismiss : true,
								buttons : {
									cancel : {
										text : '确认',
										btnClass : 'waves-effect waves-button'
									}
								}
							});
						},
					});
				}
			}).on('all.bs.table', function(e, name, args) {
				$('[data-toggle="tooltip"]').tooltip();
				$('[data-toggle="popover"]').popover();
			});
			$(".search").children("input").attr("placeholder", "电话号码搜索");
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
				if (key == "pointOrderId") {
					html.push('<p><b>积分订单ID:</b> ' + value + '</p>');
					$.ajax({
						type : "post",
						url : "/ucenter/user_address/find/" + row.userAddressId,
						data : null,
						async : false,
						dataType : "json",
						contentType : 'application/json',
						success : function(data, status) {
							if (data.stateCode == 200) {
								html.push('<p><b>联系人姓名:</b> ' + data.data.name + '</p>');
								html.push('<p><b>性别:</b> ' + data.data.sex + '</p>');
								html.push('<p><b>联系方式:</b> ' + data.data.telephone + '</p>');
								html.push('<p><b>区域ID:</b> ' + data.data.districtId + '</p>');
								html.push('<p><b>详细地址:</b> ' + data.data.address + '</p>');
							}
						},
					});
				} else if (key == "userId") {
					visible = true;
					key = "用户ID"
				} else if (key == "itemId") {
					visible = true;
					key = "商品ID"
				} else if (key == "districtId") {
					visible = true;
					key = "区域ID"
				} else if (key == "address") {
					visible = true;
					key = "收货地址"
				} else if (key == "message") {
					visible = true;
					key = "卖家留言";
				} else if (key == "courierNumber") {
					visible = true;
					key = "快递单号"
				} else if (key == "price") {
					visible = true;
					key = "价格"
				} else if (key == "state") {
					visible = true;
					key = "状态"
					if (value == 1) {
						value = '未发货';
					} else if (value == 2) {
						value = '已发货';
					} else {
						value = '完成';
					}
				} else if (key == "endTime") {
					visible = true;
					key = "结束日期"
					value = changeDateFormat(value);
				} else if (key == "createTime") {
					visible = true;
					key = "注册日期"
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