package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TDistrict;
import com.huishoucat.manager.pojo.TDistrictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TDistrictMapper {
    int countByExample(TDistrictExample example);

    int deleteByExample(TDistrictExample example);

    int deleteByPrimaryKey(Long districtId);

    int insert(TDistrict record);

    int insertSelective(TDistrict record);

    List<TDistrict> selectByExample(TDistrictExample example);

    TDistrict selectByPrimaryKey(Long districtId);

    int updateByExampleSelective(@Param("record") TDistrict record, @Param("example") TDistrictExample example);

    int updateByExample(@Param("record") TDistrict record, @Param("example") TDistrictExample example);

    int updateByPrimaryKeySelective(TDistrict record);

    int updateByPrimaryKey(TDistrict record);
}