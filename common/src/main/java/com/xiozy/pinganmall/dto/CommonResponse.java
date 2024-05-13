package com.xiozy.pinganmall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> implements Serializable {

    //响应状态码
    private  Integer code;

    private String message;

    private T data;

}
