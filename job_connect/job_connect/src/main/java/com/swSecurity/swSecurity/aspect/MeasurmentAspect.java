package com.swSecurity.swSecurity.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Aspect
@Order(0)
@Component
public class MeasurmentAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.swSecurity.service..*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("KPI:");
        sb.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
                .append("\twithArgs: ").append("(").append(StringUtils.arrayToCommaDelimitedString(joinPoint.getArgs())).append(")");
        sb.append("\ttook: ");

        Object returnValue = joinPoint.proceed();

        long duration = System.currentTimeMillis() - startTime;
        sb.append(duration).append(" ms.");
        log.info(sb.toString());

        return returnValue;
    }


}
