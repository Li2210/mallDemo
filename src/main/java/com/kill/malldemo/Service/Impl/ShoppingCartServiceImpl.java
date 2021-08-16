package com.kill.malldemo.Service.Impl;

import com.kill.malldemo.Dao.ProductDao;
import com.kill.malldemo.Dao.ShoppingCartDao;
import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import com.kill.malldemo.Pojo.Product;
import com.kill.malldemo.Pojo.ShoppingCart;
import com.kill.malldemo.Service.ShoppingCartService;
import com.kill.malldemo.vo.CartVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 4/8/21 3:07 pm
 **/
@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource(name = "shoppingCartDao")
    private ShoppingCartDao shoppingCartDao;

    @Resource(name = "productDao")
    private ProductDao productDao;

    @Override
    public List<CartVo> getCartByUserId(String userId) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(Integer.parseInt(userId));
        List<ShoppingCart> cartList = null;
        List<CartVo> cartVoList = new ArrayList<>();
        try {
            cartList = shoppingCartDao.getCartByUserId(userId);
            for (ShoppingCart c : cartList) {
                cartVoList.add(getCartVo(c));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.GET_CART_ERROR);
        }
        return cartVoList;
    }

    /**
     * 封装成CartVo类型
     * @param cart
     * @return
     */
    private CartVo getCartVo(ShoppingCart cart) {

        Product product = productDao.getProductById(cart.getProductId());
        CartVo cartVo = new CartVo();
        cartVo.setId(cart.getId());
        cartVo.setProductId(cart.getProductId());
        cartVo.setProductName(product.getProductName());
        cartVo.setProductImg(product.getProductPicture());
        cartVo.setPrice(product.getProductPrice());
        cartVo.setNum(cart.getNum());
        cartVo.setMaxNum(5);
        cartVo.setCheck(false);
        return cartVo;
    }

    @Override
    public CartVo addCart(String productId, String userId) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(Integer.parseInt(userId));
        cart.setProductId(Integer.parseInt(productId));
        //查看数据库是否存在
        ShoppingCart pre = shoppingCartDao.getCartByUserIdProductId(cart);
        if(pre != null) {
            if(pre.getNum() >= 5) {
                throw new BusinessException(ErrorCode.ADD_CART_NUM_UPPER);
            }
            pre.setNum(pre.getNum() + 1);
            shoppingCartDao.updateById(pre);
            return null;
        } else {
            cart.setNum(1);
            shoppingCartDao.addCart(cart);
            return getCartVo(cart);
        }
    }

    @Override
    public void delCart(String Id, String userId) {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(Integer.parseInt(Id));
        cart.setUserId(Integer.parseInt(userId));
        try {
            shoppingCartDao.delCart(cart);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.DELETE_CART_ERROR);
        }
    }

    @Override
    public void updateCartNum(String id, String userId, String num) {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(Integer.parseInt(id));
        cart.setUserId(Integer.parseInt(userId));
        cart.setNum(Integer.parseInt(num));
        try {
            shoppingCartDao.updateCart(cart);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.UPDATE_CART_ERROR);
        }
    }
}
