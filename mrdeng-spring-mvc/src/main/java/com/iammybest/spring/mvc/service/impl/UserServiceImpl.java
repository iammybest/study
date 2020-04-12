package com.iammybest.spring.mvc.service.impl;

import com.iammybest.spring.mvc.dao.UserInfoMapper;
import com.iammybest.spring.mvc.model.UserInfo;
import com.iammybest.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MrDeng on 2017/3/14.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getById(Integer id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public UserInfo getByNickname(String nickname) {
        return userInfoMapper.selectByNickname(nickname);
    }
}
