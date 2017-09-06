package com.wiatec.playz.orm.pojo;

/**
 * Created by xuchengpeng on 21/08/2017.
 */
public class PayPalTokenInfo {

    private int id;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "PayPalTokenInfo{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
