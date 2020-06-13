package com.example.service;

import com.example.model.vo.TaskScheduleTResVO;
import com.example.model.vo.TaskScheduleTVO;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:任务进度service
 */
public interface TaskScheduleService {

    /**
     * @Author: liuyl
     * @Date: 2020/6/12 18:58
     * @Param: [userCode, taskId]
     * @Return: com.example.model.vo.TaskScheduleTVO
     * @Description: 作业员-我的工作-根据某一项任务计划添加该任务的任务进度页面-初始化
     */
    TaskScheduleTResVO newTaskScheduleInit(String userCode, String taskId);

    /**
     * @Author: liuyl
     * @Date: 2020/6/13 14:57
     * @Param: [taskScheduleTVO]
     * @Return: void
     * @Description: 作业员-我的工作-根据某一项任务计划添加该任务的任务进度页面-保存
     */
    void newTaskSchedule(TaskScheduleTVO taskScheduleTVO);
}
