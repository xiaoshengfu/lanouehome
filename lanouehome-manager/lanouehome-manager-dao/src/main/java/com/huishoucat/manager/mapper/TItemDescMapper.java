package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TItemDesc;
import com.huishoucat.manager.pojo.TItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TItemDescMapper {
    int countByExample(TItemDescExample example);

    int deleteByExample(TItemDescExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(TItemDesc record);

    int insertSelective(TItemDesc record);

    List<TItemDesc> selectByExampleWithBLOBs(TItemDescExample example);

    List<TItemDesc> selectByExample(TItemDescExample example);

    TItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") TItemDesc record, @Param("example") TItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") TItemDesc record, @Param("example") TItemDescExample example);

    int updateByExample(@Param("record") TItemDesc record, @Param("example") TItemDescExample example);

    int updateByPrimaryKeySelective(TItemDesc record);

    int updateByPrimaryKeyWithBLOBs(TItemDesc record);

    int updateByPrimaryKey(TItemDesc record);
}