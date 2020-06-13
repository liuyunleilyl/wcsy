package com.example.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.common.restutils.ResultData;
import com.example.model.vo.TaskPlanTListResVO;
import com.example.model.vo.TaskScheduleTResVO;
import com.example.model.vo.TaskScheduleTVO;
import com.example.service.TaskScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:20
 * @Version: 1.0
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/taskSchedule")
@Api(tags = {"任务进度"})
@Validated
public class TaskScheduleController {
    private final static Logger logger = LoggerFactory.getLogger(TaskScheduleController.class);

    @Autowired
    private TaskScheduleService taskScheduleService;

    @GetMapping("/newTaskScheduleInit")
    @ApiOperation(value = "作业员-我的工作-根据某一项任务计划添加该任务的任务进度页面-初始化",
            notes = "作业员-我的工作-根据某一项任务计划添加该任务的任务进度页面-初始化")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户账号", required = true, dataType = "String",
                    example = "liuyl", paramType = "query"),
            @ApiImplicitParam(name = "taskId", value = "任务id", required = true, dataType = "String",
                    example = "1", paramType = "query")
    })
    public ResultData<TaskScheduleTResVO> newTaskScheduleInit(
            @NotEmpty(message = "用户账号不能为空") @RequestParam(value = "userCode",required = true)String userCode,
            @NotEmpty(message = "任务id不能为空") @RequestParam(value = "taskId",required = true)String taskId){
        TaskScheduleTResVO taskScheduleTResVO =  taskScheduleService.newTaskScheduleInit(userCode,taskId);
        return ResultData.success(taskScheduleTResVO);
    }


}