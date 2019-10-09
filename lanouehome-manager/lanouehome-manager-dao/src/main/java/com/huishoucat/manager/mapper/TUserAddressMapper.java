package com.huishoucat.manager.mapper;


import com.huishoucat.manager.pojo.TUserAddress;
import com.huishoucat.manager.pojo.TUserAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserAddressMapper {
    int countByExample(TUserAddressExample example);

    int deleteByExample(TUserAddressExample example);

    int deleteByPrimaryKey(Long userAddressId);

    int insert(TUserAddress record);

    int insertSelective(TUserAddress record);

    List<TUserAddress> selectByExample(TUserAddressExample example);

    TUserAddress selectByPrimaryKey(Long userAddressId);

    int updateByExampleSelective(@Param("record") TUserAddress record, @Param("example") TUserAddressExample example);

    int updateByExample(@Param("record") TUserAddress record, @Param("example") TUserAddressExample example);

    int updateByPrimaryKeySelective(TUserAddress record);

    int updateByPrimaryKey(TUserAddress record);
}