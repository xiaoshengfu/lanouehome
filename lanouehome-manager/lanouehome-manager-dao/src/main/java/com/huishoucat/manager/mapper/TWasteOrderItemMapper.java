package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TWasteOrderItem;
import com.huishoucat.manager.pojo.TWasteOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWasteOrderItemMapper {
    int countByExample(TWasteOrderItemExample example);

    int deleteByExample(TWasteOrderItemExample example);

    int deleteByPrimaryKey(Long orderItemId);

    int insert(TWasteOrderItem record);

    int insertSelective(TWasteOrderItem record);

    List<TWasteOrderItem> selectByExample(TWasteOrderItemExample example);

    TWasteOrderItem selectByPrimaryKey(Long orderItemId);

    int updateByExampleSelective(@Param("record") TWasteOrderItem record, @Param("example") TWasteOrderItemExample example);

    int updateByExample(@Param("record") TWasteOrderItem record, @Param("example") TWasteOrderItemExample example);

    int updateByPrimaryKeySelective(TWasteOrderItem record);

    int updateByPrimaryKey(TWasteOrderItem record);
}