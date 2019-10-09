package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TWasteAttribute;
import com.huishoucat.manager.pojo.TWasteAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWasteAttributeMapper {
    int countByExample(TWasteAttributeExample example);

    int deleteByExample(TWasteAttributeExample example);

    int deleteByPrimaryKey(Long wasteAttributeId);

    int insert(TWasteAttribute record);

    int insertSelective(TWasteAttribute record);

    List<TWasteAttribute> selectByExampleWithBLOBs(TWasteAttributeExample example);

    List<TWasteAttribute> selectByExample(TWasteAttributeExample example);

    TWasteAttribute selectByPrimaryKey(Long wasteAttributeId);

    int updateByExampleSelective(@Param("record") TWasteAttribute record, @Param("example") TWasteAttributeExample example);

    int updateByExampleWithBLOBs(@Param("record") TWasteAttribute record, @Param("example") TWasteAttributeExample example);

    int updateByExample(@Param("record") TWasteAttribute record, @Param("example") TWasteAttributeExample example);

    int updateByPrimaryKeySelective(TWasteAttribute record);

    int updateByPrimaryKeyWithBLOBs(TWasteAttribute record);

    int updateByPrimaryKey(TWasteAttribute record);
}