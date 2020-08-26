package com.example.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.exceptionhandler.FailException;
import com.example.common.pagehelper.PageFactory;
import com.example.common.tool.ToolUtil;
import com.example.entity.TaskPlanT;
import com.example.entity.TaskScheduleT;
import com.example.entity.TaskT;
import com.example.entity.UserT;
import com.example.mapper.TaskPlanTMapper;
import com.example.mapper.TaskScheduleTMapper;
import com.example.mapper.TaskTMapper;
import com.example.mapper.UserTMapper;
import com.example.model.vo.TaskScheduleTResVO;
import com.example.model.vo.TaskScheduleTVO;
import com.example.service.TaskScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private TaskPlanTMapper taskPlanTMapper;
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
            List<TaskPlanT> planList = taskPlanTMapper.selectList(
                    new EntityWrapper<TaskPlanT>()
                            .eq("TASK_ID", taskId)
                            .eq("USER_CODE", userCode)
            );
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
            //赋值任务信息
            if (ToolUtil.isNotEmpty(planList)){
                TaskPlanT taskPlanT = planList.get(0);
                resVO.setDlfq(taskPlanT.getDlfq());
                resVO.setJcyName(taskPlanT.getJcyName());
            }
        }
        return resVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void newTaskSchedule(TaskScheduleTVO taskScheduleTVO) {
        log.error("taskScheduleTVO{}",taskScheduleTVO.toString());
        TaskScheduleT insertTaskScheduleT
                = new TaskScheduleT();
        if(ToolUtil.isEmpty(taskScheduleTVO)){
            throw new FailException("0001","请传入正确数据！");
        }
        ToolUtil.copyProperties(taskScheduleTVO,insertTaskScheduleT);
        //进度表id
        String taskScheduleId = insertTaskScheduleT.getTaskScheduleId();
        //判断任务完成标记
        String wcbj = getWcbj(insertTaskScheduleT);//完成标记
        if(ToolUtil.isEmpty(taskScheduleId)){//新增
            Integer count = this.baseMapper.selectCount(
                    new EntityWrapper<TaskScheduleT>()
                            .eq("TASK_ID", taskScheduleTVO.getTaskId())
                            .eq("USER_CODE", taskScheduleTVO.getUserCode())
            );
            if(count != 0){
                throw new FailException("0001","该任务进度已经存在，请勿重复添加！");
            }
            insertTaskScheduleT.setWcbj(wcbj);
            //插入进度表
            this.baseMapper.insert(insertTaskScheduleT);
        }else{//修改
            insertTaskScheduleT.setWcbj(wcbj);
            //修改进度表
            this.baseMapper.updateById(insertTaskScheduleT);
        }
        //任务进度完成之后，同时修改<该人和该任务>的任务计划的完成标记位置为1
        if("1".equals(wcbj) && ToolUtil.isNotEmpty(insertTaskScheduleT.getTaskId())
                    && ToolUtil.isNotEmpty(insertTaskScheduleT.getUserCode())){//任务进度已经完成
            TaskPlanT build = TaskPlanT.builder()
                    .wcbj("1")
                    .build();
            taskPlanTMapper.update(build,
                    new EntityWrapper<TaskPlanT>()
                    .eq("USER_CODE",insertTaskScheduleT.getUserCode())
                    .eq("TASK_ID",insertTaskScheduleT.getTaskId())
            );
        }
        /**
         * 任务进度和任务计划有出入，出来一项任务，必须把该任务的任务计划全部分配完才可以填写任务进度，
         * 完成一项任务进度，同时更新任务计划，等该任务的所有任务计划完毕后，才可以修改任务表的wcbj
         */
        Integer count = this.taskPlanTMapper.selectCount(
                new EntityWrapper<TaskPlanT>()
                        .eq("TASK_ID", insertTaskScheduleT.getTaskId())
                        .eq("WCBJ", "0")
        );
        if(count == 0){//该任务的所有人的任务计划都完了，更新任务表wcbj为1，已完成
            TaskT taskT = new TaskT();
            taskT.setWcbj("1");
            taskT.setTaskId(insertTaskScheduleT.getTaskId());
            taskTMapper.updateById(taskT);
        }
    }

    @Override
    public Page<TaskScheduleTResVO> unDoneAllSchedule(String userCode) {
        Page<TaskScheduleTResVO> page = PageFactory.defaultPage();
        List<TaskScheduleTResVO> list = this.baseMapper.unDoneAllSchedule(userCode,page);
        page.setRecords(list);
        return page;
    }

    private String getWcbj(TaskScheduleT insertTaskScheduleT){
        String cj = insertTaskScheduleT.getCj();//采集
        String hc = insertTaskScheduleT.getHc();//核查
        String bj = insertTaskScheduleT.getBj();//编辑
        String zj = insertTaskScheduleT.getZj();//质检
        String ec = insertTaskScheduleT.getEc();//二查
        String hk = insertTaskScheduleT.getHk();//合库
        String sj = insertTaskScheduleT.getSj();//上交
        if("100".equals(cj) && "100".equals(hc) &&
           "100".equals(bj) && "100".equals(zj) &&
           "100".equals(ec) && "100".equals(hk) &&
           "100".equals(sj)){//完成
            return "1";
        }
        return "0";//未完成
    }
}
