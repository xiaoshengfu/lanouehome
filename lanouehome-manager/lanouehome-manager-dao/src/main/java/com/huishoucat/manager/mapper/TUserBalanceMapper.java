package com.huishoucat.manager.mapper;


import com.huishoucat.manager.pojo.TUserBalance;
import com.huishoucat.manager.pojo.TUserBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserBalanceMapper {
    int countByExample(TUserBalanceExample example);

    int deleteByExample(TUserBalanceExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(TUserBalance record);

    int insertSelective(TUserBalance record);

    List<TUserBalance> selectByExample(TUserBalanceExample example);

    TUserBalance selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") TUserBalance record, @Param("example") TUserBalanceExample example);

    int updateByExample(@Param("record") TUserBalance record, @Param("example") TUserBalanceExample example);

    int updateByPrimaryKeySelective(TUserBalance record);

    int updateByPrimaryKey(TUserBalance record);
}