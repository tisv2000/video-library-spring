package com.tisv2000.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class FirstAspect {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceLayer() {
    }

    @Before("isServiceLayer()")
    public void addLoggingBeforeMethodCall(JoinPoint joinPoint) {
        log.info("Incoming params for method " + joinPoint.getSignature().getName() + ": " + Arrays.toString(joinPoint.getArgs()));
    }

    // почему-то в логам Outcome отображается перыми, а потом Incoming params
    @AfterReturning(value = "isServiceLayer()", returning = "result")
    public void addLoggingAfterReturning(Object result) {
        log.info("Outcome result: " + result);
    }
}
