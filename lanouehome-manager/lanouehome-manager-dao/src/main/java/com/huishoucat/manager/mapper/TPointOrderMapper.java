package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TPointOrder;
import com.huishoucat.manager.pojo.TPointOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPointOrderMapper {
    int countByExample(TPointOrderExample example);

    int deleteByExample(TPointOrderExample example);

    int deleteByPrimaryKey(Long pointOrderId);

    int insert(TPointOrder record);

    int insertSelective(TPointOrder record);

    List<TPointOrder> selectByExample(TPointOrderExample example);

    TPointOrder selectByPrimaryKey(Long pointOrderId);

    int updateByExampleSelective(@Param("record") TPointOrder record, @Param("example") TPointOrderExample example);

    int updateByExample(@Param("record") TPointOrder record, @Param("example") TPointOrderExample example);

    int updateByPrimaryKeySelective(TPointOrder record);

    int updateByPrimaryKey(TPointOrder record);
}