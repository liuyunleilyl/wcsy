package com.example.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.entity.UserT;
import com.example.model.vo.ValueAndLabelTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface UserTMapper  extends BaseMapper<UserT> {
    /**
     * @Author: liuyl
     * @Date: 2020/6/17 14:17
     * @Param: []
     * @Return: java.util.List<com.example.model.vo.ValueAndLabelTemplate>
     * @Description:查询 用户账号和用户姓名
     */
    List<ValueAndLabelTemplate> userCodeAndName(@RequestParam("userRole") String userRole
                                                ,@RequestParam("userCode") String userCode);
}