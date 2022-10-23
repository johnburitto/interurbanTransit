package com.johnburitto.interurbantransit.aspect;

/*
 * @author Denta
 * @project interurbanTransit
 * @class GeneralInterceptorAspect
 * @version 1.0.0
 * @since 16.10.2022, 15:07
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.service.impls.nosql.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class GeneralInterceptorAspect {
    Logger logger = LogManager.getLogger(UserService.class);

    @Pointcut("@annotation(com.johnburitto.interurbantransit.annotations.LoggIt)")
    public void loggingPointCut() {}

    @Before("loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        logger.info("Start execute method::" + joinPoint.getSignature());
    }

    @After("loggingPointCut()")
    public void after(JoinPoint joinPoint) {
        logger.info("Method::" + joinPoint.getSignature() + " successfully executed");
    }

    @Around("loggingPointCut()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object retVal = pjp.proceed();
        long end = System.nanoTime();
        String methodName = pjp.getSignature().getName();

        logger.info("Execution of " + methodName + "()" + " took " + TimeUnit.NANOSECONDS.toMillis(end - start) + "ms");

        return retVal;
    }
}
