package com.huishoucat.cms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TWasteAttribute;

/**
 * 废品属性Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月11日 下午10:15:21
 * @version V1.0
 */
public interface IWasteAttributeService {

	/**
	 * 获取废品属性列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TWasteAttribute> getWasteAttributeList(int pageNum, int limit, String sort, String order,
			String search);

	/**
	 * 更新废品属性信息
	 * @param wasteAttribute
	 * @return
	 */
	public HuishoucatResult updateWasteAttribute(TWasteAttribute wasteAttribute);

	/**
	 * 批量删除废品属性信息
	 * @param wasteAttributeIds
	 * @return
	 */
	public HuishoucatResult deleteWasteAttributeByWasteAttributeId(Long[] wasteAttributeIds);

	/**
	 * 添加废品属性
	 * @param wasteAttribute
	 * @return
	 */
	public HuishoucatResult addWasteAttribute(TWasteAttribute wasteAttribute);

	/**
	 * 通过废品ID查找废品属性信息
	 * @param wasteId
	 * @return
	 */
	public HuishoucatResult findWasteAttributeByWasteId(Long wasteId);

}
