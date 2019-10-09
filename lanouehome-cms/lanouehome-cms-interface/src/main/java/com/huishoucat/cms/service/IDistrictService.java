package com.huishoucat.cms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TDistrict;

/**
 * 区域Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月12日 下午1:24:07
 * @version V1.0
 */
public interface IDistrictService {

	/**
	 * 获取区域列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TDistrict> getDistrictList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新区域信息
	 * @param district
	 * @return
	 */
	public HuishoucatResult updateDistrict(TDistrict district);

	/**
	 * 批量删除区域信息
	 * @param districtIds
	 * @return
	 */
	public HuishoucatResult deleteDistrictByDistrictId(Long[] districtIds);

	/**
	 * 通过区域ID查找区域
	 * @param districtId
	 * @return
	 */
	public HuishoucatResult findDistrictByDistrictId(Long districtId);

	/**
	 * 添加区域
	 * @param district
	 * @return
	 */
	public HuishoucatResult addDistrict(TDistrict district);
	
	/**
	 * 获取子区域列表
	 * @param districtId
	 * @return
	 */
	public HuishoucatResult getChildrenDistrictList(Long districtId);
}
