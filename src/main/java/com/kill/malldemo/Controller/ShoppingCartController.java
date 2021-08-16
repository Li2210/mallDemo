package com.kill.malldemo.Controller;

import com.kill.malldemo.Service.ShoppingCartService;
import com.kill.malldemo.util.ResultMessage;
import com.kill.malldemo.vo.CartVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 4/8/21 3:09 pm
 **/
@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Resource
    private ResultMessage resultMessage;

    @Resource(name = "shoppingCartService")
    private ShoppingCartService shoppingCartService;

    @ResponseBody
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResultMessage getCart(@PathVariable String userId) {
        List<CartVo> cartVoList = shoppingCartService.getCartByUserId(userId);
        resultMessage.success("001", cartVoList);
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/product/user/{productId}/{userId}", method = RequestMethod.PUT)
    public ResultMessage addCart(@PathVariable String productId, @PathVariable String userId) {
        CartVo cartVo = shoppingCartService.addCart(productId, userId);
        if (cartVo != null) {
            resultMessage.success("001", "添加购物车成功", cartVo);
        } else {
            resultMessage.success("002", "该商品已经在购物车，数量+1");
        }
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/user/num/{id}/{userId}/{num}", method = RequestMethod.POST)
    public ResultMessage updateCart(@PathVariable String id, @PathVariable String userId, @PathVariable String num) {
        shoppingCartService.updateCartNum(id, userId, num);
        resultMessage.success("001", "更新成功");
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/user/{id}/{userId}", method = RequestMethod.DELETE)
    public ResultMessage deleteCart(@PathVariable String id, @PathVariable String userId) {
        shoppingCartService.delCart(id, userId);
        resultMessage.success("001", "删除成功");
        return resultMessage;
    }

}
