package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TUserPoints;
import com.huishoucat.manager.pojo.TUserPointsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserPointsMapper {
    int countByExample(TUserPointsExample example);

    int deleteByExample(TUserPointsExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(TUserPoints record);

    int insertSelective(TUserPoints record);

    List<TUserPoints> selectByExample(TUserPointsExample example);

    TUserPoints selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") TUserPoints record, @Param("example") TUserPointsExample example);

    int updateByExample(@Param("record") TUserPoints record, @Param("example") TUserPointsExample example);

    int updateByPrimaryKeySelective(TUserPoints record);

    int updateByPrimaryKey(TUserPoints record);
}