package com.kill.malldemo.Pojo;

/**
 * @Description TODO
 * @Author lishen
 * @Date 3/8/21 8:52 pm
 **/
public class Collect {

    private int id;
    private int userId;
    private int productId;
    private Long collectTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Long getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Long collectTime) {
        this.collectTime = collectTime;
    }
}
