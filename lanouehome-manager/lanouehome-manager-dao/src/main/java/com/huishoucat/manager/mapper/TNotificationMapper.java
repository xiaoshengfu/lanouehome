package com.huishoucat.manager.mapper;

import com.huishoucat.manager.pojo.TNotification;
import com.huishoucat.manager.pojo.TNotificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TNotificationMapper {
    int countByExample(TNotificationExample example);

    int deleteByExample(TNotificationExample example);

    int deleteByPrimaryKey(Long notificationId);

    int insert(TNotification record);

    int insertSelective(TNotification record);

    List<TNotification> selectByExample(TNotificationExample example);

    TNotification selectByPrimaryKey(Long notificationId);

    int updateByExampleSelective(@Param("record") TNotification record, @Param("example") TNotificationExample example);

    int updateByExample(@Param("record") TNotification record, @Param("example") TNotificationExample example);

    int updateByPrimaryKeySelective(TNotification record);

    int updateByPrimaryKey(TNotification record);
}