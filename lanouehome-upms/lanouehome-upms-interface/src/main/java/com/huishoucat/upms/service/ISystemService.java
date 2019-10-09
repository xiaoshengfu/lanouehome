package com.huishoucat.upms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TSystem;

/**
 * 系统管理Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月2日 下午12:06:23
 * @version V1.0
 */
public interface ISystemService {
	
	/**
	 * 获取系统列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TSystem> getSystemList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新系统信息
	 * @param system
	 * @return
	 */
	public HuishoucatResult updateSystem(TSystem system);

	/**
	 * 批量删除系统信息
	 * @param systemIds
	 * @return
	 */
	public HuishoucatResult deleteSystemBySystemId(Long[] systemIds);
}
