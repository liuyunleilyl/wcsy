package com.example.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
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
    List<TaskScheduleTResVO> newTaskScheduleInit(@Param("userCode") String userCode,@Param("taskId")String taskId,
                                                 @Param("dlfq") String dlfq);

    /**
     * @Author: liuyl
     * @Date: 2020/6/13 19:59
     * @Param: [userCode]
     * @Return: java.util.List<com.example.model.vo.TaskScheduleTResVO>
     * @Description: 作业员-进度公示-查看自己未完成的和自己相关的任务进度
     */
    List<TaskScheduleTResVO> unDoneAllSchedule(@Param("userCode") String userCode, Page<TaskScheduleTResVO> page);

    /**
     * @Author: liuyl
     * @Date: 2020/11/9 16:30
     * @Param: [userCode]
     * @Return: java.util.List<com.example.model.vo.TaskScheduleTResVO>
     * @Description: 管理员-进度公示-导出所有作业员未完成的和作业员自己相关的任务进度
     */
    List<TaskScheduleTResVO> uploadUnDoneAllSchedule(@Param("userCode") String userCode,@Param("taskId") String taskId);
}