package com.example.controller;

import com.example.common.restutils.ResultData;
import com.example.model.vo.UserNamePasswordVO;
import com.example.model.vo.UserTVO;
import com.example.model.vo.ValueAndLabelTemplate;
import com.example.service.UserTService;
import io.swagger.annotations.Api;
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

    @PostMapping("/login1")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    public String initList1(){
        return "OK";
    }

    @PostMapping("/login2")
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    public String initList2(String a,String b){
        return a+","+b;
    }

    @GetMapping("/userCodeAndName")
    @ApiOperation(value = "查询用户账号和用户姓名下拉框", notes = "查询用户账号和用户姓名下拉框")
    public ResultData<List<ValueAndLabelTemplate>> userCodeAndName(){
        List<ValueAndLabelTemplate> list = userTService.userCodeAndName();
        return ResultData.success(list);
    }

}
