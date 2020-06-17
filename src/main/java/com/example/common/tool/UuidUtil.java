package com.example.common.tool;

import java.util.UUID;

/**
 * @Author: liuyl
 * @Date: 2020/6/16 11:39
 * @Version: 1.0
 * @Description:获取随机数工具类
 */
public class UuidUtil {
    public static final String getUuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
