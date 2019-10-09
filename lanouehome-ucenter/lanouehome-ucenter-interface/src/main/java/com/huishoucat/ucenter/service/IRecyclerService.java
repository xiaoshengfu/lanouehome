package com.huishoucat.ucenter.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TRecycler;

/**
 * 回收员Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月13日 上午10:35:32
 * @version V1.0
 */
public interface IRecyclerService {
	
	/**
	 * 获取回收员列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TRecycler> getRecyclerList(int pageNum, int limit, String sort, String order, String search);
	
	/**
	 * 更新回收员信息
	 * @param recycler
	 * @return
	 */
	public HuishoucatResult updateRecycler(TRecycler recycler);
	
	/**
	 * 批量删除回收员信息
	 * @param recyclerIds
	 * @return
	 */
	public HuishoucatResult deleteRecyclerByRecyclerId(Long[] recyclerIds);
	
	/**
	 * 注册添加回收员
	 * @param recycler
	 * @param districtIds
	 * @return
	 */
	public HuishoucatResult addRecycler(TRecycler recycler, Long[] districtIds);
	
	/**
	 * 查询回收员负责区域ID列表
	 * @param recyclerId
	 * @return
	 */
	public HuishoucatResult findRecyclerDistrictIds(Long recyclerId);
	
	/**
	 * 修改回收员负责区域ID列表
	 * @param districtIds
	 * @return
	 */
	public HuishoucatResult updateRecyclerDistrictIds(Long recyclerId, Long[] districtIds);
	
	/**
	 * 通过回收员ID查找回收员
	 * @param recyclerId
	 * @return
	 */
	public HuishoucatResult findRecyclerByRecyclerId(Long recyclerId);
	
	/**
	 * 通过区域ID查找负责该区域的回收员
	 * @param districtId
	 * @return
	 */
	public HuishoucatResult findRecyclerByDistrictId(Long districtId);
}