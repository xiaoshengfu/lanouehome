package com.huishoucat.ucenter.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TManager;

/**
 * 管理员Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月16日 下午10:23:41
 * @version V1.0
 */
public interface IManagerService {
	
	/**
	 * 管理员登录
	 * @param jobNumber
	 * @param password
	 * @return
	 */
	public HuishoucatResult managerLogin(Long jobNumber, String password);
	
	/**
	 * 获取回收员列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TManager> getManagerList(int pageNum, int limit, String sort, String order, String search);
	
	/**
	 * 更新管理员信息
	 * @param manager
	 * @return
	 */
	public HuishoucatResult updateManager(TManager manager);
	
	/**
	 * 批量删除管理员信息
	 * @param recyclerIds
	 * @return
	 */
	public HuishoucatResult deleteManagerByManagerId(Long[] managerIds);
	
	/**
	 * 注册添加管理员
	 * @param manager
	 * @return
	 */
	public HuishoucatResult addManager(TManager manager);
}
