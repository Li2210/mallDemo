package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 5/8/21 9:44 pm
 **/
@Repository(value="orderDao")
public interface OrderDao {

    void addOrder(Order order);

    List<Order> getOrder(int userId);

}
