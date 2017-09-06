package com.wiatec.playz.api;

import com.wiatec.playz.entry.ResultInfo;
import com.wiatec.playz.service.PayPalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by xuchengpeng on 21/08/2017.
 */
@Controller
@RequestMapping(value = "/paypal")
public class PayPal {

    @Resource
    private PayPalService payPalService;

    @RequestMapping(value = "/{token}")
    public void update(@PathVariable String token){
        payPalService.updateToken(token);
    }

    @RequestMapping(value = "/validate/{channelName}/{payId}")
    public @ResponseBody ResultInfo validatePay(@PathVariable String channelName,
                                                @PathVariable String payId){
        return payPalService.validatePay(channelName, payId);
    }

    @RequestMapping(value = "/auth/{channelName}/{payId}")
    public @ResponseBody ResultInfo validateAuth(@PathVariable String channelName,
                                   @PathVariable String payId){
        return payPalService.validateAuth(channelName, payId);
    }
}
