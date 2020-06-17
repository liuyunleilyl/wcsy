package com.example.service;

import com.example.model.vo.UserNamePasswordVO;
import com.example.model.vo.UserTVO;
import com.example.model.vo.ValueAndLabelTemplate;

import java.util.List;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:用户service
 */
public interface UserTService {

    /**
     * @Author: liuyl
     * @Date: 2020/6/10 10:25
     * @Param: [userNamePasswordVO]
     * @Return: com.example.model.vo.UserTVO
     * @Description: 用户登陆
     */
    UserTVO login(UserNamePasswordVO userNamePasswordVO);

    /** 
     * @Author: liuyl
     * @Date: 2020/6/17 12:00
     * @Param: []
     * @Return: java.util.List<com.example.model.vo.ValueAndLabelTemplate>
     * @Description: 用户账号和用户姓名
     */
    List<ValueAndLabelTemplate> userCodeAndName();
}
