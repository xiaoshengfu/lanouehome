package com.huishoucat.cms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TWaste;

/**
 * 废品Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月18日 下午8:15:28
 * @version V1.0
 */
public interface IWasteService {
	
	/**
	 * 获取废品列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TWaste> getWasteList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新废品信息
	 * @param district
	 * @return
	 */
	public HuishoucatResult updateWaste(TWaste waste);

	/**
	 * 批量删除废品信息
	 * @param wasteIds
	 * @return
	 */
	public HuishoucatResult deleteWasteByWasteId(Long[] wasteIds);

	/**
	 * 添加废品
	 * @param waste
	 * @return
	 */
	public HuishoucatResult addWaste(TWaste waste);
	
	/**
	 * 通过废品ID查找废品信息
	 * @param wasteId
	 * @return
	 */
	public HuishoucatResult findWasteByWasteId(Long wasteId);
	
	/**
	 * 通过废品ID查找子废品列表(按照排序码升序排列)
	 * @param wasteId
	 * @return
	 */
	public HuishoucatResult findChildWaste(Long parentWasteId);
}
