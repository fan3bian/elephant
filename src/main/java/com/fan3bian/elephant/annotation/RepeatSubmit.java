package com.fan3bian.elephant.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RepeatSubmit {
    String prefix() default "";

    String connector() default "_";

    String bizNo();

    String value() default "";

    long timeout() default 30;

    TimeUnit unit() default TimeUnit.MINUTES;

}
