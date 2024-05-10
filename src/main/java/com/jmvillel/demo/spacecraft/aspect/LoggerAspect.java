package com.jmvillel.demo.spacecraft.aspect;


import java.util.Locale;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	
	MessageSource messageSource;
	
	Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	
	private static final Locale locale = LocaleContextHolder.getLocale();
	
    public LoggerAspect(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
	
	@Pointcut("execution(* *..SpaceCraftService.findOneById(Long))")
	private void findOneByIdMethodFromSpaceCraftService() {
	}

	@Before(value = "findOneByIdMethodFromSpaceCraftService()")
	public void logBefore(JoinPoint joinPoint) {
	    Object[] args = joinPoint.getArgs();

	    if(args !=null && args.length>0 && args[0] != null) {
	    	Long id = (Long) args[0];
	    	if(id < 0) logger.info(messageSource.getMessage("error.method.receive.negative.value", new Object[]{"findOneById",id}, locale));
	    }
	}
}
