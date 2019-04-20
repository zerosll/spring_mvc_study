package com.cafe24.springmvcstudy.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    //모든 Service Class
    @Around("execution(* com..*Service.*(..))")
    public Object commonServiceLogging(ProceedingJoinPoint joinPoint) throws Throwable {

        long currentTime = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.debug("=================================================");
        log.debug(">>>>>>>>> LOGGING START >>>>>>>>>>");
        log.debug("[class]:{}", className);
        log.debug("[method]:{}()", methodName);

        Object result = joinPoint.proceed();

        //log.debug("[class]:{}", className);
        //log.debug("[method]:{}()", methodName);
        log.debug("[소요시간]: {}ms", new Object[]{(System.currentTimeMillis()-currentTime)});
        log.debug(">>>>>>>>>> LOGGING END >>>>>>>>>>");
        log.debug("=================================================");

        return result;
    }

    /*@Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object targetAopLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(">>>> start - {}/{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.info(">>>> finished -  {}/{}" , joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        return result;
    }*/
}
