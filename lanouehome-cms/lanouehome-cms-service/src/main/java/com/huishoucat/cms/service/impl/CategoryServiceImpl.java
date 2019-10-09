package com.huishoucat.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.cms.service.ICategoryService;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.manager.mapper.TCategoryMapper;
import com.huishoucat.manager.pojo.TCategory;
import com.huishoucat.manager.pojo.TCategoryExample;
import com.huishoucat.manager.pojo.TCategoryExample.Criteria;

/**
 * 类目Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月24日 下午9:57:42
 * @version V1.0
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private TCategoryMapper categoryMapper;

	@Override
	public PageBean<TCategory> getCategoryList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TCategoryExample categoryExample = new TCategoryExample();
		categoryExample.setOrderByClause(sort + " " + order);
		Criteria criteria = categoryExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andCategoryIdEqualTo(Long.parseLong(search));
		}
		List<TCategory> list = categoryMapper.selectByExample(categoryExample);
		PageInfo<TCategory> pageInfo = new PageInfo<TCategory>(list);
		PageBean<TCategory> pageBean = new PageBean<TCategory>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateCategory(TCategory category) {
		if (category.getParentId() != null) {
			TCategory parentCategory = categoryMapper.selectByPrimaryKey(category.getParentId());
			if (parentCategory != null) {
				if (!parentCategory.getIsParent()) {
					TCategory updateParentCategory = new TCategory(parentCategory.getCategoryId(), true, new Date());
					int i = categoryMapper.updateByPrimaryKeySelective(updateParentCategory);
					if (i != 1) {
						return HuishoucatResult.HuishoucatResultError("更新类目失败！", null);
					}
				}
				TCategory oldCategory = categoryMapper.selectByPrimaryKey(category.getCategoryId());
				if (oldCategory != null) {
					if (oldCategory.getParentId() != 0) {
						TCategoryExample categoryExample = new TCategoryExample();
						Criteria criteria = categoryExample.createCriteria();
						criteria.andParentIdEqualTo(oldCategory.getParentId());
						int i = categoryMapper.countByExample(categoryExample);
						if (i == 1) {
							List<TCategory> list = categoryMapper.selectByExample(categoryExample);
							list.get(0).setIsParent(false);
							categoryMapper.updateByPrimaryKeySelective(list.get(0));
						}
					}
					category.setUpdateTime(new Date());
					int i = categoryMapper.updateByPrimaryKeySelective(category);
					if (i == 1) {
						return HuishoucatResult.HuishoucatResultOK(null, null);
					}
				} else {
					return HuishoucatResult.HuishoucatResultError("无效类目ID！", null);
				}
			}
		}
		return HuishoucatResult.HuishoucatResultError("更新类目失败！", null);
	}

	@Override
	public HuishoucatResult deleteCategoryByCategoryId(Long[] categoryIds) {
		TCategoryExample categoryExample = new TCategoryExample();
		boolean notComplete = false;
		long num = 0;
		TCategory oldCategory = null;
		TCategory category = new TCategory();
		category.setIsDeleted(true);
		category.setUpdateTime(new Date());
		for (int i = 0; i < categoryIds.length; i++) {
			oldCategory = categoryMapper.selectByPrimaryKey(categoryIds[i]);
			if (oldCategory != null && oldCategory.getIsParent()) {
				notComplete = true;
				num++;
				continue;
			}
			categoryExample.createCriteria().andParentIdEqualTo(oldCategory.getParentId());
			int j = categoryMapper.countByExample(categoryExample);
			if (j == 1) {
				List<TCategory> list = categoryMapper.selectByExample(categoryExample);
				list.get(0).setIsParent(false);
				categoryMapper.updateByPrimaryKeySelective(list.get(0));
			}
			categoryExample.clear();
			oldCategory = null;
			category.setCategoryId(categoryIds[i]);
			categoryMapper.updateByPrimaryKeySelective(category);
		}
		if (notComplete) {
			return HuishoucatResult.HuishoucatResultWarning("有" + num + "条类目信息未删除原因：父类目不能被删除！", null);
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult findCategoryByCategoryId(Long categoryId) {
		TCategory category = categoryMapper.selectByPrimaryKey(categoryId);
		if (category != null && !category.getIsDeleted()) {
			return HuishoucatResult.HuishoucatResultOK(null, category);
		}
		return HuishoucatResult.HuishoucatResultError("无效类目ID！", null);
	}

	@Override
	public HuishoucatResult addCategory(TCategory category) {
		if (category.getParentId() != 0) {
			TCategory parentCategory = categoryMapper.selectByPrimaryKey(category.getParentId());
			if (parentCategory != null) {
				if (!parentCategory.getIsParent()) {
					TCategory updateParentCategory = new TCategory(parentCategory.getCategoryId(), true, new Date());
					int i = categoryMapper.updateByPrimaryKeySelective(updateParentCategory);
					if (i != 1) {
						return HuishoucatResult.HuishoucatResultError("添加类目失败！", null);
					}
				}
			}
		}
		Date date = new Date();
		category.setUpdateTime(date);
		category.setCreateTime(date);
		int i = categoryMapper.insertSelective(category);
		if (i == 1) {
			return HuishoucatResult.HuishoucatResultOK(null, category);
		}
		return HuishoucatResult.HuishoucatResultError("添加类目失败！", null);
	}
}
