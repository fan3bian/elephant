package com.fan3bian.elephant.aspect;

import com.fan3bian.elephant.annotation.RepeatSubmit;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepeatSubmitAspect {
    private static final Logger log = LogManager.getLogger(RepeatSubmitAspect.class);

    ExpressionParser parser = new SpelExpressionParser();

    @Around("@annotation(annotation)")
    public Object doAround(ProceedingJoinPoint pjp, RepeatSubmit annotation) throws Throwable {
        String redisKey = "";
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = pjp.getArgs();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        redisKey = parser.parseExpression(annotation.bizNo()).getValue(context, String.class);
        String value = "";
        if (StringUtils.isBlank(annotation.prefix())) {
            redisKey += annotation.prefix() + annotation.connector();
        }
        redisKey += annotation.bizNo();
        if (StringUtils.isBlank(annotation.subfix())) {
            redisKey += annotation.connector() + annotation.subfix();
        }
        log.error("防并发分布式锁：[" + redisKey + "]");
        if (StringUtils.isBlank(annotation.value())) {
            value = String.valueOf(System.currentTimeMillis());
        }
        try {

            return pjp.proceed();
        } finally {

        }
    }
}
