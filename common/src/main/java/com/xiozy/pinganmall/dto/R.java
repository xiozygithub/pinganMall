package com.xiozy.pinganmall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@NoArgsConstructor
public class R<T> implements Serializable {


    private StatusCode code;
    private String message;
    private T data;



    public static <T> R<T> ok(){
        R<T> r = new R<>();
        r.setCode(StatusCode.SUCCESS);
        r.setMessage("success");
        return r;
    }

    public static <T> R<T> ok(StatusCode statusCode,String message,T data){
        R<T> r = new R<>();
        r.setCode(statusCode);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok(T data){
        R<T> r = new R<>();
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok(StatusCode statusCode,String message){
        R<T> r = new R<>();
        r.setCode(statusCode);
        r.setMessage(message);
        return r;
    }



    public static  R<Void> error(StatusCode statusCode,String message){
        R<Void> r = new R<>();
        r.setCode(statusCode);
        r.setMessage(message);
        return r;
    }

    public static  R<Void> error(){
        return error(StatusCode.INTERNAL_SERVER_ERROR,"未知异常，请联系管理员");
    }


    /**
     * 状态码
     * 6xx:用户相关错误码
     * 7xx:订单错误码
     * 8xx:业务错误码
     *
     */
    public enum StatusCode{

        SUCCESS(200,"success"),
        ERROR(400,"操作失败"),
        NOT_FOUND(404, "资源未找到"),
        INTERNAL_SERVER_ERROR(500, "服务器内部错误");


        private final int code;
        private final String message;

        StatusCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
