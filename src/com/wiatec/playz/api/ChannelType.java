package com.wiatec.playz.api;

import com.wiatec.playz.orm.pojo.ChannelTypeInfo;
import com.wiatec.playz.service.ChannelTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
@Controller
@RequestMapping(value = "/channeltype")
public class ChannelType {

    @Resource
    private ChannelTypeService channelTypeService;

    @RequestMapping(value = "/{type}")
    public @ResponseBody List<ChannelTypeInfo> get(@PathVariable int type){
        return channelTypeService.selectByType(type);
    }
}
