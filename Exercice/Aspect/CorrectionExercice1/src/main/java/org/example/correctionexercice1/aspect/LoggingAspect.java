package org.example.correctionexercice1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(org.example.correctionexercice1.annotation.Log)")
    public void logPointCut(){
    }

    @Before("logPointCut()")
    public void logGetMethodsInformation(JoinPoint joinPoint){
        String methodeName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("nom méthode : "+methodeName);
        System.out.println("arguments : ");
        Arrays.stream(args).toList().forEach(System.out::println);
    }

    @AfterReturning(value = "logPointCut()",returning = "result")
    public void logAfterReturningResult (Object result){
        System.out.println("retour de la methode : "+result);
    }

    @AfterThrowing(value = "logPointCut()",throwing = "ex")
    public void logAfterThrowingResult (Exception ex){
        System.out.println("Exception :"+ ex.getMessage());
    }
}
