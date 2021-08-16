package com.kill.malldemo.Service;

import com.github.pagehelper.PageInfo;
import com.kill.malldemo.Pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 31/7/21 9:17 pm
 **/
public interface ProductService {

    List<Product> getProductByCategoryId(int categoryId);

    Product getProductById(int productId);

    List<Product> getHotProduct();

    PageInfo<Product> getProductByPage(String currentPage, String pageSize, String categoryId);

    List<Product> getCollect(String userId);

}
