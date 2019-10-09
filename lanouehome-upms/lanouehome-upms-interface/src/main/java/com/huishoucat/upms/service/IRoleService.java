package com.huishoucat.upms.service;

import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.pojo.TRole;

/**
 * 角色管理Service接口
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月3日 下午7:34:08
 * @version V1.0
 */
public interface IRoleService {

	/**
	 * 获取角色列表
	 * @param pageNum 当前页码
	 * @param limit 每页显示的记录数
	 * @param sort 排序字段
	 * @param order 排序方式 desc asc
	 * @param search 查找内容
	 * @return
	 */
	public PageBean<TRole> getRoleList(int pageNum, int limit, String sort, String order, String search);

	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	public HuishoucatResult updateRole(TRole role);

	/**
	 * 批量删除角色
	 * @param roleIds
	 * @return
	 */
	public HuishoucatResult deleteRoleByRoleId(Long[] roleIds);
}
