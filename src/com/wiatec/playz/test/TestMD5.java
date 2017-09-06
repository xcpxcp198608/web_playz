package com.wiatec.playz.test;

import com.wiatec.playz.utils.MD5Utils;
import org.junit.Test;

/**
 * Created by xuchengpeng on 21/08/2017.
 */
public class TestMD5 {

    @Test
    public void testCreateMd5(){
        String s = MD5Utils.create("sdf", "sf");
        System.out.println(s);
    }
}
