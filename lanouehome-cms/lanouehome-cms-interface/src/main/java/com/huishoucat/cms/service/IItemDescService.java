package com.huishoucat.cms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TItemDesc;

/**
 * 积分商品Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月27日 下午2:04:58
 * @version V1.0
 */
public interface IItemDescService {

	/**
	 * 获取积分商品列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TItemDesc> getItemDescList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新积分商品信息
	 * @param itemDesc
	 * @return
	 */
	public HuishoucatResult updateItemDesc(TItemDesc itemDesc);

	/**
	 * 批量删除积分商品信息
	 * @param itemIds
	 * @return
	 */
	public HuishoucatResult deleteItemDescByItemId(Long[] itemIds);

	/**
	 * 通过积分商品ID查找积分商品
	 * @param itemId
	 * @return
	 */
	public HuishoucatResult findItemDescByItemId(Long itemId);

	/**
	 * 添加积分商品
	 * @param itemDesc
	 * @return
	 */
	public HuishoucatResult addItemDesc(TItemDesc itemDesc);
	
	/**
	 * 查找积分商品列表(按照兑换所需积分升序排列)
	 * @return
	 */
	public HuishoucatResult findItemDescList();
}
