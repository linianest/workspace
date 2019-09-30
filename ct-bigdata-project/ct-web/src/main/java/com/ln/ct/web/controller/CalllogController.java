package com.ln.ct.web.controller;


import com.ln.ct.web.bean.Calllog;
import com.ln.ct.web.service.CalllogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Description 通话日志控制器对象
 * @AUTHOR LiNian
 * @DATE 2019/9/29 22:26
 */
@Controller
public class CalllogController {

    @Autowired
    private CalllogService calllogService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping("/query")
    public String query(String tel, String calltime, Model model){

        // 查询统计结果：mysql
        List<Calllog> logs=calllogService.queryMonthDatas(tel,calltime);
        model.addAttribute("calllogs",logs);
        return "query";
    }
}
