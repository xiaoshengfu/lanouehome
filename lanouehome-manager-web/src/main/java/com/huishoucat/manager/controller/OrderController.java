package com.huishoucat.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huishoucat.cms.service.IWasteService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.MyStringUtils;
import com.huishoucat.manager.pojo.TPointOrder;
import com.huishoucat.manager.pojo.TWaste;
import com.huishoucat.manager.pojo.TWasteOrder;
import com.huishoucat.manager.pojo.TWasteOrderItem;
import com.huishoucat.order.service.IPointOrderService;
import com.huishoucat.order.service.IWasteOrderItemService;
import com.huishoucat.order.service.IWasteOrderService;
import com.huishoucat.ucenter.service.IRecyclerService;

/**
 * 订单管理Controller
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月19日 下午10:20:31
 * @version V1.0
 */
@Controller
public class OrderController {

	@Autowired
	private IWasteService wasteService;
	@Autowired
	private IWasteOrderService wasteOrderService;
	@Autowired
	private IWasteOrderItemService wasteOrderItemService;
	@Autowired
	private IRecyclerService recyclerService;
	@Autowired
	private IPointOrderService pointOrderService;

	/**
	 * 查询废品订单列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/order/waste_order/list")
	@ResponseBody
	public PageBean<TWasteOrder> getWasteOrderList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return wasteOrderService.getWasteOrderList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort),
				order, search);
	}

	/**
	 * 编辑废品订单
	 * @param wasteOrder 更新数据
	 * @return
	 */
	@RequestMapping("/order/waste_order/edit")
	@ResponseBody
	public HuishoucatResult editWasteOrder(@RequestBody TWasteOrder wasteOrder) {
		if (wasteOrder != null) {
			if (wasteOrder.getRecyclerId() != null) {
				HuishoucatResult result = recyclerService.findRecyclerByRecyclerId(wasteOrder.getRecyclerId());
				if (result.getStateCode() != 200) {
					return HuishoucatResult.HuishoucatResultError("无效回收员ID！", null);
				}
			}
			if (wasteOrder.getWasteOrderId() != null) {
				return wasteOrderService.updateWasteOrder(wasteOrder);
			}
		}
		return HuishoucatResult.HuishoucatResultError("用户ID不能为空！", null);
	}

	/**
	 * 删除废品订单
	 * @param wasteOrderIds
	 * @return
	 */
	@RequestMapping("/order/waste_order/delete")
	@ResponseBody
	public HuishoucatResult deleteWasteOrder(@RequestBody Long[] wasteOrderIds) {
		if (wasteOrderIds != null) {
			return wasteOrderService.deleteWasteOrderByWasteOrderId(wasteOrderIds);
		}
		return HuishoucatResult.HuishoucatResultError("废品订单ID不能为空！", null);
	}

	/**
	 * 查询废品订单内容
	 * @param wasteOrderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/order/order_item/find/{wasteOrderId}")
	@ResponseBody
	public HuishoucatResult findWasteOrderItem(@PathVariable Long wasteOrderId) {
		if (wasteOrderId != null) {
			HuishoucatResult result = wasteOrderItemService.findWasteOrderItemListByWasteOrderId(wasteOrderId);
			if (result.getStateCode() == HuishoucatResult.SUCCESS) {
				List<TWasteOrderItem> itemList = (List<TWasteOrderItem>) (result.getData());
				TWaste waste;
				for (int i = 0; i < itemList.size(); i++) {
					result = wasteService.findWasteByWasteId(itemList.get(i).getWasteId());
					if (result.getStateCode() == HuishoucatResult.SUCCESS) {
						waste = (TWaste) (result.getData());
						itemList.get(i).setWaste(waste);
					}
				}
				return HuishoucatResult.HuishoucatResultOK(null, itemList);
			}
		}
		return HuishoucatResult.HuishoucatResultError("废品订单ID不能为空！", null);
	}

	/**
	 * 查询积分商品订单列表
	 * @param offset 起始行
	 * @param limit 页面大小
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @return
	 */
	@RequestMapping("/order/point_order/list")
	@ResponseBody
	public PageBean<TPointOrder> getPointOrderList(@RequestParam(name = "offset", defaultValue = "0") int offset,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sort", defaultValue = "createTime") String sort,
			@RequestParam(name = "order", defaultValue = "desc") String order, String search) {
		return pointOrderService.getPointOrderList(offset / limit + 1, limit, MyStringUtils.underscoreminName(sort),
				order, search);
	}

	/**
	 * 编辑积分订单
	 * @param pointOrder 更新数据
	 * @return
	 */
	@RequestMapping("/order/point_order/edit")
	@ResponseBody
	public HuishoucatResult editWasteOrder(@RequestBody TPointOrder pointOrder) {
		if (pointOrder != null) {
			if (pointOrder.getPointOrderId() != null) {
				pointOrder.setUserId(null);
				pointOrder.setItemId(null);
				pointOrder.setPrice(null);
				pointOrder.setIsDeleted(null);
				return pointOrderService.updatePointOrder(pointOrder);
			}
		}
		return HuishoucatResult.HuishoucatResultError("积分订单ID不能为空！", null);
	}

	/**
	 * 删除积分订单
	 * @param pointOrderIds
	 * @return
	 */
	@RequestMapping("/order/point_order/delete")
	@ResponseBody
	public HuishoucatResult deletePointOrder(@RequestBody Long[] pointOrderIds) {
		if (pointOrderIds != null) {
			return pointOrderService.deletePointOrderByPointOrderId(pointOrderIds);
		}
		return HuishoucatResult.HuishoucatResultError("积分订单ID不能为空！", null);
	}
}
