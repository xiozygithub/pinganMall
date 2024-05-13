package com.xiozy.pinganmall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: ErrorInfo.java
 * @Description: 统一的错误信息
 * @Author: xiozy
 * @Date: 2024/5/12
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo<T> {
    public static final Integer ERROR = -1;//通用错误码

        private Integer code;//特定错误码
        private String message;
        private String url;
        private T data;


}
