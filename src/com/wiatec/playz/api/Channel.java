package com.wiatec.playz.api;

import com.wiatec.playz.orm.pojo.ChannelInfo;
import com.wiatec.playz.service.ChannelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
@Controller
@RequestMapping(value = "/channel")
public class Channel {

    @Resource
    private ChannelService channelService;

    @RequestMapping(value = "/")
    public @ResponseBody List<ChannelInfo> get(@RequestParam String country){
        return channelService.selectByCountry(country);
    }
}
