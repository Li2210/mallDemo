package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.Collect;
import com.kill.malldemo.Pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 3/8/21 8:40 pm
 **/
@Repository(value="collectDao")
public interface CollectDao {

    void addCollect(Collect collect);

    Collect queryCollectByUserIdAndProductId(String userId, String productId);

    void delCollect(Collect collect);

}
