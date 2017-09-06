package com.wiatec.playz.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xuchengpeng on 07/06/2017.
 */
public class MD5Utils {

    public static String create(String s1, String s2){
        try {
            long time = System.currentTimeMillis();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((s1 + s2 + time).getBytes());
            BigInteger bigInteger = new BigInteger(1,messageDigest.digest());
            return bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
