<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<title>用户管理</title>

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
				onclick="deleteAction('/ucenter/user/delete', 'userId')"><i
				class="zmdi zmdi-close"></i> 删除用户</a>
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
				url : '/ucenter/user/list',
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
				idField : 'userId',
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
						field : 'userId',
						title : '用户ID',
						align : 'center',
						halign : 'center'
					},
					{
						field : 'telephone',
						title : '电话',
						align : 'center',
						halign : 'center',
						editable : {
							type : 'text',
							title : '电话',
							validate : function isPoneAvailable(phoneNum) {
								var reg = /^1[3|4|5|7|8][0-9]{9}$/;
								if (!reg.test(phoneNum)) {
									return "无效手机号码！";
								}
							}
						}
					},
					{
						field : 'openid',
						title : 'openid',
						align : 'center',
						halign : 'center'
					},
					{
						field : 'name',
						title : '姓名',
						align : 'center',
						halign : 'center',
						editable : {
							type : 'text',
							title : '姓名',
							validate : function isName(name) {
								if (!isNaN(name) || name == undefined) {
									return "无效姓名！";
								}
							}
						}
					},
					{
						field : 'sex',
						title : '性别',
						align : 'center',
						halign : 'center',
						editable : {
							type : 'select',
							title : '性別',
							source : [ {
								value : "1",
								text : "男"
							}, {
								value : "2",
								text : "女"
							},
							]
						},
						noeditFormatter : function(value, row, index) {
							if (value == 1) {
								return '男';
							} else if (value == 2) {
								return '女';
							} else {
								return '未知';
							}
						},
					},
					{
						field : 'state',
						title : '状态',
						align : 'center',
						halign : 'center',
						noeditFormatter : function(value, row, index) {
							if (value == 1) {
								return '正常';
							} else {
								return '禁止登录';
							}
						},
						editable : {
							type : 'select',
							title : '状态',
							source : [ {
								value : "1",
								text : '正常'
							}, {
								value : "2",
								text : '禁止登录'
							},
							]
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
					data['userId'] = row.userId;
					$.ajax({
						type : "post",
						url : "/ucenter/user/edit",
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
				if (key == "userId") {
					visible = true;
					key = "用户ID"
				} else if (key == "telephone") {
					visible = true;
					key = "电话号码"
				} else if (key == "name") {
					visible = true;
					key = "姓名"
				} else if (key == "sex") {
					visible = true;
					key = "性别"
					if (value == 1) {
						value = "男";
					} else if (value == 2) {
						value = "女";
					} else {
						value = "未知";
					}
				} else if (key == "state") {
					visible = true;
					key = "状态"
					if (value == 1) {
						value = '正常';
					} else {
						value = '禁止登录';
					}
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