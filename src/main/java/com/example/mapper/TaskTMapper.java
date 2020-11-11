package com.example.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.entity.TaskT;
import com.example.model.vo.TaskPlanTListResVO;
import com.example.model.vo.TaskScheduleTResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskTMapper extends BaseMapper<TaskT> {
    /**
     * @Author: liuyl
     * @Date: 2020/6/17 15:02
     * @Param: [page, taskId]
     * @Return: java.util.List<com.example.model.vo.TaskScheduleTResVO>
     * @Description: 管理员-查看某项任务下所有人的任务进度
     */
    List<TaskScheduleTResVO> taskScheduleList(@Param("page") Page<TaskScheduleTResVO> page, @Param("taskId") String taskId);

    /**
     * @Author: liuyl
     * @Date: 2020/6/17 15:11
     * @Param: [page, taskId]
     * @Return: java.util.List<com.example.model.vo.TaskPlanTListResVO>
     * @Description: 管理员-修改任务计划列表
     */
    List<TaskPlanTListResVO> editTaskPlanList(@Param("page") Page<TaskPlanTListResVO> page,
                                              @Param("taskPlanId") String taskPlanId,
                                              @Param("taskId") String taskId,
                                              @Param("taskName") String taskName);

    /**
     * @Author: liuyl
     * @Date: 2020/8/20 20:39
     * @Param: []
     * @Return: java.util.List<com.example.model.vo.TaskScheduleTResVO>
     * @Description: 管理员-根据某项任务计划查看对应某项的任务进度
     */
    List<TaskScheduleTResVO> queryTaskScheduleTS(@Param("userCode") String userCode,
                                                 @Param("taskId") String taskId);
}