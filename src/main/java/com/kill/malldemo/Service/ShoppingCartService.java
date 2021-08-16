package com.kill.malldemo.Service;

import com.kill.malldemo.Pojo.ShoppingCart;
import com.kill.malldemo.vo.CartVo;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 4/8/21 3:07 pm
 **/
public interface ShoppingCartService {

    List<CartVo> getCartByUserId(String userId);

    void delCart(String Id, String userId);

    CartVo addCart(String productId, String userId);

    void updateCartNum(String id, String userId, String num);
}
