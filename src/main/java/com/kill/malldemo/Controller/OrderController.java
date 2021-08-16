package com.kill.malldemo.Controller;

import com.kill.malldemo.Pojo.User;
import com.kill.malldemo.Service.OrderService;
import com.kill.malldemo.util.BeanUtil;
import com.kill.malldemo.util.ResultMessage;
import com.kill.malldemo.vo.CartVo;
import com.kill.malldemo.vo.OrderVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 6/8/21 10:49 pm
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private ResultMessage resultMessage;
    @Resource(name = "orderService")
    private OrderService orderService;
    @Resource(name = "myRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultMessage addOrder(@RequestBody List<CartVo> cartVoList, @CookieValue("MALL_TOKEN") String token) {
        String temp = redisTemplate.opsForValue().get(token);
        User user = BeanUtil.stringToBean(temp, User.class);
        assert user != null;
        orderService.addOrder(cartVoList, user.getId());
        resultMessage.success("001", "下单成功");
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultMessage getOrder(@CookieValue("MALL_TOKEN") String token) {
        String temp = redisTemplate.opsForValue().get(token);
        User user = BeanUtil.stringToBean(temp, User.class);
        assert user != null;
        List<List<OrderVo>> orders = orderService.getOrder(user.getId());
        resultMessage.success("001", orders);
        return resultMessage;
    }

}
