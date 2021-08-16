package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 31/7/21 5:38 pm
 **/
@Repository(value = "productDao")
public interface ProductDao {

    List<Product> getProductByCategoryId(int categoryId);

    Product getProductById(int productId);

    List<Product> getHotProduct();

    List<Product> getAllProduct();

    List<Product> getCollect(String userId);

    void updateProduct(Product product);

}
