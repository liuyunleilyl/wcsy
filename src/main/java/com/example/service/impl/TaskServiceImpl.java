package com.example.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.exceptionhandler.FailException;
import com.example.common.pagehelper.PageFactory;
import com.example.common.tool.ToolUtil;
import com.example.common.tool.UuidUtil;
import com.example.entity.TaskPlanT;
import com.example.entity.TaskT;
import com.example.mapper.TaskPlanTMapper;
import com.example.mapper.TaskTMapper;
import com.example.model.vo.*;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskTMapper, TaskT> implements TaskService {
    @Autowired
    private TaskPlanTMapper taskPlanTMapper;

    @Override
    public Page<TaskTVO> taskList(String taskName) {
        Page<TaskTVO> page = PageFactory.defaultPage();
        List<TaskTVO> res = new ArrayList();
        List<TaskT> list = new ArrayList<>();
        if(StringUtils.isEmpty(taskName)){
            list = this.baseMapper.selectPage(page,
                    new EntityWrapper<TaskT>()
                            .eq("WCBJ", "0")
            );
        }else {
            list = this.baseMapper.selectPage(page,
                    new EntityWrapper<TaskT>()
                            .eq("WCBJ", "0")
                            .like("TASK_NAME",taskName)
            );
        }
        for (TaskT taskT:list) {
            TaskTVO taskTVO = new TaskTVO();
            ToolUtil.copyProperties(taskT,taskTVO);
            res.add(taskTVO);
        }
        page.setRecords(res);
        return page;
    }

    @Override
    public void newTask(NewTaskTVO newTaskTVO) {
        Integer count = this.baseMapper.selectCount(
                new EntityWrapper<TaskT>()
                        .eq("TASK_YEAR", newTaskTVO.getTaskYear())
                        .eq("TASK_NAME", newTaskTVO.getTaskName())
        );
        if(count != 0){
            throw new FailException("0001","任务重复，请重新添加任务！");
        }
        TaskT taskT = TaskT.builder()
                .taskId(UuidUtil.getUuid())
                .wcbj("0")
                .taskYear(newTaskTVO.getTaskYear())
                .taskName(newTaskTVO.getTaskName())
                .build();
        this.baseMapper.insert(taskT);
    }

    @Override
    public void newTaskPlan(NewTaskPlanVO newTaskPlanVO) {
        TaskPlanT taskPlanT = new TaskPlanT();
        @NotEmpty(message = "任务年份必填！") String taskYear = newTaskPlanVO.getTaskYear();
        @NotEmpty(message = "任务内容必填") String taskName = newTaskPlanVO.getTaskName();
        //通过任务年份和任务内容查询唯一的taskid
        List<TaskT> taskTList = this.baseMapper.selectList(
                new EntityWrapper<TaskT>()
                        .eq("TASK_YEAR", newTaskPlanVO.getTaskYear())
                        .eq("TASK_NAME", newTaskPlanVO.getTaskName())
        );
        if(ToolUtil.isEmpty(taskTList)){
            throw new FailException("0001","请先添加该任务，再来填写对应的任务计划！");
        }
        //任务id
        String taskId = taskTList.get(0).getTaskId();
        ToolUtil.copyProperties(newTaskPlanVO,taskPlanT);
        taskPlanT.setTaskPlanId(UuidUtil.getUuid());//id
        taskPlanT.setTaskId(taskId);//任务id
        taskPlanT.setWcbj("0");//完成标记未完成
        taskPlanTMapper.insert(taskPlanT);
    }

    @Override
    public Page<TaskScheduleTResVO> taskScheduleList(String taskId) {
        Page<TaskScheduleTResVO> page = PageFactory.defaultPage();
        List<TaskScheduleTResVO> resList = this.baseMapper.taskScheduleList(page,taskId);
        page.setRecords(resList);
        return page;
    }

    @Override
    public Page<TaskPlanTListResVO> editTaskPlanList(String taskPlanId,String taskName) {
        Page<TaskPlanTListResVO> page = PageFactory.defaultPage();
        List<TaskPlanTListResVO> list = this.baseMapper.editTaskPlanList(page,taskPlanId,taskName);
        page.setRecords(list);
        return page;
    }

    @Override
    public void editTaskPlanListOperation(List<TaskPlanTVO> taskPlanTVOList) {
        if(ToolUtil.isEmpty(taskPlanTVOList)){
            throw new FailException("0001","请传入需要修改的数据！");
        }
        for (TaskPlanTVO taskPlanTVO: taskPlanTVOList) {
            TaskPlanT taskPlanT = new TaskPlanT();
            ToolUtil.copyProperties(taskPlanTVO,taskPlanT);
            taskPlanT.setWcbj("0");//新增/修改任务计划，永远是未完成；只有该任务进度完成后才会自动更新为已完成。
            taskPlanTMapper.updateById(taskPlanT);
        }
    }

    @Override
    public void invalidTask(List<String> taskIds) {
        this.baseMapper.deleteBatchIds(taskIds);
    }

    @Override
    public void invalidTaskPlan(List<String> taskPlanIds) {
        taskPlanTMapper.deleteBatchIds(taskPlanIds);
    }
}
