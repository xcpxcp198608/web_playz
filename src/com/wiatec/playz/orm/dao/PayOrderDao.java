package com.wiatec.playz.orm.dao;

import com.wiatec.playz.orm.pojo.PayOrderInfo;

/**
 * Created by xuchengpeng on 21/08/2017.
 */
public interface PayOrderDao {
    void insertOne(PayOrderInfo payOrderInfo);
    int countOne(PayOrderInfo payOrderInfo);
    String getTime(PayOrderInfo payOrderInfo);
}
