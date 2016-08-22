package com.chris.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuxing on 2016/8/20.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query(long id){
        if(id == 0){
            return "error";
        }
        return String.valueOf(id);
    }
}
