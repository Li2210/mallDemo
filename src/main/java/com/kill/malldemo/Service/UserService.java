package com.kill.malldemo.Service;

import com.kill.malldemo.Pojo.User;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 26/7/21 3:32 pm
 **/
public interface UserService {

    List<User> getAllUserInfo();

    User loginUser(String phone, String password);

    void register(User user);

    boolean isPhoneValid(String phone);
}
