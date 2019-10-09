package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TRelationRoleFunction;
import com.huishoucat.manager.pojo.TRelationRoleFunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRelationRoleFunctionMapper {
    int countByExample(TRelationRoleFunctionExample example);

    int deleteByExample(TRelationRoleFunctionExample example);

    int insert(TRelationRoleFunction record);

    int insertSelective(TRelationRoleFunction record);

    List<TRelationRoleFunction> selectByExample(TRelationRoleFunctionExample example);

    int updateByExampleSelective(@Param("record") TRelationRoleFunction record, @Param("example") TRelationRoleFunctionExample example);

    int updateByExample(@Param("record") TRelationRoleFunction record, @Param("example") TRelationRoleFunctionExample example);
}