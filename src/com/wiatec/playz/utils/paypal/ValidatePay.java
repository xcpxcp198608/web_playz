package com.wiatec.playz.utils.paypal;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuchengpeng on 21/08/2017.
 */
public class ValidatePay implements Runnable {

    private static OkHttpClient okHttpClient;

    static{
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(3000, TimeUnit.MILLISECONDS);
        okHttpClient = builder.build();
    }

    private String url;
    private String token;

    public ValidatePay(String url, String token) {
        this.url = url;
        this.token = token;
    }

    @Override
    public void run() {
        validate(url, token);
    }

    private void validate(String url, String token){
        Request.Builder builder = new Request.Builder();
        builder.addHeader("Content-type", "application/json");
        builder.addHeader("Authorization","Bearer "+token);
        builder.url(url);
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                System.out.println(s);
            }
        });
    }
}
