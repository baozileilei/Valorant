package com.yedean.valorant.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yedean.valorant.constant.ApiMsgConstant;

public class ResponseVo<T> {

    private int code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    private T data;

    private ResponseVo(int code,String description, T data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public static <T> ResponseVo<T> ok(T data) {
        return new ResponseVo<>(0, ApiMsgConstant.SUCCESS, data);
    }
    public static <T> ResponseVo<T> ok(){
        return new ResponseVo<>(0,ApiMsgConstant.SUCCESS,null);
    }
    public static <T> ResponseVo<T> fail(int errorCode, String errorMessage) {
        return new ResponseVo<>(errorCode,errorMessage, null);
    }
    public static <T> ResponseVo<T> failAddData(int errorCode, String errorMessage, T data) {
        return new ResponseVo<>(errorCode, errorMessage, data);
    }
    public static <T> ResponseVo<T> validationFail(int errorCode, String errorMessage) {
        return new ResponseVo<>(errorCode, errorMessage, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
