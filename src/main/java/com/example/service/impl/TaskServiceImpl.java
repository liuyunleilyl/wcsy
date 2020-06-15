package com.example.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.pagehelper.PageFactory;
import com.example.common.tool.ToolUtil;
import com.example.entity.TaskT;
import com.example.mapper.TaskTMapper;
import com.example.model.vo.TaskTVO;
import com.example.service.TaskService;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<TaskTVO> taskList() {
        Page<TaskTVO> page = PageFactory.defaultPage();
        List<TaskTVO> res = new ArrayList();
        List<TaskT> list = this.baseMapper.selectPage(page,
                new EntityWrapper<TaskT>()
                        .eq("WCBJ", "0")
        );
        for (TaskT taskT:list) {
            TaskTVO taskTVO = new TaskTVO();
            ToolUtil.copyProperties(taskT,taskTVO);
            res.add(taskTVO);
        }
        page.setRecords(res);
        return page;
    }
}
