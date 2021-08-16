package com.kill.malldemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description TODO
 * @Author lishen
 * @Date 24/7/21 9:01 pm
 **/
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/demo")
    @ResponseBody
    public String test() {
        return "hello";
    }
}
