package com.xiozy.pinganmall.advice;

import com.xiozy.pinganmall.annotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Title: LoggingAspect.java
 * @Description: 系统日志，切面处理类
 * @Author: xiozy
 * @Date: 2024/5/13
 * @Version: 1.0
 */

@Component
@Aspect
public class SysLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Pointcut("@annotation(com.xiozy.pinganmall.annotation.SysLog)")
    public void sysLogAnnotationPointcut() {}

    @Around("sysLogAnnotationPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysLog sysLog = signature.getMethod().getAnnotation(SysLog.class);

        // 获取当前时间和线程名
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(formatter);
        String threadName = Thread.currentThread().getName();

        // 构建日志信息
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        String level = sysLog.level();
        String desc = sysLog.description();
        String logMessage = String.format("[%s] %s [%s] %s: Entering method %s - %s", timestamp, threadName, level, className, methodName, desc);

        switch (level.toUpperCase()) {
            case "TRACE":
                logger.trace(logMessage);
                break;
            case "DEBUG":
                logger.debug(logMessage);
                break;
            case "INFO":
                logger.info(logMessage);
                break;
            case "WARN":
                logger.warn(logMessage);
                break;
            case "ERROR":
                logger.error(logMessage);
                break;
            default:
                logger.info(logMessage); // 默认为INFO
                break;
        }

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            // 异常处理
            String errMsg = String.format("[%s] %s [%s] %s: Exception in %s.%s", timestamp, threadName, level, className, methodName, e.getMessage());
            logger.error(errMsg, e);
            throw e;
        }

        // 方法退出日志
        logMessage = String.format("[%s] %s [%s] %s: Exiting method %s - Result: %s", timestamp, threadName, level, className, methodName, result);
        switch (level.toUpperCase()) {
            case "TRACE":
                logger.trace(logMessage);
                break;
            case "DEBUG":
                logger.debug(logMessage);
                break;
            case "INFO":
                logger.info(logMessage);
                break;
            case "WARN":
                logger.warn(logMessage);
                break;
            case "ERROR":
                logger.error(logMessage);
                break;
            default:
                logger.info(logMessage);
                break;
        }

        return result;
    }
}
