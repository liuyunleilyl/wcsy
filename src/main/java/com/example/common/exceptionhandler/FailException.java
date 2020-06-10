package com.example.common.exceptionhandler;

/**
 * 执行失败时的抛出
 *
 * @author liuyl
 */
public class FailException extends RuntimeException {
    private static final long serialVersionUID = -2843004454692943291L;
    String code;
    String message;
    Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public FailException(String message) {
        this.message = message;
    }

    public FailException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public FailException(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public FailException(String message, String code, String message1, Object data) {
        super(message);
        this.code = code;
        this.message = message1;
        this.data = data;
    }

    public FailException(String message, Throwable cause, String code, String message1, Object data) {
        super(message, cause);
        this.code = code;
        this.message = message1;
        this.data = data;
    }

    public FailException(Throwable cause, String code, String message, Object data) {
        super(cause);
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public FailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String message1, Object data) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
        this.data = data;
    }
}