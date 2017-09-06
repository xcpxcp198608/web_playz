package com.wiatec.playz.utils;

import com.wiatec.playz.utils.paypal.AuthToken;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuchengpeng on 12/06/2017.
 */
public class ApplicationContextHelper implements ApplicationContextAware{

    private static ApplicationContext applicationContext;
    private static ScheduledExecutorService scheduledExecutorService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
        scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.schedule(new AuthToken(), 10000, TimeUnit.MILLISECONDS);
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static ScheduledExecutorService getScheduledExecutorService(){
        return scheduledExecutorService;
    }
}
