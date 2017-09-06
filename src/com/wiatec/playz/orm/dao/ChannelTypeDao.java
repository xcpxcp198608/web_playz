package com.wiatec.playz.orm.dao;

import com.wiatec.playz.orm.pojo.ChannelTypeInfo;

import java.util.List;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
public interface ChannelTypeDao {
    List<ChannelTypeInfo> selectByType(int type);
}
