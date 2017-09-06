package com.wiatec.playz.web;

import com.wiatec.playz.orm.pojo.AdminInfo;
import com.wiatec.playz.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
@Controller
@RequestMapping(value = "/admin")
public class Admin {

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/login")
    public String get(@ModelAttribute AdminInfo adminInfo){
        return adminService.validate(adminInfo);
    }
}
