package org.blazer.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogger {

	private static final Logger logger = LoggerFactory.getLogger(ServiceLogger.class);

	private static final String POINT = ".";

	//@Around(value = "execution(* org.blazer.dataload.service.*.*(..))")
	public Object aspectString(ProceedingJoinPoint joinPoint) throws Throwable {
		// 获得控制的具体方法,格式:org.blazer.dataload.service.RoleService.add
		String compare = joinPoint.getSignature().getDeclaringTypeName() + POINT + joinPoint.getSignature().getName();
		long l1 = System.currentTimeMillis();
		logger.info("Aspect Service : {}", compare);
		try {
			logger.info("Args length : {}", joinPoint.getArgs().length);
			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				logger.info("Arg[{}] : {}", i, joinPoint.getArgs()[i]);
			}
			return joinPoint.proceed();
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			long l2 = System.currentTimeMillis();
			logger.info("Method waste time : {}", l2 - l1);
		}
	}

}
