package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.ProductPicture;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 1/8/21 7:29 pm
 **/
@Repository(value = "productPictureDao")
public interface ProductPictureDao {

    List<ProductPicture> getProductPictureByProductId(int productId);

}
