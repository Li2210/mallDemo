package com.kill.malldemo.Service.Impl;

import com.kill.malldemo.Dao.ProductPictureDao;
import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import com.kill.malldemo.Pojo.ProductPicture;
import com.kill.malldemo.Service.ProductPictureService;
import com.kill.malldemo.Service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 1/8/21 7:23 pm
 **/
@Service("productPictureService")
public class ProductPictureServiceImpl implements ProductPictureService {

    @Resource(name = "productPictureDao")
    private ProductPictureDao productPictureDao;

    @Override
    public List<ProductPicture> getProductPictureByProductId(int productId) {
        List<ProductPicture> productPictureList = null;
        try {
            productPictureList = productPictureDao.getProductPictureByProductId(productId);
            if(productPictureList == null) {
                throw new BusinessException(ErrorCode.GET_PRODUCT_PICTURE_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.GET_PRODUCT_PICTURE_ERROR);
        }
        return productPictureList;
    }

}
