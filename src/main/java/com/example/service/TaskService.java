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
    Page<TaskTVO> taskList(String taskName);

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
    Page<TaskPlanTListResVO> editTaskPlanList(String taskPlanId,String taskId,String taskName);

    /** 
     * @Author: liuyl
     * @Date: 2020/6/17 15:29
     * @Param: [taskPlanTVOList]
     * @Return: void
     * @Description: 管理员-计划列表选中一条修改任务计划
     */
    void editTaskPlanListOperation(List<TaskPlanTVO> taskPlanTVOList);

    /** 
     * @Author: liuyl
     * @Date: 2020/8/17 13:53
     * @Param: [taskIds]：数组
     * @Return: void
     * @Description: 管理员-删除任务
     */
    void invalidTask(List<String> taskIds);

    /**
     * @Author: liuyl
     * @Date: 2020/8/17 14:01
     * @Param: [taskPlanIds]
     * @Return: void
     * @Description: 管理员-删除任务计划
     */
    void invalidTaskPlan(List<String> taskPlanIds);

    /**
     * @Author: liuyl
     * @Date: 2020/8/20 20:27
     * @Param: [taskPlanId]
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.example.model.vo.TaskScheduleTResVO>
     * @Description: 管理员-根据某项任务计划查看对应某项的任务进度
     */
    TaskScheduleTResVO taskScheduleListByTaskPlan(String taskPlanId);
}
