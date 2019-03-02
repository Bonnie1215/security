package com.huyanqiu.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * aspect切面类
 * @ClassName MyAspect
 * @author: huyanqiu
 * @since: 2019/2/13 20:15
 */
@Aspect
@Component
public class MyAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.huyanqiu.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        logger.info("MyAspect before......");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            logger.info(arg.toString());
        }
        long startTime = System.currentTimeMillis();
        // 调用控制器中方法返回的结果
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("MyAspect话费时间："+(endTime-startTime));
        return result;
    }

}
