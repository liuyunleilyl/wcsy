package com.example.common.restutils;

/**
 * @ClassName ResponseDataQi
 * @Description 报文返回消息
 * @Author liuyl
 * @Date 2020/1/17 9:57
 * @Version 1.0
 */
public class ResultData<T> {
    public static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";
    public static final String DEFAULT_ERROR_MESSAGE = "系统异常";
    public static final Integer DEFAULT_SUCCESS_CODE = Integer.valueOf(200);
    public static final Integer DEFAULT_ERROR_CODE = Integer.valueOf(500);
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    public ResultData() {
    }

    public ResultData(Boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static SuccessResultData success() {
        return new SuccessResultData();
    }

    public static SuccessResultData success(Object object) {
        return new SuccessResultData(object);
    }

    public static SuccessResultData success(Integer code, String message, Object object) {
        return new SuccessResultData(code, message, object);
    }

    public static ErrorResultData error(String message) {
        return new ErrorResultData(message);
    }

    public static ErrorResultData error(Integer code, String message) {
        return new ErrorResultData(code, message);
    }

    public static ErrorResultData error(Integer code, String message, Object object) {
        return new ErrorResultData(code, message, object);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
