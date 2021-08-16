package com.kill.malldemo.Controller;

import com.kill.malldemo.Pojo.User;
import com.kill.malldemo.Service.UserService;
import com.kill.malldemo.util.BeanUtil;
import com.kill.malldemo.util.CookieUtil;
import com.kill.malldemo.util.MD5Util;
import com.kill.malldemo.util.ResultMessage;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author lishen
 * @Date 26/7/21 3:38 pm
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource
    private ResultMessage resultMessage;

    @Resource(name = "myRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @ResponseBody
    @RequestMapping(value = "/api/loadAllUser", method = RequestMethod.GET)
    public List<User> getAllUser() {
        List<User> allUserInfo = userService.getAllUserInfo();
        return allUserInfo;
    }

    @ResponseBody
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResultMessage logIn(@RequestBody User requestUser, HttpServletResponse response, HttpServletRequest request) {
        User user = userService.loginUser(requestUser.getPhone(), requestUser.getPassword());
        String encoded = MD5Util.encode(user.getPassword());
        //加盐
        encoded += "|" + user.getId() + "|" + user.getPhone() + "|";
        CookieUtil.setCookie(request, response, "MALL_TOKEN", encoded, 1800);

        try {
            redisTemplate.opsForValue().set(encoded, BeanUtil.beanToString(user));//putAll(encoded, BeanUtil.beanToMap(user));
            //System.out.println("token is: " + encoded);
            redisTemplate.expire(encoded, 30 * 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //不能把密码返回前端
        user.setPassword(null);
        resultMessage.success("001", "登陆成功", user);
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    public ResultMessage register(@RequestBody User user) {
//        if(userService.isPhoneValid(user.getPhone())) {
//            userService.register(user);
//            resultMessage.success("001", "注册成功");
//        } else {
//            resultMessage.fail("002", "手机号已经被使用了");
//        }
        userService.register(user);
        resultMessage.success("001", "注册成功");
        return resultMessage;
    }


    @ResponseBody
    @RequestMapping(value = "/phone/{phone}", method = RequestMethod.GET)
    public ResultMessage phone(@PathVariable String phone) {
        if(userService.isPhoneValid(phone)) {
            resultMessage.success("001", "注册成功");
        } else {
            resultMessage.fail("002", "手机号已经被使用了");
        }
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ResultMessage getUserFromToken(@CookieValue("MALL_TOKEN") String token, HttpServletResponse response, HttpServletRequest request) throws Exception {
        String result = redisTemplate.opsForValue().get(token);
        // 可能map为空 ， 即redis中时间已过期，但是cookie还存在。
        // 这个时候应该删除cookie，让用户重新登录
        if (result == null) {
            System.out.println("token is : " + token);
            CookieUtil.delCookie(request, response, "MALL_TOKEN");
            resultMessage.fail("002", "账号信息过期,请重新登陆");
            return resultMessage;
        }

        redisTemplate.expire(token, 30 * 60, TimeUnit.SECONDS); // 设置过期时间
        User user = BeanUtil.stringToBean(result, User.class);
        user.setPassword(null);
        resultMessage.success("001", user);
        return resultMessage;
    }

}
