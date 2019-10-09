package com.huishoucat.order.service;

import java.text.ParseException;
import java.util.List;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TWasteOrder;

/**
 * 废品订单Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月19日 下午9:31:39
 * @version V1.0
 */
public interface IWasteOrderService {

	/**
	 * 获取废品订单列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TWasteOrder> getWasteOrderList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新废品订单信息
	 * @param wasteOrder
	 * @return
	 */
	public HuishoucatResult updateWasteOrder(TWasteOrder wasteOrder);

	/**
	 * 批量删除废品订单信息
	 * @param wasteOrderIds
	 * @return
	 */
	public HuishoucatResult deleteWasteOrderByWasteOrderId(Long[] wasteOrderIds);

	/**
	 * 查找废品订单信息
	 * @param wasteOrderId
	 * @return
	 */
	public HuishoucatResult findWasteOrderByWasteOrderId(Long wasteOrderId);

	/**
	 * 查找回收员废品订单列表(按照订单确认时间降序排列)
	 * @param recyclerId
	 * @param state
	 * @return
	 */
	public HuishoucatResult findRecyclerWasteOrderList(Long recyclerId, Integer state);

	/**
	 * 查找回收员月完成订单数、当前剩余订单数、日完成订单数。
	 * @param recyclerId
	 * @return
	 * @throws ParseException
	 */
	public HuishoucatResult findRecyclerAchievementByRecyclerId(Long recyclerId) throws ParseException;

	/**
	 * 回收员编辑废品订单最终废品数量和单价
	 * @param wasteOrder
	 * @return
	 */
	public HuishoucatResult recyclerEditWasteOrderPrice(TWasteOrder wasteOrder);

	/**
	 * 回收员取消废品订单
	 * @param wasteOrderId
	 * @param recyclerId
	 * @param invalidReason 
	 * @return
	 */
	public HuishoucatResult recyclerCancelWasteOrder(Long wasteOrderId, Long recyclerId, String invalidReason);

	/**
	 * 用户查找根据订单状态查找废品订单列表(按照创建时间降序排序)
	 * @param userId
	 * @param states
	 * @return
	 */
	public HuishoucatResult findUserWasteOrderList(Long userId, List<Integer> states);

	/**
	 * 用户取消废品订单
	 * @param wasteOrderId
	 * @param userId
	 * @param invalidReason 
	 * @return
	 */
	public HuishoucatResult userCancelWasteOrder(Long wasteOrderId, Long userId, String invalidReason);

	/**
	 * 用户提交废品订单
	 * @param wasteOrder
	 * @return
	 */
	public HuishoucatResult userSubmitWasteOrder(TWasteOrder wasteOrder);

	/**
	 * 用户确认废品订单
	 * @param wasteOrder
	 * @return
	 */
	public HuishoucatResult userConfirmWasteOrder(TWasteOrder wasteOrder);

	/**
	 * 通过用户ID查找用户未完成订单的数量
	 * @param userId
	 * @return
	 */
	public HuishoucatResult countNewWasteOrderByUserId(Long userId);
}
