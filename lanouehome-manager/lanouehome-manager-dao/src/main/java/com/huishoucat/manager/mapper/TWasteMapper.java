package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TWaste;
import com.huishoucat.manager.pojo.TWasteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWasteMapper {
    int countByExample(TWasteExample example);

    int deleteByExample(TWasteExample example);

    int deleteByPrimaryKey(Long wasteId);

    int insert(TWaste record);

    int insertSelective(TWaste record);

    List<TWaste> selectByExampleWithBLOBs(TWasteExample example);

    List<TWaste> selectByExample(TWasteExample example);

    TWaste selectByPrimaryKey(Long wasteId);

    int updateByExampleSelective(@Param("record") TWaste record, @Param("example") TWasteExample example);

    int updateByExampleWithBLOBs(@Param("record") TWaste record, @Param("example") TWasteExample example);

    int updateByExample(@Param("record") TWaste record, @Param("example") TWasteExample example);

    int updateByPrimaryKeySelective(TWaste record);

    int updateByPrimaryKeyWithBLOBs(TWaste record);

    int updateByPrimaryKey(TWaste record);
}