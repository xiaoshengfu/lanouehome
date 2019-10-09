package com.huishoucat.upms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TFunction;

/**
 * 权限管理Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月3日 下午8:44:08
 * @version V1.0
 */
public interface IFunctionService {

	/**
	 * 获取权限列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TFunction> getFunctionList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新权限信息
	 * @param function
	 * @return
	 */
	public HuishoucatResult updateFunction(TFunction function);

	/**
	 * 批量删除权限信息
	 * @param functionIds
	 * @return
	 */
	public HuishoucatResult deleteFunctionByFunctionId(Long[] functionIds);
	
	/**
	 * 添加权限信息
	 * @param function
	 * @return
	 */
	public HuishoucatResult addFunction(TFunction function);
}
