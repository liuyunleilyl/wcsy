package com.example.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.common.restutils.ResultData;
import com.example.model.vo.TaskPlanTListResVO;
import com.example.model.vo.TaskTVO;
import com.example.service.TaskPlanService;
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
@RequestMapping("/task")
@Api(tags = {"任务"})
@Validated
public class TaskController {
    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping("/taskList")
    @ApiOperation(value = "管理员-查看未完成的计划", notes = "管理员-查看未完成的计划")
    public ResultData<Page<TaskTVO>> taskList(){
        Page<TaskTVO> pageList =  taskService.taskList();
        return ResultData.success(pageList);
    }

    
}
