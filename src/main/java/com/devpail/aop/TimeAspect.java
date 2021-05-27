package com.devpail.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @description: @Aspect注解用法
 * @author: zhangzhb
 * @create: 2019-07-27 19:30
 * <p>
 * 2.1 @Aspect
 * <p>
 * 作用是把当前类标识为一个切面供容器读取
 * <p>
 * 2.2 @Before
 * 标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
 * <p>
 * 2.3 @AfterReturning
 * <p>
 * 后置增强，相当于AfterReturningAdvice，方法正常退出时执行
 * <p>
 * 2.4 @AfterThrowing
 * <p>
 * 异常抛出增强，相当于ThrowsAdvice
 * <p>
 * 2.5 @After
 * <p>
 * final增强，不管是抛出异常或者正常退出都会执行
 * <p>
 * 2.6 @Around
 * <p>
 * 环绕增强，相当于MethodInterceptor
 * <p>
 * 2.7 @DeclareParents
 * <p>
 * 引介增强，相当于IntroductionInterceptor
 **/

public class TimeAspect {

    @Around("execution(* com.demo.aop.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        long startTime = System.currentTimeMillis();

        Object object = pjp.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println("time aspect consume " + (endTime - startTime) + " ms");

        System.out.println("time aspect end");

        return object;
    }
}
