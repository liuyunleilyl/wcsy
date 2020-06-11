package com.example.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.entity.TaskPlanT;
import com.example.model.vo.TaskPlanTVO;

import java.util.List;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:任务计划service
 */
public interface TaskPlanService {

    /** 
     * @Author: liuyl
     * @Date: 2020/6/10 19:59
     * @Param: [userCode]
     * @Return: com.baomidou.mybatisplus.plugins.Page<com.example.model.vo.TaskPlanTVO>
     * @Description: 作业员-我的工作-查看自己的任务计划
     */
    Page<TaskPlanT> taskPlanList(String userCode);
}
