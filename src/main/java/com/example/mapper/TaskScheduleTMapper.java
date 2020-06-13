package com.example.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.entity.TaskScheduleT;
import com.example.model.vo.TaskScheduleTResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaskScheduleTMapper extends BaseMapper<TaskScheduleT> {

    /**
     * @Author: liuyl
     * @Date: 2020/6/12 19:09
     * @Param: [userCode：用户账号, taskId：任务id]
     * @Return: java.util.List<com.example.entity.TaskScheduleT>
     * @Description: 作业员-我的工作-根据某一项任务计划添加该任务的任务进度页面-初始化
     */
    List<TaskScheduleTResVO> newTaskScheduleInit(@Param("userCode") String userCode,@Param("taskId")String taskId);
}