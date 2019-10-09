package com.huishoucat.upms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TOrganize;

/**
 * 组织部门Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月3日 下午5:54:27
 * @version V1.0
 */
public interface IOrganizeService {

	/**
	 * 获取组织部门列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TOrganize> getOrganizeList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新组织部门信息
	 * @param organize
	 * @return
	 */
	public HuishoucatResult updateOrganize(TOrganize organize);

	/**
	 * 批量删除组织部门信息
	 * @param organizeIds
	 * @return
	 */
	public HuishoucatResult deleteOrganizeByOrganizeId(Long[] organizeIds);

}
