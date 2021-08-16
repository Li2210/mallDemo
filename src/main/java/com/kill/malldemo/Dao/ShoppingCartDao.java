package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.ShoppingCart;
import com.kill.malldemo.vo.CartVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 4/8/21 3:05 pm
 **/
@Repository(value = "shoppingCartDao")
public interface ShoppingCartDao {

    List<ShoppingCart> getCartByUserId(String userId);

    void delCart(ShoppingCart cart);

    void addCart(ShoppingCart cart);

    ShoppingCart getCartByUserIdProductId(ShoppingCart cart);

    void updateById(ShoppingCart cart);

    void updateCart(ShoppingCart cart);
}
