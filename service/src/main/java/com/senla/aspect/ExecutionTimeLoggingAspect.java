package com.senla.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExecutionTimeLoggingAspect {

    @Around("(@within(Benchmarked) || @annotation(Benchmarked)) && !@annotation(Benchmarked.Off)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();

        Object proceed = joinPoint.proceed();

        long endTime = System.nanoTime();
        log.info("Execution time of {} is: {} ns", joinPoint.getSignature(), endTime - startTime);

        return proceed;
    }
}
