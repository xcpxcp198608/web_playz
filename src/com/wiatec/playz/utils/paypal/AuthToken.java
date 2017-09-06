package com.wiatec.playz.utils.paypal;

import com.wiatec.playz.utils.ApplicationContextHelper;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuchengpeng on 21/08/2017.
 */
public class AuthToken implements Runnable {

    private static final String TOKEN_URL = "https://api.sandbox.paypal.com/v1/oauth2/token";
    private static final String AUTH = "Basic QVdjVmppVVMyMThJOWVVbFM0NnUySFdzZGY4ZGJQWWxsc1A2Tnd2NGNleW90WnpHRnc3UFNQOGJlOU9OeWtWX29wYlJQRThOcmRYV2xyMU46RUNUeWlyc01MT0xRUkYxR3JkLXZmU3RNSjJ0ODIxNVlkV0JZc2IzN3ZWM29HdWZ3ZzlpdE5WMGd4UEVrMDJmNlpCbUdPcUFxal9xUS1WNWw=";
    private static OkHttpClient okHttpClient;

    static{
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(3000, TimeUnit.MILLISECONDS);
        okHttpClient = builder.build();
    }

    @Override
    public void run() {
        getPayPalToken();
    }

    private void getPayPalToken(){
        Request.Builder builder = new Request.Builder();
        builder.addHeader("Content-type", "application/x-www-form-urlencoded");
        builder.addHeader("Authorization", AUTH);
        FormBody.Builder builder1 = new FormBody.Builder();
        builder1.add("grant_type","client_credentials");
        builder.post(builder1.build());
        builder.url(TOKEN_URL);
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObject = new JSONObject(response.body().string());
                String token = jsonObject.getString("access_token");
                System.out.println("**************************** **************************** " + token);
                updateToken(token);
                int delay = jsonObject.getInt("expires_in");
                System.out.println("**************************** **************************** " + delay);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        getPayPalToken();
                    }
                };
                timer.schedule(timerTask,delay*1000);
            }
        });
    }

    private void updateToken(String token){
        String url = "http://playz.protv.company:8080/playz/paypal/" + token;
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
