package com.example.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.exceptionhandler.FailException;
import com.example.common.pagehelper.PageFactory;
import com.example.common.redis.RedisUtils;
import com.example.common.tool.ToolUtil;
import com.example.common.tool.UuidUtil;
import com.example.entity.UserT;
import com.example.mapper.UserTMapper;
import com.example.model.vo.NewUserTVO;
import com.example.model.vo.UserNamePasswordVO;
import com.example.model.vo.UserTVO;
import com.example.model.vo.ValueAndLabelTemplate;
import com.example.service.UserTService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.tools.Tool;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
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
    public List<ValueAndLabelTemplate> userCodeAndName(String userRole,String userCode) {
        List<ValueAndLabelTemplate> list = this.baseMapper.userCodeAndName(userRole,userCode);
        return list;
    }

    @Override
    public Page<UserTVO> userList(String userId,String userName,String userRole) {
        Page<UserTVO> page = PageFactory.defaultPage();
        //由于前端做的数据分页，默认每页查询出来所有数据
        page.setSize(1000000);
        List<UserTVO> list = new ArrayList<>();
        EntityWrapper<UserT> ew = new EntityWrapper<>();
        if(!StringUtils.isEmpty(userName)){
            ew.like("USER_NAME",userName);
        }
        if(!StringUtils.isEmpty(userRole)){
            ew.like("USER_ROLE",userRole);
        }
        if(!StringUtils.isEmpty(userRole)){
            ew.eq("USER_ID",userId);
        }
        List<UserT> userTS = this.baseMapper.selectPage(page,ew);
        for (UserT userT:userTS) {
            UserTVO res = new UserTVO();
            BeanUtils.copyProperties(userT,res);
            list.add(res);
        }
        page.setRecords(list);
        return page;
    }

    @Override
    public void newUser(NewUserTVO newUserTVO) {
        @NotEmpty(message = "用户账号必填！") String userCode = newUserTVO.getUserCode();
        @NotEmpty(message = "真实姓名必填！") String userName = newUserTVO.getUserName();
        List<UserT> userCodeList = this.baseMapper.selectList(new EntityWrapper<UserT>().eq("USER_CODE", userCode));
        List<UserT> userNameList = this.baseMapper.selectList(new EntityWrapper<UserT>().eq("USER_NAME", userName));
        if(ToolUtil.isNotEmpty(userCodeList)){
            throw new FailException("0001","用户账号已经存在，请重新注册！");
        }
        if(ToolUtil.isNotEmpty(userNameList)) throw new FailException("0001", "真实姓名已经存在，请重新注册！");
        String userId = newUserTVO.getUserId();
        UserT userT = new UserT();
        BeanUtils.copyProperties(newUserTVO,userT);
        if(ToolUtil.isEmpty(userId)){//新增
            this.baseMapper.insert(userT);
        }else{//修改
            this.baseMapper.updateById(userT);
        }

    }

    @Override
    public void invalidUser(List<String> userIds) {
        this.baseMapper.deleteBatchIds(userIds);
    }
}
