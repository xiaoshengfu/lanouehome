package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TUserPointsDetails;
import com.huishoucat.manager.pojo.TUserPointsDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserPointsDetailsMapper {
    int countByExample(TUserPointsDetailsExample example);

    int deleteByExample(TUserPointsDetailsExample example);

    int deleteByPrimaryKey(Long pointsDetailId);

    int insert(TUserPointsDetails record);

    int insertSelective(TUserPointsDetails record);

    List<TUserPointsDetails> selectByExample(TUserPointsDetailsExample example);

    TUserPointsDetails selectByPrimaryKey(Long pointsDetailId);

    int updateByExampleSelective(@Param("record") TUserPointsDetails record, @Param("example") TUserPointsDetailsExample example);

    int updateByExample(@Param("record") TUserPointsDetails record, @Param("example") TUserPointsDetailsExample example);

    int updateByPrimaryKeySelective(TUserPointsDetails record);

    int updateByPrimaryKey(TUserPointsDetails record);
}