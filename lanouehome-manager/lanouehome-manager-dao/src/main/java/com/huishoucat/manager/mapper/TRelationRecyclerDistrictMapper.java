package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TRelationRecyclerDistrict;
import com.huishoucat.manager.pojo.TRelationRecyclerDistrictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRelationRecyclerDistrictMapper {
    int countByExample(TRelationRecyclerDistrictExample example);

    int deleteByExample(TRelationRecyclerDistrictExample example);

    int deleteByPrimaryKey(Long districtId);

    int insert(TRelationRecyclerDistrict record);

    int insertSelective(TRelationRecyclerDistrict record);

    List<TRelationRecyclerDistrict> selectByExample(TRelationRecyclerDistrictExample example);

    TRelationRecyclerDistrict selectByPrimaryKey(Long districtId);

    int updateByExampleSelective(@Param("record") TRelationRecyclerDistrict record, @Param("example") TRelationRecyclerDistrictExample example);

    int updateByExample(@Param("record") TRelationRecyclerDistrict record, @Param("example") TRelationRecyclerDistrictExample example);

    int updateByPrimaryKeySelective(TRelationRecyclerDistrict record);

    int updateByPrimaryKey(TRelationRecyclerDistrict record);
}