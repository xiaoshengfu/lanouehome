<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/resources/images/huishoucat.ico" />
<title>蓝鸥e家后台管理系统</title>

<link href="/resources/plugins/fullPage/jquery.fullPage.css"
	rel="stylesheet" />
<link href="/resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="/resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css"
	rel="stylesheet" />
<link href="/resources/plugins/waves-0.7.5/waves.min.css"
	rel="stylesheet" />
<link
	href="/resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css"
	rel="stylesheet" />
<link href="/resources/css/admin.css" rel="stylesheet" />
<style>
/** skins **/
#lanouehome-ucenter #header {
	background: #29A176;
}

#lanouehome-ucenter .content_tab {
	background: #29A176;
}

#lanouehome-ucenter .s-profile>a {
	background: url(/resources/images/zheng-upms.png) left top no-repeat;
}

#lanouehome-cms #header {
	background: #455EC5;
}

#lanouehome-cms .content_tab {
	background: #455EC5;
}

#lanouehome-cms .s-profile>a {
	background: url(/resources/images/zheng-cms.png) left top no-repeat;
}

#lanouehome-pay #header {
	background: #F06292;
}

#lanouehome-pay .content_tab {
	background: #F06292;
}

#lanouehome-pay .s-profile>a {
	background: url(/resources/images/zheng-pay.png) left top no-repeat;
}

#lanouehome-order #header {
	background: #6539B4;
}

#lanouehome-order .content_tab {
	background: #6539B4;
}

#lanouehome-order .s-profile>a {
	background: url(/resources/images/zheng-ucenter.png) left top no-repeat;
}

#lanouehome-message #header {
	background: #0B8DE5;
}

#lanouehome-message .content_tab {
	background: #0B8DE5;
}

#lanouehome-message .s-profile>a {
	background: url(/resources/images/zheng-oss.png) left top no-repeat;
}

#lanouehome-upms #header {
	background: #29A176;
}

#lanouehome-upms .content_tab {
	background: #29A176;
}

#lanouehome-upms .s-profile>a {
	background: url(/resources/images/zheng-upms.png) left top no-repeat;
}

#test #header {
	background: test;
}

#test .content_tab {
	background: test;
}

