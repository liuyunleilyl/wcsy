package com.example.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.exceptionhandler.FailException;
import com.example.common.pagehelper.PageFactory;
import com.example.common.tool.ToolUtil;
import com.example.entity.TaskPlanT;
import com.example.entity.UserT;
import com.example.mapper.TaskPlanTMapper;
import com.example.mapper.UserTMapper;
import com.example.model.vo.TaskPlanTListResVO;
import com.example.model.vo.TaskPlanTVO;
import com.example.model.vo.UserNamePasswordVO;
import com.example.model.vo.UserTVO;
import com.example.service.TaskPlanService;
import com.example.service.UserTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class TaskPlanServiceImpl extends ServiceImpl<TaskPlanTMapper, TaskPlanT> implements TaskPlanService {
    @Autowired
    private TaskPlanTMapper taskPlanTMapper;

    @Override
    public Page<TaskPlanTListResVO> taskPlanList(String userCode) {
        Page<TaskPlanTListResVO> page = PageFactory.defaultPage();
        List<TaskPlanTListResVO> resList = taskPlanTMapper.queryMineUnAchieveTaskPlan(page,userCode);
        page.setRecords(resList);
        return page;
    }
}
