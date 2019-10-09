package com.huishoucat.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.cms.service.IContentService;
import com.huishoucat.common.jedis.JedisClient;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TContentMapper;
import com.huishoucat.manager.pojo.TContent;
import com.huishoucat.manager.pojo.TContentExample;
import com.huishoucat.manager.pojo.TContentExample.Criteria;

/**
 * 类目内容Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月26日 下午3:08:44
 * @version V1.0
 */
@Service
public class ContentServiceImpl implements IContentService {

	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@Autowired
	private TContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;

	@Override
	public PageBean<TContent> getContentList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TContentExample contentExample = new TContentExample();
		contentExample.setOrderByClause(sort + " " + order);
		Criteria criteria = contentExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andCategoryIdEqualTo(Long.parseLong(search));
		}
		List<TContent> list = contentMapper.selectByExample(contentExample);
		PageInfo<TContent> pageInfo = new PageInfo<TContent>(list);
		PageBean<TContent> pageBean = new PageBean<TContent>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateContent(TContent content) {
		content.setUpdateTime(new Date());
		int i = contentMapper.updateByPrimaryKeySelective(content);
		if (i == 1) {
			TContent newContent = contentMapper.selectByPrimaryKey(content.getContentId());
			if (newContent != null) {
				jedisClient.hdel(CONTENT_LIST, newContent.getCategoryId().toString());
			}
			return HuishoucatResult.HuishoucatResultOK(null, content);
		}
		return HuishoucatResult.HuishoucatResultError("更新内容失败！", null);
	}

	@Override
	public HuishoucatResult deleteContentByContentId(Long[] contentIds) {
		TContent content = new TContent();
		content.setIsDeleted(true);
		content.setUpdateTime(new Date());
		for (int i = 0; i < contentIds.length; i++) {
			content.setContentId(contentIds[i]);
			contentMapper.updateByPrimaryKeySelective(content);
			jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		}
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult addContent(TContent content) {
		Date date = new Date();
		content.setCreateTime(date);
		content.setUpdateTime(date);
		int i = contentMapper.insertSelective(content);
		if (i == 1) {
			jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
			return HuishoucatResult.HuishoucatResultOK(null, content);
		}
		return HuishoucatResult.HuishoucatResultError("添加内容失败！", null);
	}

	@Override
	public HuishoucatResult getContentListByCategoryId(Long categoryId) {
		// 查询缓存
		try {
			// 如果缓存中有直接响应结果
			String json = jedisClient.hget(CONTENT_LIST, categoryId + "");
			if (StringUtils.isNotBlank(json)) {
				List<TContent> list = JsonUtils.jsonToList(json, TContent.class);
				return HuishoucatResult.HuishoucatResultOK(null, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果没有查询数据库
		TContentExample contentExample = new TContentExample();
		Criteria criteria = contentExample.createCriteria();
		// 设置查询条件
		criteria.andCategoryIdEqualTo(categoryId);
		criteria.andIsDeletedEqualTo(false);
		// 执行查询
		List<TContent> list2 = contentMapper.selectByExampleWithBLOBs(contentExample);
		TContent content = null;
		for (int i = 0; i < list2.size(); i++) {
			content = list2.get(i);
			if (StringUtils.isNoneBlank(content.getPic())) {
				content.setPic(IMAGE_SERVER_URL + content.getPic());
			}
			if (StringUtils.isNoneBlank(content.getPic2())) {
				content.setPic2(IMAGE_SERVER_URL + content.getPic2());
			}
		}
		// 把结果添加到缓存
		try {
			jedisClient.hset(CONTENT_LIST, categoryId + "", JsonUtils.objectToJson(list2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HuishoucatResult.HuishoucatResultOK(null, list2);
	}
}
