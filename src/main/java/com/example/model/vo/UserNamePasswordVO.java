package com.example.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: liuyl
 * @Date: 2020/6/9 13:04
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel("用户登陆VO")
public class UserNamePasswordVO {
    /**
     * 用户账号
     */
    @NotEmpty(message = "请输入账号！")
    @ApiModelProperty(value = "用户账号",example = "admin")
    private String userCode;
    /**
     * 用户密码
     */
    @NotEmpty(message = "请输入密码！")
    @ApiModelProperty(value = "用户密码",example = "admin")
    private String password;
}
