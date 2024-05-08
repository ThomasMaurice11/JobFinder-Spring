package com.swSecurity.swSecurity.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Order(1)
@Component
public class LoggingAspect {


    Logger log = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut(value = "execution(* com.swSecurity.swSecurity.model.repository.*.*(..))")
    public void forRepositoryLog() {}

    @Pointcut(value = "execution(* com.swSecurity.swSecurity.service.*.*(..))")
    public void forServiceLog () {}

    @Pointcut(value = "execution(* com.swSecurity.swSecurity.controller.*.*(..))")
    public void forControllerLog () {}

    @Pointcut(value = "execution(* com.swSecurity.swSecurity.auth.*.*(..))")
    public void forAuthLog () {}

    @Pointcut(value = "forRepositoryLog() || forServiceLog() || forControllerLog() || forAuthLog ()")
    public void forAllApp() {}

	@Before(value = "forAllApp()")
	public void beforMethod(JoinPoint joinPoint) {

		String methodName = joinPoint.getSignature().toShortString();

		log.info("====>  Method Name is >> {}" , methodName );

		Object [] args = joinPoint.getArgs();

		for (Object arg : args) {

			log.info("===> argument >> {}" , arg);
		}

	}

}