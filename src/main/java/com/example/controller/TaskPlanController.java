package com.example.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.common.restutils.ResultData;
import com.example.entity.TaskPlanT;
import com.example.model.vo.TaskPlanTListResVO;
import com.example.model.vo.TaskPlanTVO;
import com.example.model.vo.UserNamePasswordVO;
import com.example.model.vo.UserTVO;
import com.example.service.TaskPlanService;
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

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:20
 * @Version: 1.0
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/taskPlan")
@Api(tags = {"任务计划"})
@Validated
public class TaskPlanController {
    private final static Logger logger = LoggerFactory.getLogger(TaskPlanController.class);

    @Autowired
    private TaskPlanService taskPlanList;

    @GetMapping("/taskPlanList")
    @ApiOperation(value = "作业员-我的工作-查看自己的未完成的任务计划", notes = "作业员-我的工作-查看自己的未完成的任务计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户账号", required = true, dataType = "String",
                    example = "liuyl", paramType = "query")
    })
    public ResultData<Page<TaskPlanTListResVO>> taskPlanList(
            @NotEmpty(message = "用户账号不能为空") @RequestParam(value = "userCode",required = true)String userCode){
        Page<TaskPlanTListResVO> pageList =  taskPlanList.taskPlanList(userCode);
        return ResultData.success(pageList);
    }


}
