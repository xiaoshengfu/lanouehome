package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TManager;
import com.huishoucat.manager.pojo.TManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TManagerMapper {
    int countByExample(TManagerExample example);

    int deleteByExample(TManagerExample example);

    int deleteByPrimaryKey(Long managerId);

    int insert(TManager record);

    int insertSelective(TManager record);

    List<TManager> selectByExample(TManagerExample example);

    TManager selectByPrimaryKey(Long managerId);

    int updateByExampleSelective(@Param("record") TManager record, @Param("example") TManagerExample example);

    int updateByExample(@Param("record") TManager record, @Param("example") TManagerExample example);

    int updateByPrimaryKeySelective(TManager record);

    int updateByPrimaryKey(TManager record);
}