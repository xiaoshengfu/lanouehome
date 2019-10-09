<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>修改积分商品</title>
<link href="/resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="/resources/plugins/waves-0.7.5/waves.min.css"
	rel="stylesheet" />
<link href="/resources/plugins/jquery-confirm/jquery-confirm.min.css"
	rel="stylesheet" />
<link href="/resources/plugins/wangEditor-3.1.1/wangEditor.min.css"
	rel="stylesheet" />
<link
	href="/resources/plugins/bootstrap-fileinput/css/fileinput.min.css"
	rel="stylesheet" />
</head>
<body>
	<form class="form-horizontal" id="itemEditForm"
		style="margin-top: 30px;margin-left: -110px;">
		<div class="container">
			<div class="row form-group">
				<label class="control-label col-lg-1 col-md-2" for="itemId">商品ID:</label>
				<div class="col-lg-3 col-md-4">
					<input name="itemId" class="form-control" type="text">
				</div>
			</div>
			<div class="row form-group">
				<label class="control-label col-lg-1 col-md-2" for="name">商品名称:</label>
				<div class="col-lg-3 col-md-4">
					<input name="name" class="form-control" type="text">
				</div>
			</div>
			<div class="row form-group">
				<label class="control-label col-lg-1 col-md-2" for="name">商品价格:</label>
				<div class="col-lg-3 col-md-4">
					<div class="input-group">
						<div class="input-group-addon">F</div>
						<input name="price" class="form-control" type="text">
					</div>
				</div>
			</div>
			<div class="row form-group">
				<label class="control-label col-lg-1 col-md-2" for="name">商品库存:</label>
				<div class="col-lg-3 col-md-4">
					<div class="input-group">
						<input name="stock" class="form-control" type="text">
						<div class="input-group-addon">件</div>
					</div>
				</div>
			</div>
			<div class="row form-group">
				<label class="control-label col-lg-1 col-md-2" for="name">商品图片:</label>
				<div class="col-lg-8 col-md-4">
					<input id="pic_input" name="pictureFiles" type="file" multiple>
				</div>
			</div>
			<div class="row form-group">
				<label class="control-label col-lg-1 col-md-2">商品描述:</label>
				<div class="col-lg-8 col-md-10">
					<div id="editor"></div>
				</div>
			</div>
			<div class="row form-group">
				<label class="control-label col-lg-1 col-md-2"></label>
				<div class="col-lg-8 col-md-10">
					<input id="edit_item_desc" type="button" class="btn btn-primary"
						value="修改" /> <input id="reset_button" type="button"
						class="btn btn-info" style="margin-left: 30px" value="重置" />

				</div>
			</div>
		</div>
	</form>
</body>
<script src="/resources/plugins/jquery.1.12.4.min.js"></script>
<script src="/resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="/resources/plugins/waves-0.7.5/waves.min.js"></script>
<script src="/resources/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="/resources/plugins/wangEditor-3.1.1/wangEditor.min.js"></script>
<script src="/resources/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="/resources/plugins/bootstrap-fileinput/js/locales/zh.js"></script>
<script type="text/javascript">
	var E = window.wangEditor;
	var editor = new E('#editor');
	editor.customConfig.uploadImgMaxLength = 5;
	editor.customConfig.uploadImgServer = '/cms/editor/upload_pictures';
	editor.create();
	editor.config.uploadFileName = 'pictureFiles';

	$("#pic_input").fileinput({
		showUpload : false,
		dropZoneEnabled : false,
		maxFileCount : 6,
		mainClass : "input-group-lg",
		language : 'zh',
	});

	$("#reset_button").click(function() {
		$('#itemEditForm')[0].reset();
		editor.txt.clear();
	});

	$("#edit_item_desc").click(function() {
		var check = true;
		var inputs = $("#itemEditForm").find("input");
		var formData = new FormData();
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].value == "") {
				$.confirm({
					title : false,
					type : 'red',
					content : "请填写完整信息！",
					backgroundDismiss : true,
					buttons : {
						cancel : {
							text : '确认',
							btnClass : 'waves-effect waves-button',
						}
					}
				});
				return;
			}
			if (inputs[i].type != "radio" && inputs[i].type != "button") {
				if (inputs[i].name == "pictureFiles") {
					for (var j = 0; j < inputs[i].files.length; j++) {
						formData.append('pictureFiles', inputs[i].files[j]);
					}
				} else {
					formData.append(inputs[i].name, inputs[i].value);
				}
			} else {
				if (inputs[i].checked) {
					formData.append(inputs[i].name, inputs[i].value);
				}
			}
		}
		formData.append("description", editor.txt.html());
		$.ajax({
			url : '/cms/item_desc/edit',
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
				$.confirm({
					title : false,
					type : 'green',
					content : "修改积分商品信息成功！",
					backgroundDismiss : true,
					buttons : {
						cancel : {
							text : '确认',
							btnClass : 'waves-effect waves-button'
						}
					}
				});
				$('#itemAddForm')[0].reset();
				editor.txt.clear();
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
	})
</script>
</html>