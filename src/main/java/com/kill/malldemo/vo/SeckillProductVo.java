package com.kill.malldemo.vo;

import com.kill.malldemo.Pojo.SeckillProduct;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author lishen
 * @Date 7/8/21 3:49 pm
 **/
public class SeckillProductVo extends SeckillProduct implements Serializable {

    private String productName;
    private Double productPrice;
    private String productPicture;
    private Long startTime;
    private Long endTime;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

}
