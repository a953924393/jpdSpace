package com.jingpaidang.crm.service.example.exampleProxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/7/13
 * Time: 9:01 AM
 */

@Component
@Aspect
public class ExampleServiceProxy {


    @Pointcut("within(com.jingpaidang.crm.service.example.ExampleService)")
    private  void refreshToken(){};


    @Around("com.jingpaidang.crm.rpc.jos.proxy.JdServiceProxy.refreshToken()")
    public Object baseJdService(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        try {

            joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return args[0];
    }
}
