package com.kill.malldemo.Pojo;

/**
 * @Description TODO
 * @Author lishen
 * @Date 7/8/21 1:47 pm
 **/
public class SeckillProduct {

    private int id;
    private int productId;
    private Double seckillPrice;
    private int seckillStock;
    private int timeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public int getSeckillStock() {
        return seckillStock;
    }

    public void setSeckillStock(int seckillStock) {
        this.seckillStock = seckillStock;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }
}
