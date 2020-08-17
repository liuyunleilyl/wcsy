package com.example.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author liuly 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("新增用户专用VO")
public class NewUserTVO {
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private String userId;

    /**
     * 用户账号
     */
    @NotEmpty(message = "用户账号必填！")
    @ApiModelProperty("用户账号")
    private String userCode;

    /**
     * 真实姓名
     */
    @NotEmpty(message = "真实姓名必填！")
    @ApiModelProperty("真实姓名")
    private String userName;

    /**
     * 用户密码
     */
    @NotEmpty(message = "用户密码必填！")
    @ApiModelProperty("用户密码")
    private String password;

    /**
     * 用户角色
     */
    @ApiModelProperty("用户角色")
    @NotEmpty(message = "用户角色必填！")
    private String userRole;

    /*@ApiModelProperty("访问接口必须带着token实现登陆验证，token放到headers中")
    private String token;*/
}