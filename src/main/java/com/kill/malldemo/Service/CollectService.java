package com.kill.malldemo.Service;

import com.kill.malldemo.Pojo.Product;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 3/8/21 8:41 pm
 **/
public interface CollectService {

    void addCollect(String userId, String productId);

    void delCollect(String userId, String productId);
}