#test .s-profile>a {
	background: url(test) left top no-repeat;
}
</style>
</head>
<body>
	<header id="header">
		<ul id="menu">
			<li id="guide" class="line-trigger">
				<div class="line-wrap">
					<div class="line top"></div>
					<div class="line center"></div>
					<div class="line bottom"></div>
				</div>
			</li>
			<li id="logo" class="hidden-xs"><a href="/index"> <img
					src="/resources/images/logo.png" />
			</a> <span id="system_title">用户管理系统</span></li>
			<li class="pull-right">
				<ul class="hi-menu">
					<!-- 搜索 -->
					<li class="dropdown"><a class="waves-effect waves-light"
						data-toggle="dropdown" href="javascript:;"> <i
							class="him-icon zmdi zmdi-search"></i>
					</a>
						<ul class="dropdown-menu dm-icon pull-right">
							<form id="search-form" class="form-inline">
								<div class="input-group">
									<input id="keywords" type="text" name="keywords"
										class="form-control" placeholder="搜索" />
									<div class="input-group-btn">
										<button type="submit" class="btn btn-default">
											<span class="glyphicon glyphicon-search"></span>
										</button>
									</div>
								</div>
							</form>
						</ul></li>
					<li class="dropdown"><a class="waves-effect waves-light"
						data-toggle="dropdown" href="javascript:;"> <i
							class="him-icon zmdi zmdi-dropbox"></i>
					</a>
						<ul class="dropdown-menu dm-icon pull-right">
							<li class="skin-switch">请选择系统切换</li>
							<li class="divider"></li>
							<li><a class="waves-effect switch-systems"
								href="javascript:;" systemid="1" systemname="lanouehome-ucenter"
								systemtitle="用户管理系统"><i class="zmdi zmdi-account"></i>用户管理系统</a>
							</li>

							<li><a class="waves-effect switch-systems"
								href="javascript:;" systemid="2" systemname="lanouehome-cms"
								systemtitle="内容管理系统"><i class="zmdi zmdi-wikipedia"></i>内容管理系统</a>
							</li>

							<li><a class="waves-effect switch-systems"
								href="javascript:;" systemid="3" systemname="lanouehome-pay"
								systemtitle="支付管理系统"><i class="zmdi zmdi-paypal-alt"></i>支付管理系统</a>
							</li>

							<li><a class="waves-effect switch-systems"
								href="javascript:;" systemid="4" systemname="lanouehome-order"
								systemtitle="订单管理系统"><i class="zmdi zmdi-shopping-cart"></i>订单管理系统</a>
							</li>

							<li><a class="waves-effect switch-systems"
								href="javascript:;" systemid="5" systemname="lanouehome-message"
								systemtitle="消息管理系统"><i class="zmdi zmdi-email"></i>消息管理系统</a></li>
							<li><a class="waves-effect switch-systems"
								href="javascript:;" systemid="6" systemname="lanouehome-upms"
								systemtitle="权限管理系统"><i class="zmdi zmdi-shield-security"></i>权限管理系统</a>
							</li>
						</ul></li>
					<li class="dropdown"><a class="waves-effect waves-light"
						data-toggle="dropdown" href="javascript:;"> <i
							class="him-icon zmdi zmdi-more-vert"></i>
					</a>
						<ul class="dropdown-menu dm-icon pull-right">
							<li class="hidden-xs"><a class="waves-effect"
								data-ma-action="fullscreen" href="javascript:fullPage();"><i
									class="zmdi zmdi-fullscreen"></i> 全屏模式</a></li>
							<li><a class="waves-effect"
								data-ma-action="clear-localstorage" href="javascript:;"><i
									class="zmdi zmdi-delete"></i> 清除缓存</a></li>
							<li><a class="waves-effect" href="javascript:;"><i
									class="zmdi zmdi-face"></i> 隐私管理</a></li>
							<li><a class="waves-effect" href="javascript:;"><i
									class="zmdi zmdi-settings"></i> 系统设置</a></li>
							<li><a class="waves-effect" href="javascript:;"><i
									class="zmdi zmdi-run"></i> 退出登录</a></li>
						</ul></li>
				</ul>
			</li>
		</ul>
	</header>
	<section id="main">
		<!-- 左侧导航区 -->
		<aside id="sidebar">
			<!-- 个人资料区 -->
			<div class="s-profile">
				<a class="waves-effect waves-light" href="javascript:;">
					<div class="sp-pic">
						<img src="/resources/images/head_portrait.jpg" />
					</div>
					<div class="sp-info">
						${loginManager.name}，您好！ <i class="zmdi zmdi-caret-down"></i>
					</div>
				</a>
				<ul class="main-menu">
					<li><a class="waves-effect" href="javascript:;"><i
							class="zmdi zmdi-account"></i> 个人资料</a></li>
					<li><a class="waves-effect" href="javascript:;"><i
							class="zmdi zmdi-face"></i> 隐私管理</a></li>
					<li><a class="waves-effect" href="javascript:;"><i
							class="zmdi zmdi-settings"></i> 系统设置</a></li>
					<li><a class="waves-effect" href="javascript:;"><i
							class="zmdi zmdi-run"></i> 退出登录</a></li>
				</ul>
			</div>
			<!-- /个人资料区 -->
			<!-- 菜单区 -->
			<ul class="main-menu">
				<li><a class="waves-effect"
					href="javascript:Tab.addTab('首页', 'home');"><i
						class="zmdi zmdi-home"></i>首页</a></li>
				<li class="sub-menu system_menus system_1"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-accounts-list"></i>用户管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('用户管理', '/ucenter_user');">用户管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_1"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-accounts"></i>回收员管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('回收员管理', '/ucenter_recycler');">回收员管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_1"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-account-circle"></i>管理员管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('管理员管理', '/ucenter_manager');">管理员管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_2"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi zmdi-pin"></i> 区域管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('区域管理', '/cms_district');">区域管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_2"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi zmdi-view-subtitles"></i> 公告管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('公告管理', '/cms_bulletin');">公告管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_2"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-delete"></i> 废品管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('废品管理', '/cms_waste');">废品管理</a></li>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('废品价格管理', '/cms_waste_price');">废品价格管理</a></li>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('废品属性管理', '/cms_waste_attribute');">废品属性管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_2"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-collection-text"></i>类目管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('类目管理', '/cms_category');">类目管理</a></li>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('类目内容管理', '/cms_content');">类目内容管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_2"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-mall"></i>积分商品管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('积分商品列表', '/cms_item_desc_list');">积分商品列表</a></li>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('添加积分商品', '/cms_add_item_desc');">添加积分商品</a></li>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('修改商品列表', '/cms_edit_item_desc');">修改商品列表</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_3"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-balance-wallet"></i>用户余额管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('用户余额管理', '/pay_user_balance');">用户余额管理</a></li>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('余额交易明细管理', '/pay_user_transaction_details');">余额交易明细管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_3"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-card"></i>用户积分管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('用户积分管理', '/pay_user_points');">用户积分管理</a></li>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('积分交易明细管理', '/pay_user_points_details');">积分交易明细管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_4"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-shopping-cart"></i>废品订单管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('废品订单管理', '/order_waste_order');">废品订单管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_4"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-shopping-cart"></i>积分订单管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('积分订单管理', '/order_point_order');">积分订单管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_5"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-comment-list"></i>通知管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('通知管理', '/message_notification');">通知管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_6"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-accounts-list"></i>系统组织管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('系统管理', '/cms_waste');">系统管理</a></li>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('组织管理', '/cms_waste');">组织管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_6"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-accounts"></i>角色管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('角色管理', '/cms_waste');">角色管理</a></li>
					</ul></li>
				<li class="sub-menu system_menus system_6"><a
					class="waves-effect" href="javascript:;"><i
						class="zmdi zmdi-lock-outline"></i>权限资源管理</a>
					<ul>
						<li><a class="waves-effect"
							href="javascript:Tab.addTab('权限管理', '/cms_waste');">权限管理</a></li>
					</ul></li>
				<li>
					<div class="upms-version">&copy; huishoucat v1.0.0</div>
				</li>
			</ul>
			<!-- /菜单区 -->
		</aside>
		<!-- /左侧导航区 -->
		<section id="content">
			<div class="content_tab">
				<div class="tab_left">
					<a class="waves-effect waves-light" href="javascript:;"><i
						class="zmdi zmdi-chevron-left"></i></a>
				</div>
				<div class="tab_right">
					<a class="waves-effect waves-light" href="javascript:;"><i
						class="zmdi zmdi-chevron-right"></i></a>
				</div>
				<ul id="tabs" class="tabs">
					<li id="tab_home" data-index="home" data-closeable="false"
						class="cur"><a class="waves-effect waves-light">首页</a></li>
				</ul>
			</div>
			<div class="content_main">
				<div id="iframe_home" class="iframe cur">
					<p>
					<h4>蓝鸥e家后台管理系统</h4>
					</p>
					<b>系统简介</b>：本系统是蓝鸥e家后台管理系统，包括用户管理系统、内容管理系统、支付管理系统、订单管理系统、消息管理系统、权限管理系统、存储管理系统。
					</p>
					<br />
					<p>
					<h4>系统功能概述：</h4>
					</p>
					<p>
						<b>用户管理系统</b>：用户、回收员、管理员的增加、删除、修改、查询功能。
					</p>
					<p>
						<b>内容管理系统</b>：区域、废品及其价格、类目及其内容的增加、删除、修改、查询功能。
					</p>
					<p>
						<b>支付管理系统</b>：用户余额及其交易记录、用户积分及其交易记录的删除、修改、查询功能。
					</p>
					<p>
						<b>订单管理系统</b>：废品订单、积分商品的删除、修改、查询功能。
					</p>
					<p>
						<b>消息管理系统</b>：系统内用户通知的发送、删除、修改、查询功能。
					</p>
					<p>
						<b>权限管理系统</b>：提供给系统、组织、角色和用户的权限增加、删除、修改、查询功能。
					</p>
				</div>
			</div>
		</section>
	</section>
	<footer id="footer"></footer>

	<script src="/resources/plugins/jquery.1.12.4.min.js"></script>
	<script src="/resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
	<script src="/resources/plugins/waves-0.7.5/waves.min.js"></script>
	<script
		src="/resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="/resources/plugins/BootstrapMenu.min.js"></script>
	<script src="/resources/plugins/device.min.js"></script>
	<script src="/resources/plugins/fullPage/jquery.fullPage.min.js"></script>
	<script src="/resources/plugins/jquery.cookie.js"></script>

	<script src="/resources/js/admin.js"></script>
</body>
</html>