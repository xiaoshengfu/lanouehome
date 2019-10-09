var $$ = Dom7;
var app = new Framework7({
	root : '#app',
	id : 'com.huishoucat.lanouehome',
	name : 'lanouehome',
	theme : 'ios',
	routes : routes,
});
var homeView = app.views.create('#view-home', {
	url : '/',
	stackPages : true,
});
var basketView = app.views.create('#view-basket', {
	url : '/basket/',
	stackPages : true,
});
var pointMallView = app.views.create('#view-point-mall', {
	url : '/point/mall/',
	stackPages : true,
});
var personView = app.views.create('#view-person', {
	url : '/person/',
	xhrCache : false,
});
var basketChangeNum = 0;
$$(document).on('page:init', function(e) {
	var history = e.detail.router.history;
	var url = e.detail.route.url;
	if (history[history.length - 1] == url) {
		if (url == '/' || url == '/person/' || url == '/basket/' || url == '/point/mall/') {
			$$('#bottom_toolbar').removeAttr('style');
		} else {

			$$('#bottom_toolbar').attr('style', 'display: none;');
		}
	}
})
$$(document).on('page:reinit', function(e) {
	var history = e.detail.router.history;
	var url = e.detail.route.url;
	if (history[history.length - 1] == url) {
		if (url == '/' || url == '/person/' || url == '/basket/' || url == '/point/mall/') {
			$$('#bottom_toolbar').removeAttr('style');
		} else {

			$$('#bottom_toolbar').attr('style', 'display: none;');
		}
	}
})
function getUserAddressData() {
	var sex = 1;
	var radio = $$('.user-address-sex');
	for (var i = 0; i < radio.length; i++) {
		if (radio[i].checked) {
			sex = radio[i].value;
			break;
		}
	}
	var name = $$("#user_address_name").val();
	var telephone = $$("#user_address_telephone").val();
	var districtName = $$("#user_address_district_name").val();
	var address = $$("#user_address_address").val();
	var state = 1;
	if ($$("#default_user_address").prop('checked')) {
		state = 2;
	}
	var districtId = $$("#user_address_district_id").val();
	var userAddressId = $$("#user_address_id").val();
	if (name.trim() != '' && districtName.trim() != '' && address.trim() != '' && districtId.trim() != '') {
		var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		if (reg.test(telephone)) {
			var data = {
				"sex" : sex,
				"name" : name,
				"state" : state,
				"address" : address,
				"telephone" : telephone,
				"districtId" : districtId,
				"districtName" : districtName,
			}
			if (userAddressId != undefined) {
				data.userAddressId = userAddressId;
			}
			return data;
		} else {
			return new String("请填写正确的手机号码！");
		}
	}
	return new String("请填写完整的信息！");
}
function formatReserveTime(reserveTime) {
	var date = new Date();
	date.setSeconds(0);
	date.setMilliseconds(0);
	var date1 = reserveTime.split(' ');
	if (date1[0] == '明天') {
		date.setTime(date.getTime() + 86400000);
	} else if (date1[0] == '后天') {
		date.setTime(date.getTime() + 172800000);
	}
	var date2 = date1[1].split(':');
	date.setHours(date2[0]);
	date.setMinutes(date2[1]);
	return date.getTime();
}
function getWasteOrderData() {
	var wasteOrderId = $$("#waste_order_id_confirm").val();
	var userAddressId = $$("#user_address_id_selected").val();
	var stringReserveTime = $$('#picker-reserve-time-toolbar').val();
	var message = $$('#user_message').val();
	var reserveTime = 0;
	if (stringReserveTime != '' && stringReserveTime != undefined) {
		reserveTime = formatReserveTime(stringReserveTime);
	}
	return {
		'wasteOrderId' : wasteOrderId,
		'userAddressId' : userAddressId,
		'message' : message,
		'reserveTime' : {
			'time' : reserveTime
		},
	}
}
function isRealNum(val) {
	if (val == null || val.trim() == "") {
		return false;
	}
	if (!isNaN(val)) {
		return true;
	} else {
		return false;
	}
}
function getWasteOrderItemData() {
	var wasteId = $$("#waste-id").val();
	var unitPoint = $$("#unit-point").val();
	var estimateUnitPrice = $$('#estimate-unit-price').val();
	var estimateNum = $$('#estimate-num').val();
	if (!isRealNum(estimateNum)) {
		estimateNum = 0;
	}
	var unit = $$('#unit').val();
	var attributePrice = 0;
	var description = [];
	$$('.attribute-content-select').each(function() {
		var os = this;
		for (var i = 0; i < os.length; i++) {
			if (os.options[i].selected) {
				attributePrice += parseInt(os.options[i].value);
				description[description.length] = os.options[i].text;
			}
		}
	});
	return {
		'wasteId' : wasteId,
		'unitPoint' : unitPoint,
		'estimateNum' : estimateNum,
		'estimateUnitPrice' : estimateUnitPrice,
		'attributePrice' : attributePrice,
		'unit' : unit,
		'description' : description.join(','),
	}
}
function basketNumChange(addNum) {
	var basketWasteNum = $$('#basket_waste_num_div').html();
	var trimBasketWasteNum = basketWasteNum.trim();
	if (trimBasketWasteNum == "" || trimBasketWasteNum == undefined) {
		$$('#basket_waste_num_div').html('<span id="basket_waste_num" class="badge color-red">' + addNum + '</span>');
		$$('#basket_waste_num_div_fill').html('<span id="basket_waste_num_fill" class="badge color-red">' + addNum + '</span>');
	} else {
		var num = parseInt($$('#basket_waste_num').html()) + parseInt(addNum);
		if (num == 0) {
			$$('#basket_waste_num_div').html('');
			$$('#basket_waste_num_div_fill').html('');
			return;
		}
		$$('#basket_waste_num').html(num);
		$$('#basket_waste_num_fill').html(num);
	}
}
function parseParam(param, key) {
	var paramStr = "";
	if (param instanceof String || param instanceof Number || param instanceof Boolean) {
		paramStr += "&" + key + "=" + encodeURIComponent(param);
	} else {
		$.each(param, function(i) {
			var k = key == null ? i : key + (param instanceof Array ? "[" + i + "]" : "." + i);
			paramStr += '&' + parseParam(this, k);
		});
	}
	return paramStr.substr(1);
}
;
$$(document).on('page:init', '.page[data-name="waste-details"]', function(e) {
	var toastIcon = app.toast.create({
		icon : app.theme === 'ios' ? '<i class="f7-icons">star</i>' : '<i class="material-icons">star</i>',
		text : '添加回收筐成功！',
		position : 'center',
		closeTimeout : 2000,
	});
	$$('.attribute-content-select').on('change', function(e) {
		var item = getWasteOrderItemData();
		var price = item.estimateNum * (parseInt(item.estimateUnitPrice) / 100 + parseInt(item.attributePrice) / 100);
		$$('#estimate-price').val(new Number(price > 0 ? price : 0).toFixed(2));
		$$('#estimate-point').val(item.estimateNum * parseInt(item.unitPoint));
	});
	$$('#estimate-num').on('input propertychange', function(e) {
		var item = getWasteOrderItemData();
		var price = item.estimateNum * (parseInt(item.estimateUnitPrice) / 100 + parseInt(item.attributePrice) / 100);
		$$('#estimate-price').val(new Number(price > 0 ? price : 0).toFixed(2));
		$$('#estimate-point').val(item.estimateNum * parseInt(item.unitPoint));
		if (item.estimateNum == 0) {
			$$('#add_basket_button').addClass('add-basket');
			$$('#add_basket_button').prop('disabled', true);
		} else {
			$$('#add_basket_button').removeClass('add-basket');
			$$('#add_basket_button').prop('disabled', false);
		}
	});
	$$('#add_basket_button').on('click', function(e) {
		var item = getWasteOrderItemData();
		if (item.estimateNum == 0) {
			return;
		}
		app.request.postJSON('/wechat/basket/add', item, function() {
			basketNumChange(1);
			toastIcon.open();
		});
	});
})
$$(document).on('page:init', '.page[data-name="basket"]', function(e) {
	$$('#a_basket').on('click', function() {
		e.detail.router.refreshPage();
	});
	$$('#qxi').on('change', function(e) {
		var i = e.srcElement.checked;
		if (i) {
			$$('input[type="checkbox"]').prop('checked', true);
			var totalMoney = 0;
			var totalSelected = 0;
			$$('.dx').each(function() {
				totalSelected++;
				totalMoney += parseInt(this.getAttribute('estimateNum')) * parseInt(this.getAttribute('estimateUnitPrice'));
			});
			$$('#settlement_num').html('结算(' + totalSelected + ')');
			$$('#settlement_delete').html('删除(' + totalSelected + ')');
			if (totalMoney > 0) {
				$$('#waste_order_submit').attr('style', 'background-color:#007aff');
			} else {
				$$('#waste_order_submit').attr('style', 'background-color:#8e8e93');
			}
			$$('#totalMoney').text(new Number(totalMoney / 100).toFixed(2));
		} else {
			$$('#settlement_num').html('结算(0)');
			$$('#settlement_delete').html('删除(0)');
			$$('input[type="checkbox"]').prop('checked', false);
			$$('#waste_order_submit').attr('style', 'background-color:#8e8e93');
			$$('#totalMoney').text('0.00');
		}
	});
	$$('.dx').on('change', function(e) {
		var ischeck = e.srcElement.checked;
		if (ischeck) {
			var checkbox = $$('.dx');
			for (var i = 0; i < checkbox.length; i++) {
				if (!(checkbox[i].checked)) {
					ischeck = false;
				}
			}
		}
		$$('#qxi').prop('checked', ischeck);
		var totalMoney = 0;
		var totalSelected = 0;
		$$('.dx').each(function() {
			if (this.checked) {
				totalSelected++;
				totalMoney += parseInt(this.getAttribute('estimateNum')) * parseInt(this.getAttribute('estimateUnitPrice'));
			}
		});
		$$('#settlement_num').html('结算(' + totalSelected + ')');
		$$('#settlement_delete').html('删除(' + totalSelected + ')');
		if (totalMoney > 0) {
			$$('#waste_order_submit').attr('style', 'background-color:#007aff');
		} else {
			$$('#waste_order_submit').attr('style', 'background-color:#8e8e93');
		}
		$$('#totalMoney').text(new Number(totalMoney / 100).toFixed(2));
	});
	$$('#waste_order_submit').on('click', function() {
		var wasteOrderItemIds = [];
		$$('.dx').each(function() {
			if (this.checked) {
				wasteOrderItemIds[wasteOrderItemIds.length] = this.value;
			}
		});
		if (wasteOrderItemIds.length == 0) {
			return;
		}
		basketChangeNum = -wasteOrderItemIds.length;
		e.detail.router.navigate('/user/waste_order/submit/?' + 'wasteOrderItemIds=' + wasteOrderItemIds.join(","), {
			async : false,
			ignoreCache : true,
		});
	});
	$$('.order-item-delete').on('click', function() {
		basketNumChange(-1);
		var wasteOrderItemIds = [ this.getAttribute("itemId") ];
		app.request.postJSON('/wechat/basket/delete', JSON.stringify(wasteOrderItemIds), function() {});
	});
	$$('#waste_order_item_delete').on('click', function() {
		var wasteOrderItemIds = [];
		$$('.dx').each(function() {
			if (this.checked) {
				wasteOrderItemIds[wasteOrderItemIds.length] = this.value;
			}
		});
		if (wasteOrderItemIds.length == 0) {
			return;
		}
		app.dialog.confirm('确认删除这' + wasteOrderItemIds.length + '项内容吗?', '提示', function() {
			$$('#qxi').prop("checked", false);
			$$('#totalMoney').text('0.00');
			$$('#settlement_num').html('结算(0)');
			$$('#settlement_delete').html('删除(0)');
			$$("#delete" + wasteOrderItemIds[0]).click();
			for (var i = 0; i < wasteOrderItemIds.length; i++) {
				$$("#delete" + wasteOrderItemIds[i]).click();
			}
		});
	});
	$$('.deleteall').on('click', function(e) {
		if ($$(this).hasClass('deleteallback')) {
			$$(this).removeClass('deleteallback')
			$$("#basket_manager").html("管理");
			$$('#footer').show();
			$$('#footer2').hide();
		} else {
			$$(this).addClass('deleteallback');
			$$("#basket_manager").html("取消");
			$$('#footer').hide();
			$$('#footer2').show();
		}
	});
});
$$(document).on('page:init', '.page[data-name="person"]', function(e) {
	var totalNewNum = 0;
	var notReadNotificationNum = $$('#not_read_notification_num').text();
	var newWasteOrderNum = $$('#new_waste_order_num').text();
	if (notReadNotificationNum != undefined && notReadNotificationNum.trim() != '') {
		totalNewNum += parseInt(notReadNotificationNum.trim());
	}
	if (newWasteOrderNum != undefined && newWasteOrderNum.trim() != '') {
		totalNewNum += parseInt(newWasteOrderNum.trim());
	}
	if (totalNewNum != 0) {
		$$('#totalNewNum').html('<span class="badge color-red">' + totalNewNum + '</span>');
		$$('#totalNewNum_fill').html('<span class="badge color-red">' + totalNewNum + '</span>');
	} else {
		$$('#totalNewNum').html('');
		$$('#totalNewNum_fill').html('');
	}
	$$('#a_person').on('click', function() {
		e.detail.router.refreshPage();
	});
});
$$(document).on('page:init', '.page[data-name="confirm-waste-order"]', function(e) {
	var pickerCustomToolbar = app.picker.create({
		inputEl : '#picker-reserve-time-toolbar',
		rotateEffect : true,
		renderToolbar : function() {
			return '<div class="toolbar">' +
				'<div class="toolbar-inner">' +
				'<div class="left">预约时间</div>' +
				'<div class="right">' +
				'<a href="#" class="link sheet-close popover-close">确定</a>' +
				'</div>' +
				'</div>' +
				'</div>';
		},
		formatValue : function(values, displayValues) {
			return values[0] + ' ' + values[1] + ':' + values[2];
		},
		cols : [
			// Hours
			{
				values : [ '今天', '明天', '后天' ]
			},
			// Hours
			{
				values : (function() {
					var arr = [];
					for (var i = 0; i <= 23; i++) {
						arr.push(i);
					}
					return arr;
				})(),
			},
			// Divider
			{
				divider : true,
				content : ':'
			},
			// Minutes
			{
				values : (function() {
					var arr = [];
					for (var i = 0; i <= 59; i++) {
						arr.push(i < 10 ? '0' + i : i);
					}
					return arr;
				})(),
			}
		],
		on : {
			open : function(picker) {
				picker.$el.find('.toolbar-randomize-link').on('click', function() {
					var col0Values = picker.cols[0].values;
					var col0Random = col0Values[Math.floor(Math.random() * col0Values.length)];

					var col1Values = picker.cols[1].values;
					var col1Random = col1Values[Math.floor(Math.random() * col1Values.length)];

					var col2Values = picker.cols[2].values;
					var col2Random = col2Values[Math.floor(Math.random() * col2Values.length)];

					picker.setValue([ col0Random, col1Random, col2Random ]);
				});
			},
		},
	});
	$$('#back_basket').on('click', function() {
		if (e.detail.router.history[0] == '/basket/') {
			e.detail.router.back('/basket/', {
				force : true,
				animate : true,
				ignoreCache : true,
			});
			basketNumChange(basketChangeNum);
			basketChangeNum = 0;
		}
	});
	$$('#link_add_user_address').on('click', function() {
		$$('.popup-close').click();
		e.detail.router.navigate('/user/user_address/add/page/', {
			async : false,
		});
	});
	$$('#address_popup').on('click', function() {
		app.request.postJSON('/wechat/user/user_address/get_list', {}, function(data) {
			if (data.stateCode == 200) {
				var userAddressListHtml = '';
				if (data.data != null && data.data != undefined) {
					for (var i = 0; i < data.data.length; i++) {
						userAddressListHtml += '<li><a href="#" userAddressId="' + data.data[i].userAddressId + '" class="choosedz item-link item-content">' +
							'<div class="item-inner">' +
							'<div class="item-title-row">' +
							'<div class="item-title">' + data.data[i].name + '</div>' +
							'<div class="item-after" style="color: black;">' + data.data[i].telephone + '</div>' +
							'</div>' +
							'<div class="item-text" style="font-size: 12px;">' + data.data[i].address + '</div>' +
							'</div>' +
							'</a></li>'
					}
				}
				$$('#select_address_list').html(userAddressListHtml);
				$$('.choosedz').on('click', function() {
					$$('#user_address_id_selected').val(this.getAttribute("userAddressId"));
					var name = $$(this).find('.item-title').html();
					var phone = $$(this).find('.item-after').html();
					var dz = $$(this).find('.item-text').html().trim();
					$$('#setname').html("收货人：" + name);
					$$('#setphone').html(phone);
					$$('#setdz').html("收货地址：" + dz);
					$$('.popup-close').click();
				});
				$$('#open_user_address_popup').click();
			} else {
				app.dialog.alert(data.message);
			}
		}, "json");
	});
	$$('#waste_order_confirm').on('click', function() {
		var orderData = getWasteOrderData();
		var param = parseParam(orderData, null);
		basketNumChange(basketChangeNum);
		basketChangeNum = 0;
		e.detail.router.navigate('/user/waste_order/confirm/?' + param, {
			async : false,
			ignoreCache : true,
		});
	});
	$$('#picker-reserve-time-toolbar').on('change', function() {
		var orderData = getWasteOrderData();
		if (orderData.userAddressId.trim() != '' && orderData.reserveTime != 0 && orderData.reserveTime != undefined) {
			$$('#waste_order_confirm').attr('style', 'background-color:#007aff');
			$$('#waste_order_confirm').prop('disabled', false);
		} else {
			$$('#waste_order_confirm').prop('disabled', true);
		}
	});
	$$('#waste_order_confirm').prop('disabled', true);
})
$$(document).on('page:init', '.page[data-name="user-login"]', function(e) {
	var wait = 60;
	function time(o) {
		if (wait == 0) {
			$$('#denglu_getcode').prop({
				'disabled' : false,
				'innerHTML' : "获取验证码"
			});
			var tel = $$("#denglu_tel").val();
			if (tel.match('[0-9]{11}')) {
				$$('#denglu_getcode').removeClass('denglutm');
				$$('#denglu_getcode').prop('disabled', false);
			} else {
				$$('#denglu_getcode').addClass('denglutm');
				$$('#denglu_getcode').prop('disabled', true);
			}
			wait = 60;
		} else {
			$$('#denglu_getcode').prop({
				'disabled' : true,
				'innerHTML' : "等待" + wait + "s"
			});
			wait--;
			setTimeout(function() {
				time(o)
			}, 1000);
		}
	}
	$$('#denglu_tel').on('input propertychange', function(e) {
		var tel = this.value;
		var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		if (reg.test(tel)) {
			$$('#denglu_getcode').removeClass('denglutm');
			$$('#denglu_getcode').prop('disabled', false);
		} else {
			$$('#denglu_getcode').addClass('denglutm');
			$$('#denglu_getcode').prop('disabled', true);
		}
	});
	$$('#denglu_getcode').on('click', function(e) {
		$$('#denglu_getcode').addClass('denglutm');
		$$('#denglu_getcode').prop('disabled', true);
		time(this);
		$.ajax({
			type : 'get',
			url : 'https://sso.wujiantao.cn/sso/user_login/send_sms?telephone=' + $$('#denglu_tel').val(),
			dataType : 'jsonp',
			success : function(data) {
				if (data.stateCode != 200) {
					app.dialog.alert(data.message);
				}
			},
		});
	});
	$$('#denglu_yzm').on('input propertychange', function(e) {
		var yzm = this.value;
		if (yzm.match('[0-9]{6}')) {
			$$('#denglu_dl').removeClass('denglutm');
			$$('#denglu_dl').prop('disabled', false);
		} else {
			$$('#denglu_dl').addClass('denglutm');
			$$('#denglu_dl').prop('disabled', true);
		}
	})
	$$('#denglu_dl').on('click', function() {
		var urls = "https://sso.wujiantao.cn/sso/user_login/telephone?&telephone=" + $$('#denglu_tel').val() + "&verificationCode=" + $$('#denglu_yzm').val() + "&output=jsonp";
		$.ajax({
			type : 'get',
			url : urls,
			dataType : 'jsonp',
			success : function(data) {
				if (data.stateCode == 200) {
					$.cookie('token', data.data, {
						expires : 1,
						path : '/'
					});
					var history = e.detail.router.history;
					e.detail.router.back(history[history.length - 2], {
						force : true,
						animate : true,
						ignoreCache : true,
					});
				} else {
					app.dialog.alert("验证码错误！");
				}
			},
		});
	})
});
$$(document).on('page:init', '.page[data-name="waste-order-list"]', function(e) {
	function cancelWasteOrder(e, wasteOrderId, invalidReason) {
		app.request.get("/wechat/user/waste_order/cancel", {
			'wasteOrderId' : $$('#delete_waste_order_id').val(),
			'invalidReason' : invalidReason,
		}, function(data) {
			if (data.stateCode == 200) {
				e.detail.router.refreshPage();
			}
		}, "json")
	}
	var cancel = app.actions.create({
		buttons : [
			[
				{
					text : '退单原因',
					label : true
				},
				{
					text : '不想卖了',
					color : 'black',
					onClick : function() {
						cancelWasteOrder(e, '不想卖了');
					}
				},
				{
					text : '重新下单',
					color : 'black',
					onClick : function() {
						cancelWasteOrder(e, '重新下单');
					}
				},
				{
					text : '其他原因',
					color : 'black',
					onClick : function() {
						cancelWasteOrder(e, '其他原因');
					}
				}
			],
			[
				{
					text : '取消',
					color : 'black',
				}
			]
		]
	});
	$$('.tel-recycler').on('click', function() {
		app.dialog.confirm('需要拨打' + this.getAttribute("telephone") + '么?', '提示', function() {});
	});
	$$('.waste_order_list_cancel').on('click', function() {
		$$('#delete_waste_order_id').val(this.getAttribute("waste_order_id"));
		cancel.open();
	});
	$$('.waste_order_list_confirm').on('click', function() {
		e.detail.router.navigate('/user/waste_order/confirm/page/?' + 'wasteOrderId=' + this.getAttribute("waste_order_id"), {
			async : false,
			ignoreCache : true,
		});
	});
});
$$(document).on('page:init', '.page[data-name="user-address-list"]', function(e) {
	$$('.user-address-edit').on('click', function() {
		e.detail.router.navigate('/user/user_address/edit/page/?' + 'userAddressId=' + this.getAttribute("address_id"), {
			async : false,
			ignoreCache : true,
		});
	});
	$$('.user-address-delete').on('click', function() {
		var userAddressId = this.getAttribute("address_id");
		app.dialog.confirm('确认删除这这条地址信息吗?', '提示', function() {
			app.request.get("/wechat/user/user_address/delete", {
				'userAddressId' : userAddressId,
			}, function(data) {
				if (data.stateCode == 200) {
					e.detail.router.refreshPage();
				} else {
					app.dialog.alert(data.message);
				}
			}, "json")
		});
	});
	$$('.setdefault-user-address').on('change', function() {
		if (this.checked) {
			app.request.get('/wechat/user/user_address/set_default', {
				userAddressId : this.value
			}, function(data) {
				e.detail.router.refreshPage();
				if (data.stateCode != 200) {
					app.dialogalert(data.message);
				}
			}, 'json');
		}
	});
});
$$(document).on('page:init', '.page[data-name="user-address-add"],.page[data-name="user-address-edit"]', function(e) {
	window.addEventListener('message', function(event) {
		// 接收位置信息，用户选择确认位置点后选点组件会触发该事件，回传用户的位置信息
		var loc = event.data;
		if (loc && loc.module == 'locationPicker') { //防止其他应用也会向该页面post信息，需判断module是否为'locationPicker'

		}
		var urls = "http://apis.map.qq.com/ws/geocoder/v1/?location=" + loc.latlng.lat + "," + loc.latlng.lng + "&key=TLHBZ-EUMKS-NWKOE-6I74P-PSIAO-V2BRQ&get_poi=0&output=jsonp";
		$.ajax({
			type : 'get',
			url : urls,
			dataType : 'jsonp',
			success : function(dj) {
				var datajson = dj.result;
				var address = datajson.address;
				var address_component_province = datajson.address_component.province;
				var address_component_city = datajson.address_component.city;
				var address_component_district = datajson.address_component.district;
				var user_address_district_name = address_component_province + " " + address_component_city + " " + address_component_district;
				var user_address_address = address;
				var user_address_district_id = datajson.ad_info.adcode;
				$("#user_address_district_name").val(user_address_district_name);
				$("#user_address_address").val(user_address_address);
				$("#user_address_district_id").val(user_address_district_id);
			},
		});
	}, false);
	$$('#user_address_edit_save').on('click', function() {
		var data = getUserAddressData();
		if (data instanceof String) {
			app.dialog.alert(data);
		} else {
			app.request.postJSON('/wechat/user/user_address/edit', data, function(data) {
				if (data.stateCode == 200) {
					var history = e.detail.router.history;
					var url = history[history.length - 2];
					if (url == '/user/user_address/list/') {
						e.detail.router.back(url, {
							force : true,
							animate : true,
							ignoreCache : true,
						});
					} else {
						e.detail.router.back();
					}
				} else {
					app.dialog.alert(data.message);
				}
			}, "json");
		}
	});
	$$('#user_address_add_save').on('click', function() {
		var data = getUserAddressData();
		if (data instanceof String) {
			app.dialog.alert(data);
		} else {
			app.request.postJSON('/wechat/user/user_address/add', data, function(data) {
				if (data.stateCode == 200) {
					var history = e.detail.router.history;
					var url = history[history.length - 2];
					if (url == '/user/user_address/list/') {
						e.detail.router.back(url, {
							force : true,
							animate : true,
							ignoreCache : true,
						});
					} else {
						e.detail.router.back();
					}
				} else {
					app.dialog.alert(data.message);
				}
			}, "json");
		}
	});
});
$$(document).on('page:init', '.page[data-name="confirm-waste-order-success"]', function(e) {
	$$('#confirm_success_find_order').on('click', function() {
		e.detail.router.back('/user/waste_order/list/', {
			force : true,
			animate : true,
			ignoreCache : true,
		});
	});
})
$$(document).on('page:init', '.page[data-name="user-information"]', function(e) {
	$$('#basket_waste_num_div').html('');
	$$('#basket_waste_num_div_fill').html('');
	$$('#totalNewNum').html('');
	$$('#totalNewNum_fill').html('');
	$$('#user_sign_out').on('click', function() {
		$.cookie('token', null, {
			path : '/'
		});
		$.cookie('token', '', {
			expires : -1,
			path : '/'
		});
		e.detail.router.back('/person/', {
			force : true,
			animate : true,
			ignoreCache : true,
		});
	});
})
$$(document).on('page:init', '.page[data-name="user-register"]', function(e) {
	var toastTop = app.toast.create({
		text : '注册成功！',
		position : 'top',
		closeTimeout : 2000,
	});
	var wait = 60;
	function time(o) {
		if (wait == 0) {
			$$('#zhuce_getcode').prop({
				'disabled' : false,
				'innerHTML' : "获取验证码"
			});
			var tel = $$("#zhuce_tel").val();
			if (tel.match('[0-9]{11}')) {
				$$('#zhuce_getcode').removeClass('zhucetm');
				$$('#zhuce_getcode').prop('disabled', false);
			} else {
				$$('#zhuce_getcode').addClass('zhucetm');
				$$('#zhuce_getcode').prop('disabled', true);
			}
			wait = 60;
		} else {
			$$('#zhuce_getcode').prop({
				'disabled' : true,
				'innerHTML' : "等待" + wait + "s"
			});
			wait--;
			setTimeout(function() {
				time(o)
			}, 1000);
		}
	}
	$$('#zhuce_tel').on('input propertychange', function(e) {
		var tel = $$(this).val();
		if (tel.match('[0-9]{11}')) {
			$$('#zhuce_getcode').removeClass('zhucetm');
			$$('#zhuce_getcode').prop('disabled', false);
		} else {
			$$('#zhuce_getcode').addClass('zhucetm');
			$$('#zhuce_getcode').prop('disabled', true);
		}
	});
	$$('#zhuce_getcode').on('click', function(e) {
		$$('#zhuce_getcode').addClass('zhucetm');
		$$('#zhuce_getcode').prop('disabled', true);
		time(this);
		$.ajax({
			type : 'get',
			url : 'https://sso.wujiantao.cn/sso/user_register/send_sms?telephone=' + $$('#zhuce_tel').val(),
			dataType : 'jsonp',
			success : function(data) {
				if (data.stateCode != 200) {
					app.dialog.alert(data.message);
				}
			},
		});
	});
	$$('#zhuce_yzm').on('input propertychange', function(e) {
		var yzm = $$(this).val();
		if (yzm.match('[0-9]{6}')) {
			$$('#zhuce_dl').removeClass('zhucetm');
			$$('#zhuce_dl').prop('disabled', false);
		} else {
			$$('#zhuce_dl').addClass('zhucetm');
			$$('#zhuce_dl').prop('disabled', true);
		}
	})
	$$('#zhuce_dl').on('click', function() {
		var urls = "https://sso.wujiantao.cn/sso/user_register/activate?&telephone=" + $$('#zhuce_tel').val() + "&verificationCode=" + $$('#zhuce_yzm').val() + "&openid=" + $.cookie('userOpenid') + "&output=jsonp";
		console.log(urls);
		$.ajax({
			type : 'get',
			url : urls,
			dataType : 'jsonp',
			success : function(data) {
				if (data.stateCode == 200) {
					$.cookie('token', data.data, {
						expires : 1,
						path : '/'
					});
					toastTop.open();
					var history = e.detail.router.history;
					e.detail.router.back(history[history.length - 3], {
						force : true,
						animate : true,
						ignoreCache : true,
					});
				} else {
					app.dialog.alert("验证码错误！");
				}
			},
		});
	})
});
$$(document).on('page:init', '.page[data-name="balance-withdrawals"]', function(e) {
	var mykeyboard = $.keyboard({
		num : 6,
		title : "支付密码",
		msg : "正在验证支付密码",
		skin : "jianpan",
		links : "<a href='#'>忘记密码？</a>",
		callback : function(data) {
			if ('541882' == data.join('')) {
				e.detail.router.navigate('/user/balance/withdrawals/?money=' + $$('#tixian_input').val() * 100, {
					async : false,
					ignoreCache : true,
				});
				mykeyboard.hideKeyBoard();
			} else {
				setTimeout(function() {
					mykeyboard.reset("支付密码错误，请重新输入！");
				}, 2000)
			}
		}
	});
	$("#tixain_submit").click(function() {
		mykeyboard.showKeyBoard();
	});
	$$('#tixian_all').on('click', function() {
		var yue = $$('#tixian_yue').html();
		$$('#tixian_input').val(yue);
		$$('#tixain_submit').attr('style', "opacity: 1");
		$$('#tixain_submit').prop('disabled', false);
	});
	$$('#tixian_input').on('keyup', function(obj) {
		var v = $$('#tixian_input').val()
		v = v.replace(/[^\d.]/g, ""); //清除"数字"和"."以外的字符
		v = v.replace(/^\./g, ""); //验证第一个字符是数字
		v = v.replace(/\.{2,}/g, "."); //只保留第一个, 清除多余的
		v = v.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		v = v.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
		$$('#tixian_input').val(v)
	});
	$$('#tixian_input').on('input propertychange', function() {
		var v = $$('#tixian_input').val();
		var v2 = $$('#tixian_yue').html();
		if (v == "") {
			$$('#im1').attr('style', 'font-size: 12px; color: #8e8e93');
			$$('#im2').attr('style', 'font-size: 12px; color:#ff3b30;display: none;');
			$$('#tixain_submit').attr('style', "opacity: 0.5");
			$$('#tixain_submit').prop('disabled', true);
		} else {
			if (parseFloat(v) <= parseFloat(v2)) {
				$$('#im1').attr('style', 'font-size: 12px; color: #8e8e93');
				$$('#im2').attr('style', 'font-size: 12px; color:#ff3b30;display: none;');
				$$('#tixain_submit').attr('style', "opacity: 1");
				$$('#tixain_submit').prop('disabled', false);
			} else {
				$$('#im1').attr('style', 'font-size: 12px; color: #8e8e93;display: none;');
				$$('#im2').attr('style', 'font-size: 12px; color:#ff3b30;');
				$$('#tixain_submit').attr('style', "opacity: 0.5");
				$$('#tixain_submit').prop('disabled', true);
			}
		}
	});
});
$$(document).on('page:init', '.page[data-name="withdrawals-success"]', function(e) {
	$$('#back_balance_button').on('click', function() {
		e.detail.router.back('/user/balance/', {
			force : true,
			animate : true,
			ignoreCache : true,
		});
	});
});
$$(document).on('page:init', '.page[data-name="notification-list"]', function(e) {
	$$('#all_change_read').on('click', function() {
		app.request.get("/wechat/user/notification/read_all", {
		}, function(data) {
			if (data.stateCode == 200) {
				e.detail.router.refreshPage();
			}
		}, "json")
	});
});
$$(document).on('page:init', '.page[data-name="notification-details"]', function(e) {
	$$('#back_notification_list').on('click', function() {
		e.detail.router.back('/user/notification/list/', {
			force : true,
			animate : true,
			ignoreCache : true,
		});
	});
});