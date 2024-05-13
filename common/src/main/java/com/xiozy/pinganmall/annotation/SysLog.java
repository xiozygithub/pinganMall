package com.xiozy.pinganmall.annotation;

import java.lang.annotation.*;

/**
 * @Title: Loggable.java
 * @Description: 描述此类的主要功能和用途
 * @Author: xiozy
 * @Date: 2024/5/13
 * @Version: 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String level() default "INFO";
    String description() default "";
}
