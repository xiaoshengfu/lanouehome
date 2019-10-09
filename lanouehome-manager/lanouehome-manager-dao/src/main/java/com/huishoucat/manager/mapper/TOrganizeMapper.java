package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TOrganize;
import com.huishoucat.manager.pojo.TOrganizeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOrganizeMapper {
    int countByExample(TOrganizeExample example);

    int deleteByExample(TOrganizeExample example);

    int deleteByPrimaryKey(Long organizeId);

    int insert(TOrganize record);

    int insertSelective(TOrganize record);

    List<TOrganize> selectByExample(TOrganizeExample example);

    TOrganize selectByPrimaryKey(Long organizeId);

    int updateByExampleSelective(@Param("record") TOrganize record, @Param("example") TOrganizeExample example);

    int updateByExample(@Param("record") TOrganize record, @Param("example") TOrganizeExample example);

    int updateByPrimaryKeySelective(TOrganize record);

    int updateByPrimaryKey(TOrganize record);
}