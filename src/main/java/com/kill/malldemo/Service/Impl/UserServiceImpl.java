package com.kill.malldemo.Service.Impl;

import com.kill.malldemo.Dao.UserDao;
import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import com.kill.malldemo.Pojo.User;
import com.kill.malldemo.Service.UserService;
import com.kill.malldemo.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Description TODO
 * @Author lishen
 * @Date 26/7/21 3:34 pm
 **/

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public List<User> getAllUserInfo() {
        List<User> allUsers = this.userDao.getAllUserInfo();
        return allUsers;
    }

    @Override
    public User loginUser(String phone, String password) {
        String encodedPassword = MD5Util.encode(password);
        User logUser = userDao.loginUser(phone, encodedPassword);
        if (logUser == null) {
            throw new BusinessException(ErrorCode.GET_USER_NOT_FOUND);
        }
        return logUser;
    }

    @Override
    public void register(User user) {
        User registerUser = new User();

//        if(userDao.countByPhone(user.getPhone()) >= 1) {
//            throw new BusinessException(ErrorCode.SAVE_USER_ERROR);
//        }

        registerUser.setPhone(user.getPhone());
        registerUser.setPassword(MD5Util.encode(user.getPassword()));
        registerUser.setNickname(user.getNickname());
        registerUser.setAge(user.getAge());
        registerUser.setGender(user.getGender());
        try {
            userDao.register(registerUser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.SAVE_USER_ERROR);
        }
    }

    @Override
    public boolean isPhoneValid(String phone) {
        if (userDao.countByPhone(phone) >= 1) {
            //throw new BusinessException(ErrorCode.SAVE_USER_REUSE);
            return false;
        } else {
            return true;
        }
    }

}
