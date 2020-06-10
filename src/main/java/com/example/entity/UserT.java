package com.example.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author liuly 
 */
@TableName("user_t")
@Data
public class UserT {
    /**
     * 用户id
     */
    @TableId(value = "USER_ID", type = IdType.UUID)
    private String userId;

    /**
     * 用户账号
     */
    @TableField("USER_CODE")
    private String userCode;

    /**
     * 真实姓名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 用户密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 用户角色
     */
    @TableField("USER_ROLE")
    private String userRole;

    /**
     * 用户id
     * @return USER_ID 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 用户账号
     * @return USER_CODE 用户账号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 用户账号
     * @param userCode 用户账号
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 真实姓名
     * @return USER_NAME 真实姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 真实姓名
     * @param userName 真实姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户密码
     * @return PASSWORD 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 用户密码
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户角色
     * @return USER_ROLE 用户角色
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * 用户角色
     * @param userRole 用户角色
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}