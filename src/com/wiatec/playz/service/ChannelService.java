package com.wiatec.playz.service;

import com.wiatec.playz.orm.dao.ChannelDao;
import com.wiatec.playz.orm.pojo.ChannelInfo;
import com.wiatec.playz.utils.AESUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
@Service
public class ChannelService {

    @Resource
    private ChannelDao channelDao;

    @Transactional(readOnly = true)
    public List<ChannelInfo> selectByCountry(String country){
        List<ChannelInfo> channelInfoList = channelDao.selectByCountry(country);
        for(ChannelInfo channelInfo : channelInfoList){
            channelInfo.setUrl(AESUtil.encrypt(channelInfo.getUrl(), AESUtil.KEY));
        }
        return channelInfoList;
    }
}
