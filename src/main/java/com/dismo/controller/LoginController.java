package com.dismo.controller;

import com.dismo.mapper.LoginMapper;
import com.dismo.model.novel.NovelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author  by Edwin Yang on 2017/11/8 0008.
 */
@RestController
public class LoginController {


    @Autowired
    private LoginMapper loginMapper;

    @RequestMapping("/")
    public ModelAndView toIndexPage(){
        return new ModelAndView("index");
    }

    @RequestMapping(value="/login")
    public ModelAndView  getUserInfoByName() {
        ModelAndView mv = new ModelAndView("view/userInfo");
        NovelInfo book = new NovelInfo();
        mv.addObject("userInfo",loginMapper.findUserByName().get(0));
        return mv;
    }

}
