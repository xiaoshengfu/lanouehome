package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TRelationManagerRole;
import com.huishoucat.manager.pojo.TRelationManagerRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRelationManagerRoleMapper {
    int countByExample(TRelationManagerRoleExample example);

    int deleteByExample(TRelationManagerRoleExample example);

    int insert(TRelationManagerRole record);

    int insertSelective(TRelationManagerRole record);

    List<TRelationManagerRole> selectByExample(TRelationManagerRoleExample example);

    int updateByExampleSelective(@Param("record") TRelationManagerRole record, @Param("example") TRelationManagerRoleExample example);

    int updateByExample(@Param("record") TRelationManagerRole record, @Param("example") TRelationManagerRoleExample example);
}