package com.wiatec.playz.service;

import com.wiatec.playz.orm.dao.UpgradeDao;
import com.wiatec.playz.orm.pojo.UpgradeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
@Service
public class UpgradeService {

    @Resource
    private UpgradeDao upgradeDao;

    @Transactional(readOnly = true)
    public UpgradeInfo selectOne(){
        return upgradeDao.selectOne();
    }
}
