package com.example.common.config;

import com.example.common.interceptor.MineInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: liuyl
 * @Date: 2020/6/18 17:05
 * @Version: 1.0
 * @Description: 拦截器配置
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private MineInterceptor mineInterceptor;

    /**
     * @Author: liuyl
     * @Date: 2020/6/28 14:46
     * @Param: [registry]
     * @Return: void
     * @Description: 解决springboot配置了访问静态页面之后，访问不到swagger-ui.html（配置静态资源的，比如html，js，css，等等）
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /** 
     * @Author: liuyl
     * @Date: 2020/6/28 14:46
     * @Param: [registry]
     * @Return: void
     * @Description: 注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截器按照顺序执行
         *addPathPatterns 用于添加拦截规则
         *excludePathPatterns 用于排除拦截
         */
        registry.addInterceptor(mineInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/configuration/ui")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/configuration/security")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/error")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/**/favicon.ico").excludePathPatterns("/")
                .excludePathPatterns("/swagger-resources/configuration/ui")
                .excludePathPatterns("/swagger-resources/configuration/security")
                .excludePathPatterns("/user/login")
        ;
    }
}
