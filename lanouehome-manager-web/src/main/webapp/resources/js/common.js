$(function() {
	// Waves初始化
	Waves.displayEffect();
	// 数据表格动态高度
	$(window).resize(function() {
		$('#table').bootstrapTable('resetView', {
			height : getHeight()
		});
	});
	// 设置input特效
	$(document).on('focus', 'input[type="text"]', function() {
		$(this).parent().find('label').addClass('active');
	}).on('blur', 'input[type="text"]', function() {
		if ($(this).val() == '') {
			$(this).parent().find('label').removeClass('active');
		}
	});
});
// 动态高度
function getHeight() {
	return $(window).height() - 20;
}
// 数据表格展开内容
function detailFormatter(index, row) {
	var html = [];
	$.each(row, function(key, value) {
		html.push('<p><b>' + key + ':</b> ' + value + '</p>');
	});
	return html.join('');
}
// 初始化input特效
function initMaterialInput() {
	$('form input[type="text"]').each(function() {
		if ($(this).val() != '') {
			$(this).parent().find('label').addClass('active');
		}
	});
}
//删除
function deleteAction(url, fieldName) {
	var rows = $table.bootstrapTable('getSelections');
	if (rows.length == 0) {
		$.confirm({
			title : false,
			type : 'blue',
			content : '请选择至少一条记录！',
			autoClose : 'cancel|3000',
			backgroundDismiss : true,
			buttons : {
				cancel : {
					text : '取消',
					btnClass : 'waves-effect waves-button'
				}
			}
		});
	} else {
		$.confirm({
			type : 'red',
			animationSpeed : 300,
			title : false,
			content : '确认删除' + rows.length + '条数据吗？',
			buttons : {
				confirm : {
					text : '确认',
					btnClass : 'waves-effect waves-button',
					action : function() {
						var ids = new Array();
						for (var i in rows) {
							ids.push(rows[i][fieldName]);
						}
						$.ajax({
							type : "POST",
							url : url,
							data : JSON.stringify(ids),
							dataType : "json",
							contentType : 'application/json',
							success : function(data) {
								if (data.stateCode == 200) {
									$('#table').bootstrapTable('remove', {
										field : fieldName,
										values : ids
									})
								} else {
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
				},
				cancel : {
					text : '取消',
					btnClass : 'waves-effect waves-button'
				}
			}
		});
	}
}