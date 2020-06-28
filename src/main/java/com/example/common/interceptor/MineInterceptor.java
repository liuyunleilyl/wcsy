package com.example.common.interceptor;

import com.example.common.exceptionhandler.FailException;
import com.example.common.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: liuyl
 * @Date: 2020/6/28 14:31
 * @Version: 1.0
 * @Description: 自定义拦截器
 */
@Slf4j
@Component
public class MineInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        log.info("requestURI=="+requestURI);
        return true;
    }
}
