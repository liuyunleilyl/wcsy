package com.example.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.model.vo.*;

import java.util.List;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:任务service
 */
public interface TaskService {

    /**
     * @Author: liuyl
     * @Date: 2020/6/15 20:58
     * @Param: []
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.example.model.vo.TaskTVO>
     * @Description: 管理员-查看未完成的计划
     */
    Page<TaskTVO> taskList();

    /** 
     * @Author: liuyl
     * @Date: 2020/6/16 11:15
     * @Param: [newTaskTVO]
     * @Return: void
     * @Description: 管理员-新增任务
     */
    void newTask(NewTaskTVO newTaskTVO);

    /** 
     * @Author: liuyl
     * @Date: 2020/6/17 14:21
     * @Param: [newTaskPlanVO]
     * @Return: void
     * @Description: 管理员-新增任务计划
     */
    void newTaskPlan(NewTaskPlanVO newTaskPlanVO);

    /**
     * @Author: liuyl
     * @Date: 2020/6/17 14:56
     * @Param: []
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.example.model.vo.TaskScheduleTResVO>
     * @Description: 管理员-查看某项任务下所有人的任务进度
     */
    Page<TaskScheduleTResVO> taskScheduleList(String taskId);

    /**
     * @Author: liuyl
     * @Date: 2020/6/17 15:10
     * @Param: [taskId]
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.example.model.vo.TaskPlanTListResVO>
     * @Description: 管理员-修改任务计划列表
     */
    Page<TaskPlanTListResVO> editTaskPlanList(String taskId);

    /** 
     * @Author: liuyl
     * @Date: 2020/6/17 15:29
     * @Param: [taskPlanTVOList]
     * @Return: void
     * @Description: 管理员-计划列表选中一条修改任务计划
     */
    void editTaskPlanListOperation(List<TaskPlanTVO> taskPlanTVOList);
}
