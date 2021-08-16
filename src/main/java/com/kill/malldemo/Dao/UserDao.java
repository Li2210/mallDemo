package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Description TODO
 * @Author lishen
 * @Date 26/7/21 2:55 pm
 **/
@Repository(value="userDao")
public interface UserDao {

    List<User> getAllUserInfo();

    User loginUser(String phone, String password);

    void register(User user);

    int countByPhone(String phone);

}
