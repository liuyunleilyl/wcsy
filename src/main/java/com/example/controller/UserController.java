package com.example.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.common.restutils.ResultData;
import com.example.common.restutils.SuccessResultData;
import com.example.model.vo.*;
import com.example.service.UserTService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:20
 * @Version: 1.0
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = {"用户信息"})
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserTService userTService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    public ResultData<UserTVO> initList(@RequestBody @Validated UserNamePasswordVO userNamePasswordVO){
        UserTVO userTVO = userTService.login(userNamePasswordVO);
        return ResultData.success(userTVO);
    }


    @GetMapping("/userCodeAndName")
    @ApiOperation(value = "查询用户账号和用户姓名下拉框", notes = "查询用户账号和用户姓名下拉框")
    public ResultData<List<ValueAndLabelTemplate>> userCodeAndName(){
        List<ValueAndLabelTemplate> list = userTService.userCodeAndName();
        return ResultData.success(list);
    }

    @GetMapping("/userList")
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "详情-用户id", required = false, dataType = "query",
                    example = "2", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "查询条件-用户名称", required = false, dataType = "query",
                    example = "liuyl", paramType = "query"),
            @ApiImplicitParam(name = "userRole", value = "查询条件-用户角色", required = false, dataType = "query",
                    example = "作业员", paramType = "query")
    })
    public ResultData<Page<UserTVO>> userList(@RequestParam(value = "userId",required = false) String userId,
                                              @RequestParam(value = "userName",required = false) String userName,
                                              @RequestParam(value = "userRole",required = false) String userRole){
        Page<UserTVO> list = userTService.userList(userId,userName,userRole);
        return ResultData.success(list);
    }

    @PostMapping("/newUser")
    @ApiOperation(value = "新增/修改用户", notes = "新增/修改用户")
    public SuccessResultData newUser(@Validated @RequestBody NewUserTVO newUserTVO){
        userTService.newUser(newUserTVO);
        return ResultData.success();
    }

    @GetMapping("/invalidUser")
    @ApiOperation(value = "管理员-删除用户", notes = "管理员-删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id数组", required = true, dataType = "query",
                    example = "123", paramType = "query")
    })
    public SuccessResultData invalidUser(@RequestParam(value = "taskIds",required = true) List<String> userIds){
        userTService.invalidUser(userIds);
        return SuccessResultData.success();
    }
}
