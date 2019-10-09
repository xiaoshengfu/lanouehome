package com.huishoucat.cms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TWastePrice;

/**
 * 废品价格Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月18日 下午8:15:01
 * @version V1.0
 */
public interface IWastePriceService {
	
	/**
	 * 获取废品列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TWastePrice> getWastePriceList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新废品价格信息
	 * @param wastePrice
	 * @return
	 */
	public HuishoucatResult updateWastePrice(TWastePrice wastePrice);

	/**
	 * 批量删除废品价格信息
	 * @param wastePriceIds
	 * @return
	 */
	public HuishoucatResult deleteWastePriceByWastePriceId(Long[] wastePriceIds);

	/**
	 * 添加废品价格
	 * @param wastePrice
	 * @return
	 */
	public HuishoucatResult addWastePrice(TWastePrice wastePrice);
	
	/**
	 * 通过废品ID查找最新的废品价格
	 * @param wasteId
	 * @return
	 */
	public HuishoucatResult getNewestWastePriceByWasteId(Long wasteId);
}
