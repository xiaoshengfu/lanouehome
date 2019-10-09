package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TUserTransactionDetails;
import com.huishoucat.manager.pojo.TUserTransactionDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserTransactionDetailsMapper {
    int countByExample(TUserTransactionDetailsExample example);

    int deleteByExample(TUserTransactionDetailsExample example);

    int deleteByPrimaryKey(Long transactionId);

    int insert(TUserTransactionDetails record);

    int insertSelective(TUserTransactionDetails record);

    List<TUserTransactionDetails> selectByExample(TUserTransactionDetailsExample example);

    TUserTransactionDetails selectByPrimaryKey(Long transactionId);

    int updateByExampleSelective(@Param("record") TUserTransactionDetails record, @Param("example") TUserTransactionDetailsExample example);

    int updateByExample(@Param("record") TUserTransactionDetails record, @Param("example") TUserTransactionDetailsExample example);

    int updateByPrimaryKeySelective(TUserTransactionDetails record);

    int updateByPrimaryKey(TUserTransactionDetails record);
}