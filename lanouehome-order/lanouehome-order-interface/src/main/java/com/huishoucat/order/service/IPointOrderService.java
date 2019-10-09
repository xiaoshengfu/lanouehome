package com.huishoucat.order.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TPointOrder;

/**
 * 积分订单Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月27日 下午5:51:43
 * @version V1.0
 */
public interface IPointOrderService {

	/**
	 * 获取积分订单列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TPointOrder> getPointOrderList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新积分订单信息
	 * @param pointOrder
	 * @return
	 */
	public HuishoucatResult updatePointOrder(TPointOrder pointOrder);

	/**
	 * 批量删除积分订单信息
	 * @param pointOrderIds
	 * @return
	 */
	public HuishoucatResult deletePointOrderByPointOrderId(Long[] pointOrderIds);
}
