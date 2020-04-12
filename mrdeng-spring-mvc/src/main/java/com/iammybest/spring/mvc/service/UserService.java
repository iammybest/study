package com.iammybest.spring.mvc.service;


import com.iammybest.spring.mvc.model.UserInfo;

/**
 * Created by MrDeng on 2017/3/14.
 */
public interface UserService {
     public UserInfo getById(Integer id);

    public UserInfo getByNickname(String nickname);
}
