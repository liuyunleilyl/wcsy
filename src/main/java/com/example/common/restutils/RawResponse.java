package com.example.common.restutils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.common.exceptionhandler.FailException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** 
 * @Author: liuyl
 * @Date: 2020/6/10 10:46
 * @Param:
 * @Return: 
 * @Description: 返回报文格式
 */
public class RawResponse {
    String code;
    String message;
    JSONObject data;
    Map map;

    public RawResponse(String code, String message) {
        this(code, message, new JSONObject());
    }

    public RawResponse(String code, String message, JSONObject jo) {
        setCode(code);
        setMessage(message);
        setData(jo);
    }
    public RawResponse(String code, String message, JSONObject jo, Map map) {
        setCode(code);
        setMessage(message);
        setData(jo);
        setMap(map);
    }
    public RawResponse(JSONObject jo) {
        this("0000", "请求成功", jo);
    }

    public RawResponse(Object jo) {
        this("0000","请求成功",jo);
    }
    public RawResponse(String code, String message, Object jo) {
        setCode(code);
        setMessage(message);
        if (jo instanceof JSONArray || jo instanceof ArrayList || jo instanceof Array) {
            Map map = new HashMap();
            map.put("list", jo);
            setData(JSONObject.parseObject(JSONObject.toJSONString(map,SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteNullNumberAsZero,
                    SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullBooleanAsFalse)));
        } else {
            setData(JSONObject.parseObject(JSONObject.toJSONString(jo,SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteNullNumberAsZero,
                    SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullBooleanAsFalse)));
        }
    }


    public String ReturnMsg() {
        JSONObject jo = new JSONObject();
        jo.put("code", getCode());
        jo.put("message", getMessage());
        jo.put("data", getData());
        return jo.toJSONString();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public static RawResponse copy(FailException failException) {
        return new RawResponse(failException.getCode(), failException.getMessage(), failException.getData());
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

}
