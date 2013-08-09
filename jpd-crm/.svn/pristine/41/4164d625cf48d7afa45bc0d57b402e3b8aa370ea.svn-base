package com.jingpaidang.crm.rpc.jos.proxy;

import com.jd.open.api.sdk.response.AbstractResponse;
import com.jingpaidang.crm.dao.merchant.MerchantMapper;
import com.jingpaidang.crm.domain.merchant.Merchant;
import com.jingpaidang.crm.rpc.jos.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: JackChan
 * Date: 8/7/13
 * Time: 8:48 AM
 */

@Aspect
@Component
public class JdServiceProxy {

    @Resource
    private TokenService tokenService;

    @Resource
    private MerchantMapper merchantMapper;


    @Pointcut("within(com.jingpaidang.crm.rpc.jos.JdService)")
    public  void refreshToken(){};


    /**
     * token过期，刷新token
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("com.jingpaidang.crm.rpc.jos.proxy.JdServiceProxy.refreshToken()")
    public AbstractResponse baseJdService(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        AbstractResponse response = (AbstractResponse) joinPoint.proceed(args);

        if(!"0".equals(response.getCode())) {

            Merchant byAccess = merchantMapper.findByAccess(args[0].toString());

            args[0] = tokenService.refreshToken(byAccess.getMerchantRefreshToken());

            byAccess.setMerchantAccessToken(args[0].toString());

            merchantMapper.updateAccessToken(byAccess);

            response =  (AbstractResponse) joinPoint.proceed(args);
        }

        return response;
    }
}
