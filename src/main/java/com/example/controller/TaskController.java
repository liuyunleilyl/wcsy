package com.example.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.common.restutils.ResultData;
import com.example.common.restutils.SuccessResultData;
import com.example.model.vo.*;
import com.example.service.TaskService;
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
@RequestMapping("/task")
@Api(tags = {"任务"})
@Validated
public class TaskController {
    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping("/taskList")
    @ApiOperation(value = "管理员-查看未完成的任务", notes = "管理员-查看未完成的任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskName", value = "查询条件-任务名称", required = false, dataType = "query",
                    example = "马鞍山市地理国情监测", paramType = "query")
    })
    public ResultData<Page<TaskTVO>> taskList(@RequestParam(value = "taskName",required = false) String taskName){
        Page<TaskTVO> pageList =  taskService.taskList(taskName);
        return ResultData.success(pageList);
    }

    @PostMapping("/newTask")
    @ApiOperation(value = "管理员-新增任务", notes = "管理员-新增任务")
    public SuccessResultData newTask(@Validated @RequestBody NewTaskTVO newTaskTVO){
        taskService.newTask(newTaskTVO);
        return ResultData.success();
    }

    @GetMapping("/invalidTask")
    @ApiOperation(value = "管理员-删除任务", notes = "管理员-删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskIds", value = "任务id数组", required = true, dataType = "query",
                    example = "123", paramType = "query")
    })
    public SuccessResultData invalidTask(@RequestParam(value = "taskIds",required = true) List<String> taskIds){
        taskService.invalidTask(taskIds);
        return SuccessResultData.success();
    }

    @GetMapping("/editTaskPlanList")
    @ApiOperation(value = "管理员-任务计划列表", notes = "管理员-任务计划列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskPlanId", value = "详情使用-任务表id", required = false, dataType = "String",
                    example = "1", paramType = "query"),
            @ApiImplicitParam(name = "taskName", value = "查询条件-任务名称", required = false, dataType = "String",
                    example = "马鞍山市地理国情监测", paramType = "query")
    })
    public ResultData<Page<TaskPlanTListResVO>> editTaskPlanList(@RequestParam(value = "taskPlanId",required = false) String taskPlanId,
                                                                 @RequestParam(value = "taskName",required = false) String taskName){
        Page<TaskPlanTListResVO> pageList =  taskService.editTaskPlanList(taskPlanId,taskName);
        return ResultData.success(pageList);
    }

    @PostMapping("/newTaskPlan")
    @ApiOperation(value = "管理员-新增任务计划",
            notes = "管理员-新增任务计划")
    public SuccessResultData newTaskPlan(@Validated @RequestBody NewTaskPlanVO newTaskPlanVO){
        taskService.newTaskPlan(newTaskPlanVO);
        return ResultData.success();
    }

    @GetMapping("/invalidTaskPlan")
    @ApiOperation(value = "管理员-删除任务计划", notes = "管理员-删除任务计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskPlanIds", value = "任务计划id数组", required = true, dataType = "query",
                    example = "123", paramType = "query")
    })
    public SuccessResultData invalidTaskPlan(@RequestParam(value = "taskPlanIds",required = true) List<String> taskPlanIds){
        taskService.invalidTaskPlan(taskPlanIds);
        return SuccessResultData.success();
    }

    @PostMapping("/editTaskPlanListOperation")
    @ApiOperation(value = "管理员-计划列表选中一条修改任务计划", notes = "管理员-计划列表选中一条修改任务计划")
    public SuccessResultData editTaskPlanListOperation(@RequestBody List<TaskPlanTVO> taskPlanTVOList){
        taskService.editTaskPlanListOperation(taskPlanTVOList);
        return ResultData.success();
    }

    @GetMapping("/taskScheduleList")
    @ApiOperation(value = "管理员-查看某项任务下所有人的任务进度", notes = "管理员-查看某项任务下所有人的任务进度")
    public ResultData<Page<TaskScheduleTResVO>> taskScheduleList(@RequestParam(value = "taskId",required = true) String taskId){
        Page<TaskScheduleTResVO> pageList =  taskService.taskScheduleList(taskId);
        return ResultData.success(pageList);
    }

}
