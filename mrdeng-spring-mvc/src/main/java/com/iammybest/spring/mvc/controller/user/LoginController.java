package com.iammybest.spring.mvc.controller.user;

import com.alibaba.fastjson.JSON;
import com.iammybest.common.log.Logger;
import com.iammybest.spring.mvc.model.UserInfo;
import com.iammybest.spring.mvc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by MrDeng on 2017/3/14.
 */

@Controller
@RequestMapping("/auth")
@Api(value = "auth")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public ModelAndView login(@ApiParam(name = "nickname", value = "nickname", required = true) @RequestParam(value = "nickname", required = true) String nickname,
                              @ApiParam(name = "password", value = "password", required = true) @RequestParam(value = "password", required = true) String password) {

        ModelAndView mv = new ModelAndView();

        UserInfo userInfo = userService.getByNickname(nickname);

        Logger.i(this.getClass(), JSON.toJSONString(userInfo));
        mv.addObject("nickname", userInfo.getNickname());
        return mv;
    }
}
