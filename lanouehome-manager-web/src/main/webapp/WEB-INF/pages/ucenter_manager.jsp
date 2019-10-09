<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<title>管理员管理</title>

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
				onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增管理员</a> <a
				class="waves-effect waves-button" href="javascript:;"
				onclick="deleteAction('/ucenter/manager/delete', 'managerId')"><i
				class="zmdi zmdi-close"></i> 删除管理员</a>
		</div>
		<table id="table"></table>
	</div>
	<!-- 新增 -->
	<div id="createDialog" class="crudDialog" hidden>
		<div class="form-group">
			<label for="input0">工号</label> <input id="input1" type="text"
				name="jobNumber" class="form-control">
		</div>
		<div class="form-group">
			<label for="input1">姓名</label> <input id="input2" type="text"
				name="name" class="form-control">
		</div>
		<div class="form-group">
			<label for="input2">电话</label> <input id="input3" type="text"
				name="telephone" class="form-control">
		</div>
		<div class="form-group">
			<label for="input5">密码</label> <input id="input4" type="text"
				name="password" class="form-control">
		</div>
		<div class="form-group">
			<a href="javascript:;" id="a_picture" class="add-upload"><span
				id="span_picture">上传照片</span><input id="input5" class="add-change"
				type="file" name="pictureFile" class="form-control"></a>
		</div>
		<div class="radio radio-primary radio-inline">
			<input type="radio" id="inlineRadio1" value="1" name="sex" checked>
			<label for="inlineRadio1"> 男 </label>
		</div>
		<div class="radio radio-danger radio-inline">
			<input type="radio" id="inlineRadio2" value="2" name="sex"> <label
				for="inlineRadio2"> 女 </label>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="radio radio-success radio-inline">
			<input type="radio" id="inlineRadio3" value="1" name="state" checked>
			<label for="inlineRadio3"> 正常 </label>
		</div>
		<div class="radio radio-warning radio-inline">
			<input type="radio" id="inlineRadio4" value="2" name="state">
			<label for="inlineRadio4"> 锁定 </label>
		</div>
		<br/><br/>
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
				url : '/ucenter/manager/list',
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
				idField : 'managerId',
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
						field : 'managerId',
						title : '管理员ID',
						align : 'center',
						halign : 'center'
					},
					{
						field : 'jobNumber',
						title : '工号',
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
					data['managerId'] = row.managerId;
					$.ajax({
						type : "post",
						url : "/ucenter/manager/edit",
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
			$(".search").children("input").attr("placeholder", "工号搜索");
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
					url : '/ucenter/manager/upload_picture/' + row.managerId,
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
				if (key == "recyclerId") {
					visible = true;
					key = "管理员ID"
				} else if (key == "telephone") {
					visible = true;
					key = "电话号码"
				} else if (key == "name") {
					visible = true;
					key = "姓名"
				} else if (key == "jobNumber") {
					visible = true;
					key = "工号"
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
				} else if (key == "sex") {
					visible = true;
					key = "性別";
					if (value = 1) {
						value = "男";
					} else if (value = 2) {
						value = "女";
					} else {
						value = "未知";
					}
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
				title : '新增管理员',
				content : '<form action="/ucenter/mamager/add" enctype="multipart/form-data" id="addForm">' + $('#createDialog').html() + '</form>',
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
								url : '/ucenter/manager/add',
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