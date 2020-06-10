package com.example.common.restutils;

public class SuccessResultData extends ResultData {
    public SuccessResultData() {
        super(true, DEFAULT_SUCCESS_CODE, "请求成功", (Object)null);
    }

    public SuccessResultData(Object object) {
        super(true, DEFAULT_SUCCESS_CODE, "请求成功", object);
    }

    public SuccessResultData(Integer code, String message, Object object) {
        super(true, code, message, object);
    }
}
