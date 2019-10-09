package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TSystem;
import com.huishoucat.manager.pojo.TSystemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSystemMapper {
    int countByExample(TSystemExample example);

    int deleteByExample(TSystemExample example);

    int deleteByPrimaryKey(Long systemId);

    int insert(TSystem record);

    int insertSelective(TSystem record);

    List<TSystem> selectByExample(TSystemExample example);

    TSystem selectByPrimaryKey(Long systemId);

    int updateByExampleSelective(@Param("record") TSystem record, @Param("example") TSystemExample example);

    int updateByExample(@Param("record") TSystem record, @Param("example") TSystemExample example);

    int updateByPrimaryKeySelective(TSystem record);

    int updateByPrimaryKey(TSystem record);
}