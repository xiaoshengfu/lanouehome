package com.huishoucat.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishoucat.cms.service.IBulletinService;
import com.huishoucat.common.jedis.JedisClient;
import com.huishoucat.common.pojo.HuishoucatResult;
import com.huishoucat.common.pojo.PageBean;
import com.huishoucat.common.utils.JsonUtils;
import com.huishoucat.manager.mapper.TBulletinMapper;
import com.huishoucat.manager.pojo.TBulletin;
import com.huishoucat.manager.pojo.TBulletinExample;
import com.huishoucat.manager.pojo.TBulletinExample.Criteria;

/**
 * 系统公告Service
 * @author xiaoshengfu(2439323118@qq.com)
 * @description TODO
 * @date 2018年2月28日 下午10:53:32
 * @version V1.0
 */
@Service
public class BulletinServiceImpl implements IBulletinService {

	@Value("${NEWEST_BULLETIN}")
	private String NEWEST_BULLETIN;

	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TBulletinMapper bulletinMapper;

	@Override
	public PageBean<TBulletin> getBulletinList(int pageNum, int limit, String sort, String order, String search) {
		PageHelper.startPage(pageNum, limit);
		TBulletinExample bulletinExample = new TBulletinExample();
		bulletinExample.setOrderByClause(sort + " " + order);
		Criteria criteria = bulletinExample.createCriteria();
		criteria.andIsDeletedEqualTo(false);
		if (StringUtils.isNotBlank(search)) {
			criteria.andBulletinTitleLike("%" + search + "%");
		}
		List<TBulletin> list = bulletinMapper.selectByExample(bulletinExample);
		PageInfo<TBulletin> pageInfo = new PageInfo<TBulletin>(list);
		PageBean<TBulletin> pageBean = new PageBean<TBulletin>(limit, pageInfo.getTotal(), pageNum, list);
		return pageBean;
	}

	@Override
	public HuishoucatResult updateBulletin(TBulletin bulletin) {
		bulletin.setUpdateTime(new Date());
		int i = bulletinMapper.updateByPrimaryKeySelective(bulletin);
		if (i == 1) {
			jedisClient.del(NEWEST_BULLETIN);
			return HuishoucatResult.HuishoucatResultOK(null, null);
		}
		return HuishoucatResult.HuishoucatResultError("更新系统公告失败！", null);
	}

	@Override
	public HuishoucatResult deleteBulletinByBulletinId(Long[] bulletinIds) {
		TBulletin bulletin = new TBulletin();
		bulletin.setUpdateTime(new Date());
		bulletin.setIsDeleted(true);
		for (int i = 0; i < bulletinIds.length; i++) {
			bulletin.setBulletinId(bulletinIds[i]);
			bulletinMapper.updateByPrimaryKeySelective(bulletin);
		}
		jedisClient.del(NEWEST_BULLETIN);
		return HuishoucatResult.HuishoucatResultOK(null, null);
	}

	@Override
	public HuishoucatResult findNewestBulletin() {
		// 查询缓存
		try {
			// 如果缓存中有直接响应结果
			String json = jedisClient.get(NEWEST_BULLETIN);
			if (StringUtils.isNotBlank(json)) {
				TBulletin bulletin = JsonUtils.jsonToPojo(json, TBulletin.class);
				return HuishoucatResult.HuishoucatResultOK(null, bulletin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageHelper.startPage(1, 1);
		TBulletinExample bulletinExample = new TBulletinExample();
		bulletinExample.setOrderByClause("create_time desc");
		bulletinExample.createCriteria().andIsDeletedEqualTo(false);
		List<TBulletin> list = bulletinMapper.selectByExample(bulletinExample);
		new PageInfo<TBulletin>(list);
		if (list.size() == 0) {
			return HuishoucatResult.HuishoucatResultError(null, null);
		}
		try {
			jedisClient.set(NEWEST_BULLETIN, JsonUtils.objectToJson(list.get(0)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HuishoucatResult.HuishoucatResultOK(null, list.get(0));
	}

	@Override
	public HuishoucatResult addBulletin(TBulletin bulletin) {
		Date date = new Date();
		bulletin.setCreateTime(date);
		bulletin.setUpdateTime(date);
		int i = bulletinMapper.insertSelective(bulletin);
		if (i == 1) {
			jedisClient.del(NEWEST_BULLETIN);
			return HuishoucatResult.HuishoucatResultOK(null, bulletin);
		}
		return HuishoucatResult.HuishoucatResultError("发布公告失败！", null);
	}
}
