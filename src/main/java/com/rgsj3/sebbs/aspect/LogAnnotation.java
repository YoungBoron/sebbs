package com.rgsj3.sebbs.aspect;

import java.lang.annotation.*;
/**
 *自定义注解 拦截service
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface LogAnnotation {
    String description()  default "";
}
