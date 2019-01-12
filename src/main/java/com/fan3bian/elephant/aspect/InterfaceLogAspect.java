package com.fan3bian.elephant.aspect;

import com.fan3bian.elephant.annotation.InterfaceLog;
import com.fan3bian.elephant.utils.JsonUtil;
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

@Aspect
@Component
public class InterfaceLogAspect {
    private static final Logger log = LogManager.getLogger(InterfaceLogAspect.class);
    ExpressionParser parser = new SpelExpressionParser();

    @Around("@annotation(interfaceLog)")
    public Object doAround(ProceedingJoinPoint pjp, InterfaceLog interfaceLog) throws Throwable {

        long start = System.currentTimeMillis();
        String value = interfaceLog.value();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String interfaceName = signature.getDeclaringTypeName() + "#" + signature.getName();
        log.info("interfaceName=" + interfaceName);
        String[] parameterNames = signature.getParameterNames();
        Object[] args = pjp.getArgs();
        log.info("interfaceArgs=" + JsonUtil.toJson(args));
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        String bizNo = null;
        Object result = null;
        try {
            try {
                bizNo = parser.parseExpression(value).getValue(context, String.class);
            } catch (ExpressionException e) {
                log.error(e);
//                throw new IllegalArgumentException("bizNo is illegal");
            }
            log.error("interfaceBizNo=【" + bizNo + "】");
            result = pjp.proceed();
        } finally {
            log.info("interfaceReturnVal=" + JsonUtil.toJson(result));
            long end = System.currentTimeMillis();
            log.info("interfaceExeTime=" + (end - start));
//            try {
//                InterfaceLogCache.print(bizNo, interfaceName, end - start, result, args);
//            } catch (Exception e) {
//                log.error(e);
//            }
        }
        return result;
    }

}
