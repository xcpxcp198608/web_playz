package com.wiatec.playz.orm.dao;

import com.wiatec.playz.orm.pojo.AdminInfo;
import com.wiatec.playz.orm.pojo.ChannelInfo;

import java.util.List;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
public interface AdminDao {
    int countOne(AdminInfo adminInfo);
}
