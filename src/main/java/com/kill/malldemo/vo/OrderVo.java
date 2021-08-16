package com.kill.malldemo.vo;

import com.kill.malldemo.Pojo.Order;

/**
 * @Description TODO
 * @Author lishen
 * @Date 6/8/21 1:47 pm
 **/
public class OrderVo extends Order {

    private String productName;
    private String productPicture;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }
}
