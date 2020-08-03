package com.example.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.exceptionhandler.FailException;
import com.example.common.redis.RedisUtils;
import com.example.common.tool.ToolUtil;
import com.example.common.tool.UuidUtil;
import com.example.entity.UserT;
import com.example.mapper.UserTMapper;
import com.example.model.vo.UserNamePasswordVO;
import com.example.model.vo.UserTVO;
import com.example.model.vo.ValueAndLabelTemplate;
import com.example.service.UserTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:
 */
@Service
public class UserTServiceImpl extends ServiceImpl<UserTMapper, UserT> implements UserTService {
    @Autowired
    private UserTMapper userTMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public UserTVO login(UserNamePasswordVO userNamePasswordVO) {
        UserTVO resVO = new UserTVO();
        @NotEmpty(message = "请输入账号！") String userCode = userNamePasswordVO.getUserCode();
        @NotEmpty(message = "请输入密码！") String password = userNamePasswordVO.getPassword();
        List<UserT> users = userTMapper.selectList(
                new EntityWrapper<UserT>()
                        .eq("USER_CODE", userCode)
        );
        if(ToolUtil.isEmpty(users) || ToolUtil.isEmpty(users.get(0))){
            throw new FailException("0001","账号不正确，请重新输入!");
        }
        //根据账号查询用户
        UserT userT = users.get(0);
        if(!password.equals(userT.getPassword())){
            throw new FailException("0001","密码不正确，请重新输入!");
        }
        ToolUtil.copyProperties(userT,resVO);
        /*//把token放入redis实现登陆验证
        String token = userCode+"-"+ UuidUtil.getUuid();
        redisUtils.set(token,token, Long.valueOf(30) , TimeUnit.MINUTES);
        resVO.setToken(token);*/
        return resVO;
    }

    @Override
    public List<ValueAndLabelTemplate> userCodeAndName() {
        List<ValueAndLabelTemplate> list = this.baseMapper.userCodeAndName();
        return list;
    }
}
