package com.example.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.exceptionhandler.FailException;
import com.example.common.pagehelper.PageFactory;
import com.example.common.tool.ToolUtil;
import com.example.common.tool.UuidUtil;
import com.example.entity.TaskPlanT;
import com.example.entity.TaskScheduleT;
import com.example.entity.TaskT;
import com.example.mapper.TaskPlanTMapper;
import com.example.mapper.TaskScheduleTMapper;
import com.example.mapper.TaskTMapper;
import com.example.model.vo.*;
import com.example.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    private TaskScheduleTMapper taskScheduleTMapper;

    @Override
    public Page<TaskTVO> taskList(String taskName) {
        Page<TaskTVO> page = PageFactory.defaultPage();
        List<TaskTVO> res = new ArrayList();
        List<TaskT> list = new ArrayList<>();
        if(StringUtils.isEmpty(taskName)){
            list = this.baseMapper.selectPage(page,
                    new EntityWrapper<TaskT>()
                            .eq("WCBJ", "0")
                    .orderDesc(Arrays.asList("TASK_NAME","TASK_YEAR"))
            );
        }else {
            list = this.baseMapper.selectPage(page,
                    new EntityWrapper<TaskT>()
                            .eq("WCBJ", "0")
                            .like("TASK_NAME",taskName)
                            .orderDesc(Arrays.asList("TASK_NAME","TASK_YEAR"))
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
        String taskPlanId = newTaskPlanVO.getTaskPlanId();
        @NotEmpty(message = "任务年份必填！") String taskYear = newTaskPlanVO.getTaskYear();
        @NotEmpty(message = "任务内容必填") String taskName = newTaskPlanVO.getTaskName();
        @NotEmpty(message = "作业员用户账号必填") String userCode = newTaskPlanVO.getUserCode();
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
        if(ToolUtil.isEmpty(taskPlanId)){//新增
            Integer count = taskPlanTMapper.selectCount(
                    new EntityWrapper<TaskPlanT>()
                            .eq("TASK_ID", taskId)
                            .eq("USER_CODE", userCode)
            );
            if(count != 0){
                throw new FailException("0001","对应的作业员名下已经有该任务计划，请勿重复添加！");
            }
            taskPlanT.setTaskPlanId(UuidUtil.getUuid());//id
            taskPlanT.setTaskId(taskId);//任务id
            taskPlanT.setWcbj("0");//完成标记未完成
            taskPlanTMapper.insert(taskPlanT);
        }else{//修改
            taskPlanT.setTaskId(taskId);//任务id
            taskPlanTMapper.updateById(taskPlanT);
        }
    }

    @Override
    public Page<TaskScheduleTResVO> taskScheduleList(String taskId) {
        Page<TaskScheduleTResVO> page = PageFactory.defaultPage();
        List<TaskScheduleTResVO> resList = this.baseMapper.taskScheduleList(page,taskId);
        page.setRecords(resList);
        return page;
    }

    @Override
    public Page<TaskPlanTListResVO> editTaskPlanList(String taskPlanId,String taskId,String taskName) {
        Page<TaskPlanTListResVO> page = PageFactory.defaultPage();
        List<TaskPlanTListResVO> list = this.baseMapper.editTaskPlanList(page,taskPlanId,taskId,taskName);
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
    @Transactional(rollbackFor = Exception.class)
    public void invalidTask(List<String> taskIds) {
        //删除任务
        this.baseMapper.deleteBatchIds(taskIds);
        /**
         * 删除相关任务的计划和进度
         */
        for (String taskId:taskIds) {
            //删除相关任务的计划
            taskPlanTMapper.delete(
                    new EntityWrapper<TaskPlanT>()
                            .eq("TASK_ID",taskId)
            );
            //删除相关任务的进度
            taskScheduleTMapper.delete(
                    new EntityWrapper<TaskScheduleT>()
                    .eq("TASK_ID",taskId)
            );
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invalidTaskPlan(List<String> taskPlanIds) {
        /**
         * 删除相关的进度
         */
        List<TaskPlanT> list = taskPlanTMapper.selectBatchIds(taskPlanIds);
        if(ToolUtil.isNotEmpty(list)){
            for (TaskPlanT taskPlanT:list) {
                String userCode = taskPlanT.getUserCode();
                String taskId = taskPlanT.getTaskId();
                if(ToolUtil.isNotEmpty(userCode) && ToolUtil.isNotEmpty(taskId)){
                    taskScheduleTMapper.delete(
                            new EntityWrapper<TaskScheduleT>()
                            .eq("USER_CODE",userCode)
                            .eq("TASK_ID",taskId)
                    );
                }
            }
        }
        //删除计划
        taskPlanTMapper.deleteBatchIds(taskPlanIds);
    }

    @Override
    public TaskScheduleTResVO taskScheduleListByTaskPlan(String taskPlanId) {
        TaskScheduleTResVO res = new TaskScheduleTResVO();
        TaskPlanT taskPlanT = taskPlanTMapper.selectById(taskPlanId);
        if(ToolUtil.isNotEmpty(taskPlanT)){
            String userCode = taskPlanT.getUserCode();
            String taskId = taskPlanT.getTaskId();
            if(ToolUtil.isNotEmpty(userCode) && ToolUtil.isNotEmpty(taskId)){
                List<TaskScheduleTResVO> resList = this.baseMapper.queryTaskScheduleTS(userCode,taskId);
                if(ToolUtil.isNotEmpty(resList)){
                    res = resList.get(0);
                }
            }
        }
        return res;
    }
}
