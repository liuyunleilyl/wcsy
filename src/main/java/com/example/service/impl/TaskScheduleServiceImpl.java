package com.example.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.tool.ToolUtil;
import com.example.entity.TaskScheduleT;
import com.example.entity.TaskT;
import com.example.entity.UserT;
import com.example.mapper.TaskScheduleTMapper;
import com.example.mapper.TaskTMapper;
import com.example.mapper.UserTMapper;
import com.example.model.vo.TaskScheduleTResVO;
import com.example.model.vo.TaskScheduleTVO;
import com.example.service.TaskScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class TaskScheduleServiceImpl extends ServiceImpl<TaskScheduleTMapper, TaskScheduleT> implements TaskScheduleService {
    @Autowired
    private UserTMapper userTMapper;
    @Autowired
    private TaskTMapper taskTMapper;

    @Override
    public TaskScheduleTResVO newTaskScheduleInit(String userCode, String taskId) {
        TaskScheduleTResVO resVO = new TaskScheduleTResVO();
        List<TaskScheduleTResVO> taskScheduleTS = this.baseMapper.newTaskScheduleInit(userCode,taskId);
        if(ToolUtil.isNotEmpty(taskScheduleTS)){
            resVO = taskScheduleTS.get(0);
        }else{//赋予默认值
            resVO = TaskScheduleTResVO.builder()
                    .userCode(userCode)
                    .taskId(taskId)
                    .build();
            List<UserT> users = userTMapper.selectList(
                    new EntityWrapper<UserT>()
                            .eq("USER_CODE", userCode)
            );
            TaskT taskT = taskTMapper.selectById(taskId);
            //赋值用户信息
            if(ToolUtil.isNotEmpty(users) && ToolUtil.isNotEmpty(users.get(0))){
                UserT userT = users.get(0);
                resVO.setUserName(userT.getUserName());
            }
            //赋值任务信息
            if(ToolUtil.isNotEmpty(taskT)){
                resVO.setTaskYear(taskT.getTaskYear());
                resVO.setTaskName(taskT.getTaskName());
            }
        }
        return resVO;
    }
}
