routes = [
	//首页
	{
		path : '/',
		url : '/wechat/index',
	},
	//回收筐
	{
		path : '/basket/',
		url : '/wechat/basket',
	},
	//积分商城
	{
		path : '/point/mall/',
		url : '/wechat/point/mall',
	},
	//用户登录页面
	{
		path : '/user/login/page/',
		url : '/wechat/user/login/page',
	},
	//用户注册页面
	{
		path : '/user/register/page/',
		url : '/wechat/user/register/page',
	},
	//个人中心
	{
		path : '/person/',
		url : '/wechat/person/center',
	},
	//用户详细信息
	{
		path : '/user/user_information/',
		url : '/wechat/user/user_information',
	},
	//用户余额
	{
		path : '/user/balance/',
		url : '/wechat/user/balance',
	},
	//用户余额明细
	{
		path : '/user/balance/details/list/',
		url : '/wechat/user/balance/details/list',
	},
	//用户积分余额
	{
		path : '/user/point/',
		url : '/wechat/user/point',
	},
	//用户积分余额明细
	{
		path : '/user/point/details/list/',
		url : '/wechat/user/point/details/list',
	},
	//用户系统通知列表
	{
		path : '/user/notification/list/',
		url : '/wechat/user/notification/list',
	},
	//用户系统通知详情
	{
		path : '/user/notification/details/:notificationId/',
		url : '/wechat/user/notification/details/{{notificationId}}',
	},
	//废品子级列表
	{
		path : '/waste/find_child/list/:wasteId/',
		url : '/wechat/waste/find_child/list/{{wasteId}}',
	},
	//废品详情页
	{
		path : '/waste/details/:wasteId',
		url : '/wechat/waste/details/{{wasteId}}',
	},
	//订单管理
	{
		path : '/user/waste_order/list/',
		url : '/wechat/user/waste_order/list',
	},
	//确认废品订单页面
	{
		path : '/user/waste_order/confirm/page/',
		url : '/wechat/user/waste_order/confirm/page',
	},
	//用户地址列表
	{
		path : '/user/user_address/list/',
		url : '/wechat/user/user_address/list',
	},
	//新增用户地址
	{
		path : '/user/user_address/add/page/',
		url : '/wechat/user/user_address/add/page/',
	},
	//修改用户地址
	{
		path : '/user/user_address/edit/page/',
		url : '/wechat/user/user_address/edit/page',
	},
	//用户地址详情
	{
		path : '/user/user_address/details/:userAddressId/',
		url : '/wechat/user/user_address/details/{{userAddressId}}',
	},
	//余额明细
	{
		path : '/user/balance/details/',
		url : '/wechat/user/balance/details',
	},
	//余额提现页面
	{
		path : '/user/balance/withdrawals/page/',
		url : '/wechat/user/balance/withdrawals/page',
	},
	//余额提现
	{
		path : '/user/balance/withdrawals/',
		url : '/wechat/user/balance/withdrawals',
	},
	//绿色积分
	{
		path : '/user/point/',
		url : '/wechat/user/point/show',
	},
	//积分明细
	{
		path : '/user/point/details/',
		url : '/wechat/user/point/details',
	},
	//用户地址列表
	{
		path : '/user/user_address/list',
		url : '/wechat/user/user_address/list',
	},
	//设置
	{
		path : '/user/settings/',
		url : '/wechat/user/settings',
	},
	//账户与安全
	{
		path : '/user/settings/account_and_security/',
		url : '/wechat/user/settings/account_and_security',
	},
	//支付密码
	{
		path : '/user/settings/payment_password/',
		url : '/wechat/user/settings/payment_password',
	},
	//支付密码
	{
		path : '/user/settings/notification_prompt/',
		url : '/wechat/user/settings/notification_prompt',
	},
	//关于回收猫
	{
		path : '/user/settings/about_huishoucat/',
		url : '/wechat/user/settings/about_huishoucat',
	},
	//提交废品订单
	{
		path : '/user/waste_order/submit/',
		url : '/wechat/user/waste_order/submit',
	},
	//确认废品订单
	{
		path : '/user/waste_order/confirm/',
		url : '/wechat/user/waste_order/confirm',
	},
	//积分商品详情
	{
		path : '/point/mall/item/:itemDescId/',
		url : '/wechat/point/mall/{{itemDescId}}',
	},
	//404页面
	{
		path : '(.*)',
		url : '/wechat/404/',
	},
];