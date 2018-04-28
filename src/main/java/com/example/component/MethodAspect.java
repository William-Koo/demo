package com.example.component;


import com.example.util.ContextHolderUtils;
import com.example.util.JsonUtil;
import com.example.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @description: 方法调用统一切面处理
 * @Copyright:   (c) 2017
 * @company:
 * @author:      William-Koo
 * @version:     1.0
 * @date:    2017/10/2 10:54
 */
@Aspect
@Component
public class MethodAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodAspect.class);

//    @Value("${ip}")
//    private String ip;

    @Pointcut("execution(public * com.example.controller..*(..))")
    public void pkg(){}


    /**
     * 环绕通知
     * @param pjp 指各个参数点
     * @return
     * @throws Throwable
     */
    @Around("pkg()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = StringUtil.getCurrentTime();
        Object result = null;

        try {
            //前置通知
            HttpServletRequest req = ContextHolderUtils.getRequest();
            Signature sign = pjp.getSignature();

            LOGGER.info("请求url: {}", req.getRequestURL());
            LOGGER.info("方法类型: {}", req.getMethod());
            LOGGER.info("ip: {}", req.getRemoteAddr());
            LOGGER.info("请求方法名: {}", sign.getDeclaringTypeName()+"."+sign.getName());

            Object[] args = pjp.getArgs();
            StringBuilder log = new StringBuilder();
            for (Object arg : args) {
                log.append(arg + " ");
            }

            LOGGER.info("请求入参: {}", log);

            //执行目标方法
            result = pjp.proceed();

            //返回通知
            LOGGER.info("返回值：{}", JsonUtil.objToJson(result));

        } catch (Throwable e) {
            //异常通知
            LOGGER.error("调用方法异常：", e);
            throw new Throwable("调用方法异常：", e);
        }

        //后置通知
        long end = StringUtil.getCurrentTime();
        LOGGER.info("程序执行时间：{}", end - start);

        return result;
    }


}
