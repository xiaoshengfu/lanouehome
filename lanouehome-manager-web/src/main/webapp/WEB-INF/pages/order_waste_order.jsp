<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<title>废品订单管理</title>

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
				onclick="deleteAction('/order/waste_order/delete', 'userId')"><i
				class="zmdi zmdi-close"></i> 删除废品订单</a>
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
				url : '/order/waste_order/list',
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
				idField : 'wasteOrderId',
				sortName : 'confirmTime',
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
						field : 'wasteOrderId',
						title : '废品订单ID',
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
						field : 'recyclerId',
						title : '回收员ID',
						visible : false,
						align : 'center',
						halign : 'center',
					},
					{
						field : 'reserveTime',
						title : '预约时间',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
					{
						field : 'message',
						title : '留言',
						visible : false,
						align : 'center',
						halign : 'center',
					},
					{
						field : 'state',
						title : '状态',
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							if (value == 1) {
								return '<span class="label label-info">待确认</span>';
							} else if (value == 2) {
								return '<span class="label label-primary">待回收</span>';
							} else if (value == 3) {
								return '<span class="label label-warning">已失效</span>';
							} else if (value == 4) {
								return '<span class="label label-success">已完成</span>';
							} else {
								return '<span class="label label-danger">未知</span>';
							}
						}
					},
					{
						field : 'estimatePrice',
						title : '预估价格',
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return value / 100;
						},
					},
					{
						field : 'realPrice',
						title : '实际价格',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return value / 100;
						},
					},
					{
						field : 'estimatePoint',
						title : '预估积分',
						align : 'center',
						halign : 'center',
					},
					{
						field : 'realPoint',
						title : '实际积分',
						visible : false,
						align : 'center',
						halign : 'center',
					},
					{
						field : 'confirmTime',
						title : '确认时间',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
					{
						field : 'payTime',
						title : '支付时间',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
					{
						field : 'endTime',
						title : '完成时间',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
					{
						field : 'invalidTime',
						title : '失效时间',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return changeDateFormat(value);
						},
					},
					{
						field : 'invalidReason',
						title : '失效原因',
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
				onEditableSave : function editSave(field, row, oldValue, $element) {
					var data = {};
					data[field] = row[field];
					data['wasteOrderId'] = row.wasteOrderId;
					$.ajax({
						type : "post",
						url : "/order/waste_order/edit",
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
				if (key == "wasteOrderId") {
					html.push('<p><b>废品订单ID:</b> ' + value + '</p>');
					$.ajax({
						type : "post",
						url : "/ucenter/user_address/find/" + row.userAddressId,
						data : null,
						async : false,
						dataType : "json",
						contentType : 'application/json',
						success : function(data, status) {
							if (data.stateCode == 200) {
								html.push('<p><b>卖家姓名:</b> ' + data.data.name + '</p>');
								html.push('<p><b>性别:</b> ' + data.data.sex + '</p>');
								html.push('<p><b>联系方式:</b> ' + data.data.telephone + '</p>');
								html.push('<p><b>详细地址:</b> ' + data.data.address + '</p>');
							}
						},
					});
					$.ajax({
						type : "post",
						url : "/order/order_item/find/" + value,
						data : null,
						async : false,
						dataType : "json",
						contentType : 'application/json',
						success : function(data, status) {
							if (data.stateCode == 200) {
								html.push('<b>订单内容详情:</b><br/>');
								html.push('<div class="table-responsive" style="width:70%"><table class="table table-condensed"><tr><th>图片</th><th>名称</th><th>描述</th><th>预估货量</th><th>实际货量</th><th>预估单价(元)</th><th>实际单价(元)</th></tr>');
								for (var i = 0; i < data.data.length; i++) {
									var unit = data.data[i].unit;
									if (unit == 1) {
										unit = '斤';
									} else if (unit == 2) {
										unit = '公斤';
									} else if (unit == 3) {
										unit = '吨';
									} else if (unit == 4) {
										unit = '台';
									} else if (unit == 5) {
										unit = '部';
									} else if (unit == 6) {
										unit = '个';
									} else {
										unit = '其他';
									}
									html.push('<tr><td><img width="50px" alt="'+data.data[i].waste.name+'" src="'+data.data[i].waste.pictureUrl+'"/></td><td>'+data.data[i].waste.name+'</td><td>'+data.data[i].description+'</td><td>'+data.data[i].estimateNum+unit+'</td><td>'+data.data[i].realNum+unit+'</td><td>'+data.data[i].estimateUnitPrice/100+'</td><td>'+data.data[i].realUnitPrice/100+'</td></tr>');
								}
								html.push('</table></div>');
							}
						},
					});
				} else if (key == "userId") {
					html.push('<p><b>用户姓名:</b> ' + value + '</p>');
				} else if (key == "recyclerId") {
					$.ajax({
						type : "post",
						url : "/ucenter/recycler/find/" + value,
						data : null,
						async : false,
						dataType : "json",
						contentType : 'application/json',
						success : function(data, status) {
							if (data.stateCode == 200) {
								html.push('<p><b>回收员:</b> ' + data.data.name + " " + data.data.telephone + '</p>');
							}
						},
					});
				} else if (key == "reserveTime") {
					if (value != null) {
						visible = true;
						key = "预约时间";
						value = changeDateFormat(value);
					}
				} else if (key == "districtId") {
					visible = true;
					key = "区域ID";
				} else if (key == "address") {
					visible = true;
					key = "地址";
				} else if (key == "message") {
					visible = true;
					key = "留言";
				} else if (key == "estimatePrice") {
					visible = true;
					key = "预估价格(元)";
					value = value / 100;
				} else if (key == "realPrice") {
					if (value != null) {
						visible = true;
						key = "实际价格(元)";
						value = value / 100;
					}
				} else if (key == "estimatePoint") {
					visible = true;
					key = "预估积分";
				} else if (key == "realPoint") {
					if (value != null) {
						visible = true;
						key = "实际积分";
					}
				} else if (key == "state") {
					visible = true;
					key = "状态";
					if (value == 1) {
						value = '待确认';
					} else if (value == 2) {
						value = '待回收';
					} else if (value == 3) {
						value = '已失效';
					} else if (value == 4) {
						value = '已完成';
					} else {
						value = '未知';
					}
				} else if (key == "confirmTime") {
					if (value != null) {
						visible = true;
						key = "确认时间"
						value = changeDateFormat(value);
					}
				} else if (key == "payTime") {
					if (value != null) {
						visible = true;
						key = "支付时间";
						value = changeDateFormat(value);
					}
				} else if (key == "endTime") {
					if (value != null) {
						visible = true;
						key = "完成时间"
						value = changeDateFormat(value);
					}
				} else if (key == "createTime") {
					visible = true;
					key = "订单创建日期"
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