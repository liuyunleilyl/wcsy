package com.example.service;

import com.example.model.vo.UserNamePasswordVO;
import com.example.model.vo.UserTVO;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:
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
}
