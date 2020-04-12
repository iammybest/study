package com.iammybest.spring.mvc.controller;

import com.iammybest.common.log.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by MrDeng on 2017/3/13.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        Logger.i(this.getClass(),"Home Page");
        return "index";
    }
    @RequestMapping("/error")
    public ModelAndView error(){
        Logger.i(this.getClass(),"error Page");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
}
