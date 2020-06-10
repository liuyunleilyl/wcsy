package com.example.common.restutils;

import java.util.Date;

public class ErrorResultData extends ResultData {
    private Date timestamp;
    private String path;
    private String exceptionClazz;

    public ErrorResultData(String message) {
        super(false, ResultData.DEFAULT_ERROR_CODE, message, (Object)null);
    }

    public ErrorResultData(Integer code, String message) {
        super(false, code, message, (Object)null);
    }

    public ErrorResultData(Class<? extends Throwable> _class, Integer code, String message) {
        super(false, code, message, (Object)null);
        this.exceptionClazz = _class.getName();
    }

    public ErrorResultData(Integer code, String message, Date timestamp, String path) {
        super(false, code, message, (Object)null);
        this.timestamp = timestamp;
        this.path = path;
    }

    public ErrorResultData(Class<? extends Throwable> _class, Integer code, String message, Date timestamp, String path) {
        super(false, code, message, (Object)null);
        this.exceptionClazz = _class.getName();
        this.timestamp = timestamp;
        this.path = path;
    }

    public ErrorResultData(Integer code, String message, Object object) {
        super(false, code, message, object);
    }

    public ErrorResultData(Class<? extends Exception> _class, Integer code, String message, Object object) {
        super(false, code, message, object);
        this.exceptionClazz = _class.getName();
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExceptionClazz() {
        return this.exceptionClazz;
    }

    public void setExceptionClazz(String exceptionClazz) {
        this.exceptionClazz = exceptionClazz;
    }
}
