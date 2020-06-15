package com.example.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.model.vo.TaskTVO;

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
}
