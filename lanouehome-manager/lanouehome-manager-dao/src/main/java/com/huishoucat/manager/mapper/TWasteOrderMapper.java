package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TWasteOrder;
import com.huishoucat.manager.pojo.TWasteOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWasteOrderMapper {
    int countByExample(TWasteOrderExample example);

    int deleteByExample(TWasteOrderExample example);

    int deleteByPrimaryKey(Long wasteOrderId);

    int insert(TWasteOrder record);

    int insertSelective(TWasteOrder record);

    List<TWasteOrder> selectByExample(TWasteOrderExample example);

    TWasteOrder selectByPrimaryKey(Long wasteOrderId);

    int updateByExampleSelective(@Param("record") TWasteOrder record, @Param("example") TWasteOrderExample example);

    int updateByExample(@Param("record") TWasteOrder record, @Param("example") TWasteOrderExample example);

    int updateByPrimaryKeySelective(TWasteOrder record);

    int updateByPrimaryKey(TWasteOrder record);
}