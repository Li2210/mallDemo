package com.kill.malldemo.Service;

import com.github.pagehelper.PageInfo;
import com.kill.malldemo.Pojo.Product;
import com.kill.malldemo.Pojo.ProductPicture;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 1/8/21 7:22 pm
 **/
public interface ProductPictureService {

    List<ProductPicture> getProductPictureByProductId(int productId);

}
