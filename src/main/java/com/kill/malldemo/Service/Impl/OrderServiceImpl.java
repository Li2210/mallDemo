package com.kill.malldemo.Service.Impl;

import com.kill.malldemo.Dao.OrderDao;
import com.kill.malldemo.Dao.ProductDao;
import com.kill.malldemo.Dao.ShoppingCartDao;
import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import com.kill.malldemo.Pojo.Order;
import com.kill.malldemo.Pojo.Product;
import com.kill.malldemo.Pojo.ShoppingCart;
import com.kill.malldemo.Service.OrderService;
import com.kill.malldemo.util.IdWorker;
import com.kill.malldemo.util.SnowFlake;
import com.kill.malldemo.vo.CartVo;
import com.kill.malldemo.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author lishen
 * @Date 6/8/21 1:01 am
 **/
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource(name = "orderDao")
    private OrderDao orderDao;
    @Resource
    private SnowFlake snowFlake;
    @Resource(name = "shoppingCartDao")
    private ShoppingCartDao shoppingCartDao;
    @Resource(name = "productDao")
    private ProductDao productDao;

    @Override
    public void addOrder(List<CartVo> cartVoList, int userId) {
        String orderId = snowFlake.nextId() + ""; //生成订单id
        long time = new Date().getTime();         //生成时间
        List<Integer> cartIdList = new ArrayList<>();

        for (CartVo cartVo : cartVoList) {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderTime(time);
            order.setProductId(cartVo.getProductId());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            order.setUserId(userId);
            // 获得cart id
            cartIdList.add(cartVo.getId());
            try {
                orderDao.addOrder(order);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(ErrorCode.ADD_ORDER_ERROR);
            }
            // 减去商品库存,记录卖出商品数量
            // TODO : 此处会产生多线程问题，即不同用户同时对这个商品操作，此时会导致数量不一致问题
            Product product = productDao.getProductById(cartVo.getProductId());
            System.out.println("product original num: " + product.getProductNum());
            product.setProductNum(product.getProductNum() - cartVo.getNum());
            System.out.println("updated num: " + product.getProductNum());
            System.out.println("product original sales: " + product.getProductSales());
            product.setProductSales(product.getProductSales() + cartVo.getNum());
            System.out.println("product updated sales: " + product.getProductSales());
            productDao.updateProduct(product);
        }
        // 更新购物车数据
        // TODO : 此处会产生多线程问题，即不同用户同时对这个商品操作，此时会导致数量不一致问题
        for(int id : cartIdList) {
            ShoppingCart cart = new ShoppingCart();
            cart.setUserId(userId);
            cart.setId(id);
            try {
                shoppingCartDao.delCart(cart);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(ErrorCode.ADD_ORDER_ERROR);
            }
        }
    }

    private OrderVo getOrderVo(Order order) {
        Product product = productDao.getProductById(order.getProductId());
        OrderVo orderVo = new OrderVo();
        orderVo.setId(order.getId());
        orderVo.setOrderId(order.getOrderId());
        orderVo.setProductId(order.getProductId());
        orderVo.setProductName(product.getProductName());
        orderVo.setProductPicture(product.getProductPicture());
        orderVo.setOrderTime(order.getOrderTime());
        orderVo.setProductNum(order.getProductNum());
        orderVo.setProductPrice(order.getProductPrice());
        orderVo.setUserId(order.getUserId());

        return orderVo;
    }

    @Override
    public List<List<OrderVo>> getOrder(int userId) {
        List<Order> orderList = null;
        List<OrderVo> orderVos = new ArrayList<>();
        List<List<OrderVo>> result = new ArrayList<>();
        try {
            orderList = orderDao.getOrder(userId);

            if(orderList == null) {
                throw new BusinessException(ErrorCode.GET_ORDER_NOT_FOUND);
            } else {
                for (Order order : orderList) {
                    orderVos.add(getOrderVo(order));
                }
                // 同一组订单放在一起
                Map<String, List<OrderVo>> group = orderVos.stream().collect(Collectors.groupingBy(Order::getOrderId));
                Collection<List<OrderVo>> values = group.values();
                result.addAll(values);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.GET_ORDER_ERROR);
        }
        return result;
    }

}
