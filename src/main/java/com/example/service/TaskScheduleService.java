package com.example.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.model.vo.TaskScheduleTResVO;
import com.example.model.vo.TaskScheduleTVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * @Author: liuyl
     * @Date: 2020/6/13 19:39
     * @Param: [userCode]
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.example.model.vo.TaskScheduleTResVO>
     * @Description: 作业员-进度公示-查看自己未完成的和自己相关的任务进度
     */
    Page<TaskScheduleTResVO> unDoneAllSchedule(String userCode);

    /**
     * @Author: liuyl
     * @Date: 2020/11/9 16:30
     * @Param: [userCode]
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.example.model.vo.TaskScheduleTResVO>
     * @Description: 管理员-进度公示-导出所有作业员未完成的和作业员自己相关的任务进度
     */
    void downloadUnDoneAllSchedule(String userCode,String taskId, HttpServletRequest request, HttpServletResponse response);
}
