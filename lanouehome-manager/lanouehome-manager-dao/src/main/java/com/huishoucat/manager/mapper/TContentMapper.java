package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TContent;
import com.huishoucat.manager.pojo.TContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TContentMapper {
    int countByExample(TContentExample example);

    int deleteByExample(TContentExample example);

    int deleteByPrimaryKey(Long contentId);

    int insert(TContent record);

    int insertSelective(TContent record);

    List<TContent> selectByExampleWithBLOBs(TContentExample example);

    List<TContent> selectByExample(TContentExample example);

    TContent selectByPrimaryKey(Long contentId);

    int updateByExampleSelective(@Param("record") TContent record, @Param("example") TContentExample example);

    int updateByExampleWithBLOBs(@Param("record") TContent record, @Param("example") TContentExample example);

    int updateByExample(@Param("record") TContent record, @Param("example") TContentExample example);

    int updateByPrimaryKeySelective(TContent record);

    int updateByPrimaryKeyWithBLOBs(TContent record);

    int updateByPrimaryKey(TContent record);
}