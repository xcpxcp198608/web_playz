package com.wiatec.playz.service;

import com.wiatec.playz.orm.dao.ChannelTypeDao;
import com.wiatec.playz.orm.pojo.ChannelTypeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
@Service
public class ChannelTypeService {

    @Resource
    private ChannelTypeDao channelTypeDao;

    @Transactional(readOnly = true)
    public List<ChannelTypeInfo> selectByType(int type){
        return channelTypeDao.selectByType(type);
    }
}
