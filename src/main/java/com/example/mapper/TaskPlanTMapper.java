package com.example.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.entity.TaskPlanT;
import com.example.model.vo.TaskPlanTListResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskPlanTMapper extends BaseMapper<TaskPlanT> {
    /** 
     * @Author: liuyl
     * @Date: 2020/6/11 14:14
     * @Param: []
     * @Return: java.util.List<com.example.model.vo.TaskPlanTListResVO>
     * @Description: 查询自己未完成状态下的的任务计划
     */
    List<TaskPlanTListResVO> queryMineUnAchieveTaskPlan(@Param("page") Page<TaskPlanTListResVO> page
            ,@Param("userCode") String userCode);
}