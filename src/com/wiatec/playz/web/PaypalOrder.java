package com.wiatec.playz.web;

import com.wiatec.playz.orm.pojo.AdminInfo;
import com.wiatec.playz.service.AdminService;
import com.wiatec.playz.service.PayPalOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuchengpeng on 18/08/2017.
 */
@Controller
@RequestMapping(value = "/order")
public class PaypalOrder {

    @Resource
    private PayPalOrderService payPalOrderService;

    @RequestMapping(value = "/show")
    public String get(HttpServletRequest request, Model model){
        return "order/show";
    }
}
