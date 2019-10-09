package com.huishoucat.manager.mapper;


import com.huishoucat.manager.pojo.TRecycler;
import com.huishoucat.manager.pojo.TRecyclerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRecyclerMapper {
    int countByExample(TRecyclerExample example);

    int deleteByExample(TRecyclerExample example);

    int deleteByPrimaryKey(Long recyclerId);

    int insert(TRecycler record);

    int insertSelective(TRecycler record);

    List<TRecycler> selectByExample(TRecyclerExample example);

    TRecycler selectByPrimaryKey(Long recyclerId);

    int updateByExampleSelective(@Param("record") TRecycler record, @Param("example") TRecyclerExample example);

    int updateByExample(@Param("record") TRecycler record, @Param("example") TRecyclerExample example);

    int updateByPrimaryKeySelective(TRecycler record);

    int updateByPrimaryKey(TRecycler record);
}