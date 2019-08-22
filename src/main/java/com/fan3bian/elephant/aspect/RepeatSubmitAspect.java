package com.fan3bian.elephant.aspect;

import com.fan3bian.elephant.annotation.RepeatSubmit;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RepeatSubmitAspect {
    private static final Logger log = LogManager.getLogger(RepeatSubmitAspect.class);

    ExpressionParser parser = new SpelExpressionParser();

    @Around("@annotation(annotation)")
    public Object doAround(ProceedingJoinPoint pjp, RepeatSubmit annotation) throws Throwable {

//        long start = System.currentTimeMillis();

        String bizNo = annotation.bizNo();

        String value = annotation.value();
        long timeout = annotation.timeout();
        TimeUnit unit = annotation.unit();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = pjp.getArgs();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        String redisKey = null;
        Object result = null;
        redisKey = parser.parseExpression(bizNo).getValue(context, String.class);
        log.info("redisKey="+redisKey);
        try {

            result = pjp.proceed();
        } finally {

        }
        return result;
    }
}
