package com.example.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 11:12
 * @Version: 1.0
 * @Description: 配置访问swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * 屏蔽部分的spring-boot自带的接口
     *
     * @return
     */
    @Bean

    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*"))).paths(Predicates.not(PathSelectors.regex("/info.*")))
                .paths(Predicates.not(PathSelectors.regex("/autoconfig.*")))
                .paths(Predicates.not(PathSelectors.regex("/health.*")))
                .paths(Predicates.not(PathSelectors.regex("/metrics.*")))
                .paths(Predicates.not(PathSelectors.regex("/mappings.*")))
                .paths(Predicates.not(PathSelectors.regex("/trace.*")))
                .paths(Predicates.not(PathSelectors.regex("/configprops.*")))
                .paths(Predicates.not(PathSelectors.regex("/beans.*"))).paths(Predicates.not(PathSelectors.regex("/env.*")))
                .paths(Predicates.not(PathSelectors.regex("/dump.*")))
                .paths(Predicates.not(PathSelectors.regex("/auditevents.*")))
                .paths(Predicates.not(PathSelectors.regex("/docs.*"))).paths(Predicates.not(PathSelectors.regex("/archaius.*")))
                .paths(Predicates.not(PathSelectors.regex("/features.*")))
                .paths(Predicates.not(PathSelectors.regex("/pause.*"))).paths(Predicates.not(PathSelectors.regex("/refresh.*")))
                .paths(Predicates.not(PathSelectors.regex("/resume.*")))
                .paths(Predicates.not(PathSelectors.regex("/actuator.*")))
                .paths(Predicates.not(PathSelectors.regex("/jolokia.*")))
                .paths(Predicates.not(PathSelectors.regex("/loggers.*")))
                .paths(Predicates.not(PathSelectors.regex("/restart.*")))
                .paths(Predicates.not(PathSelectors.regex("/service-registry/instance-status.*")))
                .paths(Predicates.not(PathSelectors.regex("/logfile.*")))
                .paths(Predicates.not(PathSelectors.regex("/channels.*"))).paths(Predicates.not(PathSelectors.regex("/bus.*")))
                .paths(Predicates.not(PathSelectors.regex("/heapdump.*"))).build();
    }

    /**
     * Swagger2主界面信息,描述api的基本信息用于展示
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Restful服务接口定义和规范").description("Restful服务接口定义和规范")
                .termsOfServiceUrl("http://****.com/").contact(new Contact("liuyl", "", "")).version("1.0")
                .license("版权所有 ©***仿冒必究").build();
    }
}
