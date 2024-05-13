package com.xiozy.pinganmall.advice;

import com.xiozy.pinganmall.dto.ErrorInfo;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title: GlobalExceptionHandler.java
 * @Description: 全局异常捕获
 * @Author: xiozy
 * @Date: 2024/5/12
 * @Version: 1.0
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    // Controller 中抛出异常，ControllerAdvice 就会捕获，定位到 ExceptionHandler 中处理
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ErrorInfo<String> errorHandler(HttpServletRequest request,Exception e){
        ErrorInfo<String> info = new ErrorInfo<>();

        info.setCode(ErrorInfo.ERROR);
        info.setMessage(e.getMessage());
        info.setData("Do Not Have Return Data");
        info.setUrl(request.getRequestURL().toString());

        return info;

    }

    /**
     * 处理自定义异常
     * @ExceptionHandler(value = UserNotFoundException.class)
     * public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
     *     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
     * }
     */
}
