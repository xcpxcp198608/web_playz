package com.wiatec.playz.api;

import com.wiatec.playz.orm.pojo.UpgradeInfo;
import com.wiatec.playz.service.UpgradeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
@Controller
@RequestMapping(value = "/upgrade")
public class Upgrade {

    @Resource
    private UpgradeService upgradeService;

    @RequestMapping(value = "/")
    public @ResponseBody UpgradeInfo get(){
        return upgradeService.selectOne();
    }
}
