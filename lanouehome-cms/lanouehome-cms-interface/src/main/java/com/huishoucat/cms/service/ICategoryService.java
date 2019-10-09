package com.huishoucat.cms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TCategory;

/**
 * 类目Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月24日 下午9:57:42
 * @version V1.0
 */
public interface ICategoryService {

	/**
	 * 获取类目列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TCategory> getCategoryList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新类目信息
	 * @param category
	 * @return
	 */
	public HuishoucatResult updateCategory(TCategory category);

	/**
	 * 批量删除类目信息
	 * @param categoryIds
	 * @return
	 */
	public HuishoucatResult deleteCategoryByCategoryId(Long[] categoryIds);

	/**
	 * 通过类目ID查找类目
	 * @param categoryId
	 * @return
	 */
	public HuishoucatResult findCategoryByCategoryId(Long categoryId);

	/**
	 * 添加类目
	 * @param category
	 * @return
	 */
	public HuishoucatResult addCategory(TCategory category);
}
