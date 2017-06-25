package com.example.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggerTest {

	private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

	@Pointcut("execution(* com.example.controller.*.*(..))")
	//参数会自动传入
	public void log() {}

	@Before("log()")
	public void beforeLog(JoinPoint joinPoint) {

		ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		HttpServletRequest request = ra.getRequest();

		String url = request.getRequestURL().toString();
		
		logger.info("URL: " + url);
		
		logger.info("接入类名和方法名" + joinPoint.getSignature().getDeclaringTypeName() + joinPoint.getSignature().getName());
		

		logger.info("接入点的参数" + joinPoint.getArgs());
		
	}
	
	@After(value = "log()")
	public void afer() {
		
		
		
		
	}
	
	
	@AfterReturning(returning="object", pointcut="log()") 
	public void afterReturning(Object object) {
		
		//returning的值要和这个方法传入的方法名相同，因为在真正执行切入点时，会获取到对应切入点的方法的返回值。
		logger.info("{}", object);
	}
	

}
