package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	//add all related advices for logging
	
	//let's start with @Before advice
	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	//@Before("execution(public void add*())")
	//@Before("execution(public void updateAccount())")
	public void beforeAddAccountAdvice() {
		System.out.println("\n====>>>> Executing @Before advice on method()");
	}
	


}
