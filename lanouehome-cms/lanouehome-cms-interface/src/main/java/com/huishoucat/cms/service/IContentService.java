package com.huishoucat.cms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TContent;

/**
 * 内容Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月25日 下午2:37:14
 * @version V1.0
 */
public interface IContentService {
	
	/**
	 * 获取内容列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TContent> getContentList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新内容信息
	 * @param content
	 * @return
	 */
	public HuishoucatResult updateContent(TContent content);

	/**
	 * 批量删除内容信息
	 * @param contentIds
	 * @return
	 */
	public HuishoucatResult deleteContentByContentId(Long[] contentIds);

	/**
	 * 添加内容
	 * @param content
	 * @return
	 */
	public HuishoucatResult addContent(TContent content);
	
	/**
	 * 根据类目ID查询内容列表
	 * @param categoryId
	 * @return
	 */
	public HuishoucatResult getContentListByCategoryId(Long categoryId);
}
