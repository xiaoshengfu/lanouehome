<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<title>积分商品管理</title>

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
				onclick="deleteAction('/cms/item_desc/delete', 'itemId')"><i
				class="zmdi zmdi-close"></i> 删除积分商品</a>
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
				url : '/cms/item_desc/list',
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
				idField : 'itemId',
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
						field : 'itemId',
						title : '商品ID',
						align : 'center',
						halign : 'center'
					},
					{
						field : 'name',
						title : '名称',
						align : 'center',
						halign : 'center'
					},
					{
						field : 'price',
						title : '价格',
						align : 'center',
						halign : 'center',
						editable : {
							type : 'text',
							title : '价格',
							validate : function isNum(num) {
								if (isNaN(num)) {
									return "请输入正确的数字！";
								}
							}
						},
					},
					{
						field : 'stock',
						title : '库存',
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
								return '正常';
							} else if (value == 2) {
								return '库存不足';
							} else {
								return '下架';
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
								text : '库存不足'
							}, {
								value : "3",
								text : '下架'
							},
							]
						},
					},
					{
						field : 'description',
						title : '商品描述',
						align : 'center',
						halign : 'center',
						visible : false,
						editable : {
							type : 'text',
							title : '商品描述',
							validate : function isString(num) {
								if (!isNaN(num)) {
									return "请输入正确的商品描述！";
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
				],
				onEditableSave : function editSave(field, row, oldValue, $element) {
					$.ajax({
						type : "post",
						url : "/cms/item_desc/edit",
						data : field + "=" + row[field] + "&itemId=" + row.itemId,
						dataType : "json",
						contentType : 'application/x-www-form-urlencoded',
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
			$(".search").children("input").attr("placeholder", "名称模糊搜索");
		});
		function actionFormatter(value, row, index) {
			return [
				'<a href="javascript:;" class="upload">选择文件<input type="file" class="change like" id="picEdit' + index + '" name="uploadImage" /></a>',
			].join('');
		}
		function detailFormatter(index, row) {
			var html = [];
			$.each(row, function(key, value) {
				var visible = false;
				if (key == "itemId") {
					visible = true;
					key = "商品ID"
				} else if (key == "name") {
					visible = true;
					key = "名称"
				} else if (key == "price") {
					visible = true;
					key = "价格"
				} else if (key == "stock") {
					visible = true;
					key = "库存"
				} else if (key == "state") {
					visible = true;
					key = "状态";
					if (value == 1) {
						value = '正常';
					} else if (value == 2) {
						value = '库存不足';
					} else {
						value = '下架';
					}
				} else if (key == "createTime") {
					visible = true;
					key = "注册日期"
					value = changeDateFormat(value);
				} else if (key == "updateTime") {
					visible = true;
					key = "信息更新日期";
					value = changeDateFormat(value);
				} else if (key == "description") {
					visible = true;
					key = "商品描述";
				} else if (key == "pictureUrl") {
					var image_html = '';
					var urls = row.pictureUrl.split(',');
					for (var i = 0; i < urls.length; i++) {
						var url = "${imageServiceUrl}" + urls[i];
						image_html += '<a href="' + url + '" target="view_window"><img width="200px" src="' + url + '"/></a>';
					}
					html.push('<b>照片:</b><br/>' + image_html + '<br/>');
				}
				if (visible) {
					html.push('<p><b>' + key + ':</b> ' + value + '</p>');
				}
			});
			return html.join('');
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