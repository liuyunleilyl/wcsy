package com.example.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.common.restutils.ResultData;
import com.example.common.restutils.SuccessResultData;
import com.example.model.vo.*;
import com.example.service.TaskService;
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
@RequestMapping("/task")
@Api(tags = {"任务"})
@Validated
public class TaskController {
    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping("/taskList")
    @ApiOperation(value = "管理员-查看未完成的任务", notes = "管理员-查看未完成的任务")
    public ResultData<Page<TaskTVO>> taskList(){
        Page<TaskTVO> pageList =  taskService.taskList();
        return ResultData.success(pageList);
    }

    @PostMapping("/newTask")
    @ApiOperation(value = "管理员-新增任务", notes = "管理员-新增任务")
    public SuccessResultData newTask(@Validated @RequestBody NewTaskTVO newTaskTVO){
        taskService.newTask(newTaskTVO);
        return ResultData.success();
    }

    @PostMapping("/newTaskPlan")
    @ApiOperation(value = "管理员-新增任务计划",
            notes = "管理员-新增任务计划")
    public SuccessResultData newTaskPlan(@Validated @RequestBody NewTaskPlanVO newTaskPlanVO){
        taskService.newTaskPlan(newTaskPlanVO);
        return ResultData.success();
    }

    @GetMapping("/taskScheduleList")
    @ApiOperation(value = "管理员-查看某项任务下所有人的任务进度", notes = "管理员-查看某项任务下所有人的任务进度")
    public ResultData<Page<TaskScheduleTResVO>> taskScheduleList(@RequestParam(value = "taskId",required = true) String taskId){
        Page<TaskScheduleTResVO> pageList =  taskService.taskScheduleList(taskId);
        return ResultData.success(pageList);
    }

    @GetMapping("/editTaskPlanList")
    @ApiOperation(value = "管理员-修改任务计划列表", notes = "管理员-修改任务计划列表")
    public ResultData<Page<TaskPlanTListResVO>> editTaskPlanList(@RequestParam(value = "taskId",required = true) String taskId){
        Page<TaskPlanTListResVO> pageList =  taskService.editTaskPlanList(taskId);
        return ResultData.success(pageList);
    }

    @PostMapping("/editTaskPlanListOperation")
    @ApiOperation(value = "管理员-计划列表选中一条修改任务计划", notes = "管理员-计划列表选中一条修改任务计划")
    public SuccessResultData editTaskPlanListOperation(@RequestBody List<TaskPlanTVO> taskPlanTVOList){
        taskService.editTaskPlanListOperation(taskPlanTVOList);
        return ResultData.success();
    }
}
