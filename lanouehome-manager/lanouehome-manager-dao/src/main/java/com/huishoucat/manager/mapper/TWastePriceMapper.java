package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TWastePrice;
import com.huishoucat.manager.pojo.TWastePriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWastePriceMapper {
    int countByExample(TWastePriceExample example);

    int deleteByExample(TWastePriceExample example);

    int deleteByPrimaryKey(Long wastePriceId);

    int insert(TWastePrice record);

    int insertSelective(TWastePrice record);

    List<TWastePrice> selectByExample(TWastePriceExample example);

    TWastePrice selectByPrimaryKey(Long wastePriceId);

    int updateByExampleSelective(@Param("record") TWastePrice record, @Param("example") TWastePriceExample example);

    int updateByExample(@Param("record") TWastePrice record, @Param("example") TWastePriceExample example);

    int updateByPrimaryKeySelective(TWastePrice record);

    int updateByPrimaryKey(TWastePrice record);
}