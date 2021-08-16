package com.kill.malldemo.Service;

import com.kill.malldemo.vo.CartVo;
import com.kill.malldemo.vo.OrderVo;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 6/8/21 1:00 am
 **/
public interface OrderService {

    void addOrder(List<CartVo> cartVoList, int userId);

    List<List<OrderVo>> getOrder(int userId);
}
