package com.example.common.interceptor;

import com.example.common.exceptionhandler.FailException;
import com.example.common.redis.RedisUtils;
import com.example.common.tool.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;

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
        log.info("requestURI="+requestURI);
        String token = request.getHeader("token");
        if(ToolUtil.isEmpty(token)){
            token = request.getParameter("token");
        }
        if(ToolUtil.isEmpty(token)){
            throw new FailException("请先登陆，再来访问！");
        }
        if(!redisUtils.exists(token)){
            throw new FailException("登陆超时，请重新登陆！");
        }
        return true;
    }
}
