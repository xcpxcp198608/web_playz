package com.wiatec.playz.service;

import com.wiatec.playz.entry.ResultInfo;
import com.wiatec.playz.orm.dao.PayOrderDao;
import com.wiatec.playz.orm.dao.PayPayTokenDao;
import com.wiatec.playz.orm.pojo.PayOrderInfo;
import com.wiatec.playz.utils.MD5Utils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by xuchengpeng on 21/08/2017.
 */
@Service
public class PayPalOrderService {

    private static final String PAYPAL_VALIDATE_API = "https://api.sandbox.paypal.com/v1/payments/payment/";

    @Resource
    private PayPayTokenDao payPayTokenDao;
    @Resource
    private PayOrderDao payOrderDao;

    @Transactional(readOnly = true)
    public String getToken(){
        return payPayTokenDao.get();
    }

    @Transactional
    public void updateToken(String token){
        payPayTokenDao.update(token);
    }

    @Transactional
    public ResultInfo validatePay(String channelName, String payId){
        String token = getToken();
        String url = PAYPAL_VALIDATE_API+payId;
        return validate(channelName, url, token);
    }

    @Transactional(readOnly = true)
    public ResultInfo validateAuth(String channelName, String payId){
        PayOrderInfo payOrderInfo = new PayOrderInfo();
        payOrderInfo.setChannelName(channelName);
        payOrderInfo.setPayId(payId);
        ResultInfo resultInfo = new ResultInfo();
        if(payOrderDao.countOne(payOrderInfo) != 1){
            resultInfo.setCode(400);
            resultInfo.setStatus("Pay state error");
            resultInfo.setMessage("");
            return resultInfo;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 0;
        try {
            time =simpleDateFormat.parse(payOrderDao.getTime(payOrderInfo)).getTime() * 1000;
        } catch (ParseException e) {
            time = 0;
        }
        if(System.currentTimeMillis() > time + 86400){
            resultInfo.setCode(400);
            resultInfo.setStatus("auth timeout, only 24H");
            resultInfo.setMessage("");
            return resultInfo;
        }
        resultInfo.setCode(200);
        resultInfo.setStatus("OK");
        resultInfo.setMessage("");
        return resultInfo;
    }

    private ResultInfo validate(String channelName, String url, String token){
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder builder = new StringBuilder();
        ResultInfo resultInfo = new ResultInfo();
        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();
            urlConnection.setRequestProperty("Content-type","application/json");
            urlConnection.setRequestProperty("Authorization","Bearer "+token);
            inputStream = urlConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            String result =  builder.toString();
            JSONObject jsonObject = new JSONObject(result);
            System.out.println(jsonObject);
            PayOrderInfo payOrderInfo = new PayOrderInfo();
            payOrderInfo.setChannelName(channelName);
            payOrderInfo.setPayId(jsonObject.getString("id"));
            payOrderInfo.setState(jsonObject.getString("state"));
            payOrderInfo.setCart(jsonObject.getString("cart"));
            JSONObject payer = jsonObject.getJSONObject("payer");
            payOrderInfo.setPaymentMethod(payer.getString("payment_method"));
            payOrderInfo.setPaymentStatus(payer.getString("status"));
            JSONObject payer_info = payer.getJSONObject("payer_info");
            payOrderInfo.setEmail(payer_info.getString("email"));
            payOrderInfo.setFirstName(payer_info.getString("first_name"));
            payOrderInfo.setLastName(payer_info.getString("last_name"));
            payOrderInfo.setPayerId(payer_info.getString("payer_id"));
            payOrderInfo.setPhone(payer_info.getString("payer_id"));
            payOrderInfo.setCountryCode(payer_info.getString("country_code"));
            JSONArray transactions = jsonObject.getJSONArray("transactions");
            JSONObject jsonObject1 = transactions.getJSONObject(0);
            payOrderInfo.setDescription(jsonObject1.getString("description"));
            JSONArray related_resources = jsonObject1.getJSONArray("related_resources");
            JSONObject jsonObject2 = related_resources.getJSONObject(0);
            JSONObject sale = jsonObject2.getJSONObject("sale");
            JSONObject amount = sale.getJSONObject("amount");
            payOrderInfo.setPrice(Float.parseFloat(amount.getString("total")));
            payOrderInfo.setCurrency(amount.getString("currency"));
            JSONObject transaction_fee = sale.getJSONObject("transaction_fee");
            payOrderInfo.setTransactionFee(Float.parseFloat(transaction_fee.getString("value")));
            payOrderInfo.setCreateTime(sale.getString("create_time"));
            payOrderInfo.setUpdateTime(sale.getString("update_time"));

            if("approved".equals(payOrderInfo.getState())){
                String auth = MD5Utils.create("gfdgg","dfggfd");
                payOrderInfo.setAuth(auth);
                payOrderDao.insertOne(payOrderInfo);
                resultInfo.setCode(200);
                resultInfo.setStatus("OK");
                resultInfo.setMessage(auth);
                return resultInfo;
            }else{
                resultInfo.setCode(400);
                resultInfo.setStatus("Pay state error");
                resultInfo.setMessage("");
                return resultInfo;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return resultInfo;
    }
}
