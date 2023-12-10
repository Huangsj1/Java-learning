package com.itheima.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 定义注解类（需要加上下面两个元注解）
@Retention(RetentionPolicy.RUNTIME)     // 运行时生效
@Target(ElementType.METHOD)             // 作用在方法上
public @interface Log {
}
