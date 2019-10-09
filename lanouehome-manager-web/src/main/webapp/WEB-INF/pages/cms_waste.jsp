<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<title>废品管理</title>

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
<style type="text/css">
.upload {
	padding: 2px 6px;
	height: 20px;
	line-height: 20px;
	position: relative;
	border: 1px solid #999;
	text-decoration: none;
	color: #666;
}

.change {
	width: 60px;
	position: absolute;
	overflow: hidden;
	right: 0;
	top: 0;
	opacity: 0;
}

.add-upload {
	padding: 6px 105px;
	height: 20px;
	line-height: 20px;
	position: relative;
	border: 1px solid #999;
	text-decoration: none;
	color: #666;
}

.add-change {
	position: absolute;
	overflow: hidden;
	right: 0;
	top: 0;
	opacity: 0;
}
</style>
</head>
<body>
	<div id="main">
		<div id="toolbar">
			<a class="waves-effect waves-button" href="javascript:;"
				onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增废品</a> <a
				class="waves-effect waves-button" href="javascript:;"
				onclick="deleteAction('/cms/waste/delete', 'wasteId')"><i
				class="zmdi zmdi-close"></i> 删除废品</a>
		</div>
		<table id="table"></table>
	</div>
	<!-- 新增 -->
	<div id="createDialog" class="crudDialog" hidden>
		<div class="form-group">
			<label for="input991">父废品ID</label> <input id="input991" type="text"
				name="parentId" class="form-control">
		</div>
		<div class="form-group">
			<label for="input1">名称</label> <input id="input1" type="text"
				name="name" class="form-control">
		</div>
		<div class="form-group">
			<label for="input99">单位量所获积分</label> <input id="input99" type="text"
				name="unitPoint" class="form-control">
		</div>
		<div class="form-group">
			<label for="input2">描述</label> <input id="input2" type="text"
				name="description" class="form-control">
		</div>
		<div class="form-group">
			<label for="input3">排序码</label> <input id="input3" type="text"
				name="sort" class="form-control">
		</div>
		<div class="form-group">
			<a href="javascript:;" id="a_picture" class="add-upload"><span
				id="span_picture">上传照片</span><input id="input5" class="add-change"
				type="file" name="pictureFile" class="form-control"></a>
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
				url : '/cms/waste/list',
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
				idField : 'wasteId',
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
						field : 'wasteId',
						title : '废品ID',
						align : 'center',
						halign : 'center'
					},
					{
						field : 'parentId',
						title : '父废品ID',
						align : 'center',
						halign : 'center',
						editable : {
							type : 'text',
							title : '父废品ID',
							validate : function isNum(name) {
								if (isNaN(name)) {
									return "无效废品ID！";
								}
							}
						}
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
						field : 'level',
						title : '等级',
						visible : false,
						align : 'center',
						halign : 'center'
					},
					{
						field : 'isParent',
						title : '是否有子废品',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							if (value) {
								return '是';
							} else {
								return '否';
							}
						},
					},
					{
						field : 'pictureUrl',
						title : '照片',
						visible : false,
						align : 'center',
						halign : 'center',
						formatter : function(value, row, index) {
							var url = "${imageServiceUrl}" + row.pictureUrl;
							return '<a href="' + url + '" target="view_window"><img width="200px" src="' + url + '"/></a>';
						},
					},
					{
						field : 'unitPoint',
						title : '单位量所获积分',
						align : 'center',
						halign : 'center',
						editable : {
							type : 'text',
							title : '单位量所获积分',
							validate : function isNum(name) {
								if (isNaN(name)) {
									return "单位量所获积分！";
								}
							}
						},
						noeditFormatter : function(value, row, index) {
							return value / 100;
						},
					},
					{
						field : 'description',
						title : '描述',
						sortable : false,
						align : 'center',
						halign : 'center',
						editable : {
							type : 'text',
							title : '姓名',
							validate : function isName(name) {
								if (!isNaN(name) || name == undefined) {
									return "无效描述！";
								}
							}
						},
					},
					{
						field : 'sort',
						title : '排序码',
						align : 'center',
						halign : 'center',
						editable : {
							type : 'text',
							title : '排序码',
							validate : function isNum(num) {
								if (num == undefined || num.trim() == '' || isNaN(num)) {
									return "无效排序码！";
								}
							}
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
					{
						field : 'action',
						title : '上传照片',
						halign : 'center',
						align : 'center',
						formatter : 'actionFormatter',
						events : 'actionEvents',
						clickToSelect : false
					}
				],
				onEditableSave : function editSave(field, row, oldValue, $element) {
					var data = {};
					data[field] = row[field];
					data['wasteId'] = row.wasteId;
					$.ajax({
						type : "post",
						url : "/cms/waste/edit",
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
			$(".search").children("input").attr("placeholder", "废品名称模糊搜索");
		});
		function actionFormatter(value, row, index) {
			return [
				'<a href="javascript:;" class="upload">选择文件<input type="file" class="change like" id="picEdit' + index + '" name="uploadImage" /></a>',
			].join('');
		}
	
		window.actionEvents = {
			'change .like' : function(e, value, row, index) {
				var file = document.getElementById("picEdit" + index).files[0];
				var patn = /\.jpg$|\.jpeg$|\.png$/i;
				if (!patn.test(file.name)) {
					$.confirm({
						title : false,
						type : 'red',
						content : "仅支持上传.jpg.jpeg.png格式图片文件！",
						backgroundDismiss : true,
						buttons : {
							cancel : {
								text : '确认',
								btnClass : 'waves-effect waves-button'
							}
						}
					});
					e.value = '';
					return;
				}
				var formData = new FormData();
				formData.append('pictureFile', file);
				$.ajax({
					url : '/cms/waste/upload_picture/' + row.wasteId,
					type : 'POST',
					cache : false,
					data : formData,
					processData : false,
					dataType : false,
					contentType : false
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
						row.pictureUrl = data.data;
						$('#table').bootstrapTable('updateRow', {
							index : index,
							row : row,
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
				e.value = '';
			},
		};
		function detailFormatter(index, row) {
			var html = [];
			$.each(row, function(key, value) {
				var visible = false;
				if (key == "wasteId") {
					visible = true;
					key = "废品ID"
				} else if (key == "name") {
					visible = true;
					key = "名称"
				} else if (key == "level") {
					visible = true;
					key = "等级"
				} else if (key == "isParent") {
					visible = true;
					key = "是否有子废品";
					if (value) {
						value = '是';
					} else {
						value = '否';
					}
				} else if (key == "parentId") {
					visible = true;
					key = "父废品ID"
				} else if (key == "description") {
					visible = true;
					key = "描述"
				} else if (key == "sort") {
					visible = true;
					key = "排序码"
				} else if (key == "createTime") {
					visible = true;
					key = "创建日期"
					value = changeDateFormat(value);
				} else if (key == "updateTime") {
					visible = true;
					key = "信息更新日期";
					value = changeDateFormat(value);
				} else if (key == "pictureUrl") {
					var url = "${imageServiceUrl}" + row.pictureUrl;
					html.push('<b>照片:</b><br/><a href="' + url + '" target="view_window"><img width="200px" src="' + url + '"/></a>');
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
				title : '新增废品',
				content : '<form action="/cms/waste/add" enctype="multipart/form-data" id="addForm">' + $('#createDialog').html() + '</form>',
				buttons : {
					confirm : {
						text : '确认',
						btnClass : 'waves-effect waves-button',
						action : function() {
							var inputs = $("#addForm").find("input");
							var formData = new FormData();
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
								if (inputs[i].type != "radio") {
									if (inputs[i].name == "pictureFile") {
										formData.append('pictureFile', inputs[i].files[0]);
									} else {
										formData.append(inputs[i].name, inputs[i].value);
									}
								} else {
									if (inputs[i].checked) {
										formData.append(inputs[i].name, inputs[i].value);
									}
								}
							}
							$.ajax({
								url : '/cms/waste/add',
								type : 'POST',
								cache : false,
								data : formData,
								processData : false,
								dataType : false,
								contentType : false
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
	</script>
</body>
</html>