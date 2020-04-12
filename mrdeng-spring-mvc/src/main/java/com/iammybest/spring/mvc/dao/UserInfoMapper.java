package com.iammybest.spring.mvc.dao;


import com.iammybest.spring.mvc.model.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int insert(UserInfo record);

    UserInfo selectById(Integer id);
    UserInfo selectByNickname(@Param(value = "nickname") String nickname);
}