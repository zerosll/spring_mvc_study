package com.cafe24.springmvcstudy.common.aspect;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    //모든 Service Class
    //@Around("execution(* com..*Service.*(..))")
    @Around("@annotation(com.cafe24.springmvcstudy.common.annotation.ProgressTime)")
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

    @Pointcut("execution(public * com..*Controller.*(..))")
    public void controllerClassMethods() {}

    @Before(value = "controllerClassMethods()")
    public void checkSessionValid(JoinPoint joinPoint) {
        String token = joinPoint.getSignature().getDeclaringTypeName(); //JoinPoint클래스를 이용하여 타켓 메서드의 파라미터를 가로챌 수 있다.
        log.debug("Controller Before AOP >>>>>>>>>>>>>>>  {}", token);
    }




    /*@Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object targetAopLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(">>>> start - {}/{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        log.info(">>>> finished -  {}/{}" , joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        return result;
    }*/
}
