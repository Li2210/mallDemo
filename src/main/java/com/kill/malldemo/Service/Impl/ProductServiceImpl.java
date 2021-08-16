package com.kill.malldemo.Service.Impl;

import com.kill.malldemo.Dao.ProductDao;
import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import com.kill.malldemo.Pojo.Product;
import com.kill.malldemo.Service.ProductService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 31/7/21 9:38 pm
 **/
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Resource(name = "productDao")
    private ProductDao productDao;

    @Override
    public List<Product> getProductByCategoryId(int categoryId) {
        List<Product> productList = null;
        PageHelper.startPage(0, 7);
        try {
            productList = productDao.getProductByCategoryId(categoryId);
            if(productList == null) {
                throw new BusinessException(ErrorCode.GET_PRODUCT_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.GET_PRODUCT_ERROR);
        }
        return productList;
    }

    @Override
    public Product getProductById(int productId) {
        Product product = null;
        try {
            product = productDao.getProductById(productId);
            if(product == null) {
                throw new BusinessException(ErrorCode.GET_PRODUCT_NOT_FOUND);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.GET_PRODUCT_ERROR);
        }
        return product;
    }

    @Override
    public List<Product> getHotProduct() {
        List<Product> productList = null;
        PageHelper.startPage(0, 7);

        try {
            productList = productDao.getHotProduct();
            if(productList == null) {
                throw new BusinessException(ErrorCode.GET_PRODUCT_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.GET_PRODUCT_ERROR);
        }
        return productList;
    }

    @Override
    public PageInfo<Product> getProductByPage(String currentPage, String pageSize, String categoryId) {
        List<Product> productList = null;
        PageHelper.startPage(Integer.parseInt(currentPage) - 1, Integer.parseInt(pageSize), true);
        if (categoryId.equals("0")) {
            productList = productDao.getAllProduct();
        } else {
            productList = productDao.getProductByCategoryId(Integer.parseInt(categoryId));
        }
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }

    @Override
    public List<Product> getCollect(String userId) {
        List<Product> productList = null;
        try {
            productList = productDao.getCollect(userId);
//            if(productList.isEmpty()) {
//                throw new BusinessException(ErrorCode.GET_COLLECT_NOT_FOUND);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.GET_COLLECT_ERROR);
        }
        return productList;
    }
}
