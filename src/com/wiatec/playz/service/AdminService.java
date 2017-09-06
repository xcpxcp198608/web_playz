package com.wiatec.playz.service;

import com.wiatec.playz.orm.dao.AdminDao;
import com.wiatec.playz.orm.pojo.AdminInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
@Service
public class AdminService {

    @Resource
    private AdminDao adminDao;

    @Transactional(readOnly = true)
    public String validate(AdminInfo adminInfo){
        if(adminDao.countOne(adminInfo) == 1){
            return "redirect:/order/show";
        }else{
            throw new RuntimeException("login information error");
        }
    }
}
