package com.huishoucat.upms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TFunctionMapper;
import com.huishoucat.manager.pojo.TFunction;
import com.huishoucat.manager.pojo.TFunctionExample;
import com.huishoucat.manager.pojo.TFunctionExample.Criteria;
import com.huishoucat.upms.service.IFunctionService;

/**
 * 权限管理Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年3月4日 上午9:04:25
 * @version V1.0
 */
public class FunctionServiceImpl implements IFunctionService {

	@Autowired
	private TFunctionMapper functionMapper;

	@Override
	public PageBean<TFunction> getFunctionList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TFunctionExample functionExample = new TFunctionExample();
		functionExample.setOrderByClause(sort + " " + order);
		Criteria criteria = functionExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andNameLike("%" + search + "%");
		}
		List<TFunction> list = functionMapper.selectByExample(functionExample);
		PageInfo<TFunction> pageInfo = new PageInfo<TFunction>(list);
		PageBean<TFunction> pageBean = new PageBean<TFunction>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateFunction(TFunction function) {
		function.setUpdateTime(new Date());
		int i = functionMapper.updateByPrimaryKeySelective(function);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新权限信息失败！", null);
	}

	@Override
	public HuishoucatResult deleteFunctionByFunctionId(Long[] functionIds) {
		TFunction function = new TFunction();
		Date date = new Date();
		for (int i = 0; i < functionIds.length; i++) {
			function.setFunctionId(functionIds[i]);
			function.setIsDeleted(true);
			function.setUpdateTime(date);
			functionMapper.updateByPrimaryKeySelective(function);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult addFunction(TFunction function) {
		Date date = new Date();
		function.setUpdateTime(date);
		function.setCreateTime(date);
		int i = functionMapper.insertSelective(function);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, function);
		}
		return HuishoucatResult.HuishoucatResultError("添加权限失败！", null);
	}
}
