package com.huishoucat.cms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TBulletin;

/**
 * 系统公告Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月28日 下午10:41:43
 * @version V1.0
 */
public interface IBulletinService {

	/**
	 * 获取公告列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TBulletin> getBulletinList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新公告信息
	 * @param bulletin
	 * @return
	 */
	public HuishoucatResult updateBulletin(TBulletin bulletin);

	/**
	 * 发布公告
	 * @param bulletin
	 * @return
	 */
	public HuishoucatResult addBulletin(TBulletin bulletin);

	/**
	 * 批量删除公告
	 * @param bulletinIds
	 * @return
	 */
	public HuishoucatResult deleteBulletinByBulletinId(Long[] bulletinIds);

	/**
	 * 查找最新公告
	 * @return
	 */
	public HuishoucatResult findNewestBulletin();
}
