package org.example.demoaspect.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DemoAspectAdvice {

//    @Before("execution (* org.example.demoaspect.service.*.*(..))")
//    public void addBeforeEachExecutionMethode (){
//        System.out.println("run before each Methode");
////        throw new RuntimeException("error");
//    }
//
//    @After("execution (* org.example.demoaspect.service.*.*(..))")
//    public void addAfterEachExecutionMethode (){
//        System.out.println("run after each Methode");
////        throw new RuntimeException("error");
//    }
//
//    @AfterReturning(value = "execution (* org.example.demoaspect.service.MainService.methodWithResult(..))",returning = "result")
//    public void addAfterReturningEachExecutionMethode (Object result){
//        System.out.println("run after each Methode with result = "+result);
////        throw new RuntimeException("error");
//    }
//
//    @AfterThrowing("execution (* org.example.demoaspect.service.*.*(..))")
//    public void addAfterThrowingMethode (){
//        System.out.println("after throwing");
//    }

    @Pointcut("@annotation(org.example.demoaspect.annotation.Performance)")
    public void performancePointCut(){
    }

    @Before("performancePointCut()")
    public void beforePointCut (JoinPoint joinPoint){
        System.out.println("Before execution " + joinPoint.getSignature().getName());
    }

    @Around("performancePointCut()")
    public Object around (ProceedingJoinPoint proceedingJoinPoint){
        try {
            System.out.println("around cut");

            Object[] arg = proceedingJoinPoint.getArgs();
            System.out.println(arg);

            Object result = proceedingJoinPoint.proceed();
            System.out.println("result : "+ result);
            return result;

        }catch (Throwable e){
            throw new RuntimeException();
        }
    }
}
