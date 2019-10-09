<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<title>类目管理</title>

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
<link href="/resources/plugins/checkboxes/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="/resources/plugins/checkboxes/css/build.css"
	rel="stylesheet" />

<link href="/resources/css/common.css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<div id="toolbar">
			<a class="waves-effect waves-button" href="javascript:;"
				onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增类目</a> <a
				class="waves-effect waves-button" href="javascript:;"
				onclick="deleteAction('/cms/category/delete', 'districtId')"><i
				class="zmdi zmdi-close"></i> 删除类目</a>
		</div>
		<table id="table"></table>
	</div>
	<!-- 新增 -->
	<div id="createDialog" class="crudDialog" hidden>
		<div class="form-group">
			<label for="input0">父类目ID</label> <input id="input0" type="text"
				name="parentId" class="form-control">
		</div>
		<div class="form-group">
			<label for="input1">名称</label> <input id="input1" type="text"
				name="name" class="form-control">
		</div>
		<div class="form-group">
			<label for="input2">排序码</label> <input id="input2" type="text"
				name="sortOrder" class="form-control">
		</div>
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
				url : '/cms/category/list',
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
				idField : 'categoryId',
				sortName : 'sortOrder',
				sortOrder : 'asc',
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
						field : 'categoryId',
						title : '类目ID',
						align : 'center',
						halign : 'center'
					},
					{
						field : 'parentId',
						title : '父类目ID',
						align : 'center',
						halign : 'center',
					},
					{
						field : 'name',
						title : '名称',
						align : 'center',
						halign : 'center',
						editable : {
							type : 'text',
							title : '名称',
							validate : function isName(name) {
								if (!isNaN(name) || name == undefined) {
									return "无效名称！";
								}
							}
						}
					},
					{
						field : 'state',
						title : '状态',
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							return '<span class="label label-success">正常</span>';
						}
					},
					{
						field : 'sortOrder',
						title : '排序码',
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
				onEditableSave : function editSave(field, row, oldValue, $element) {
					var data = {};
					data[field] = row[field];
					data['categoryId'] = row.categoryId;
					$.ajax({
						type : "post",
						url : "/cms/category/edit",
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
			$(".search").children("input").attr("placeholder", "区域名称模糊搜索");
		});
		function detailFormatter(index, row) {
			var html = [];
			$.each(row, function(key, value) {
				var visible = false;
				if (key == "categoryId") {
					visible = true;
					key = "类目ID"
				} else if (key == "parentId") {
					visible = true;
					key = "父类目ID"
				} else if (key == "name") {
					visible = true;
					key = "名称"
				} else if (key == "state") {
					visible = true;
					key = "状态";
					value = "正常";
				} else if (key == "isParent") {
					visible = true;
					key = "是否有子类目"
					if (value) {
						value = "是";
					} else {
						value = "否";
					}
				} else if (key == "sortOrder") {
					visible = true;
					key = "排序码"
				} else if (key == "createTime") {
					visible = true;
					key = "添加日期"
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
		// 新增
		function createAction() {
			$.confirm({
				type : 'dark',
				animationSpeed : 300,
				title : '新增类目',
				content : '<form action="/cms/category/add" enctype="multipart/form-data" id="addForm">' + $('#createDialog').html() + '</form>',
				buttons : {
					confirm : {
						text : '确认',
						btnClass : 'waves-effect waves-button',
						action : function() {
							var inputs = $("#addForm").find("input");
							for (var i = 0; i < inputs.length; i++) {
								if (inputs[i].value == "") {
									$.confirm({
										title : false,
										type : 'red',
										content : "请填写完整的内容！",
										backgroundDismiss : true,
										buttons : {
											cancel : {
												text : '确认',
												btnClass : 'waves-effect waves-button'
											}
										}
									});
									return;
								}
							}
							$.ajax({
								url : '/cms/category/add',
								type : 'POST',
								cache : false,
								data : JSON.stringify($("#addForm").serializeObject()),
								dataType : 'json',
								contentType : 'application/json'
							}).done(function(data) {
								if (data.stateCode != 200) {
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
								} else {
									$('#table').bootstrapTable('insertRow', {
										index : 0,
										row : data.data,
									})
								}
							}).fail(function(res) {
								$.confirm({
									title : false,
									type : 'red',
									content : "网络繁忙！",
									backgroundDismiss : true,
									buttons : {
										cancel : {
											text : '确认',
											btnClass : 'waves-effect waves-button'
										}
									}
								});
							});
						}
					},
					cancel : {
						text : '取消',
						btnClass : 'waves-effect waves-button'
					}
				},
			});
		}
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
		$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		}
	</script>
</body>
</html>