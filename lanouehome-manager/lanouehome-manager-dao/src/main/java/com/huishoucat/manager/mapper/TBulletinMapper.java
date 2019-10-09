package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TBulletin;
import com.huishoucat.manager.pojo.TBulletinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBulletinMapper {
    int countByExample(TBulletinExample example);

    int deleteByExample(TBulletinExample example);

    int deleteByPrimaryKey(Long bulletinId);

    int insert(TBulletin record);

    int insertSelective(TBulletin record);

    List<TBulletin> selectByExampleWithBLOBs(TBulletinExample example);

    List<TBulletin> selectByExample(TBulletinExample example);

    TBulletin selectByPrimaryKey(Long bulletinId);

    int updateByExampleSelective(@Param("record") TBulletin record, @Param("example") TBulletinExample example);

    int updateByExampleWithBLOBs(@Param("record") TBulletin record, @Param("example") TBulletinExample example);

    int updateByExample(@Param("record") TBulletin record, @Param("example") TBulletinExample example);

    int updateByPrimaryKeySelective(TBulletin record);

    int updateByPrimaryKeyWithBLOBs(TBulletin record);

    int updateByPrimaryKey(TBulletin record);
}