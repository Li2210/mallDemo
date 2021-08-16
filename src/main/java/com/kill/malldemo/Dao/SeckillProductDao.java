package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.SeckillProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 7/8/21 2:52 pm
 **/
@Repository(value = "seckillProductDao")
public interface SeckillProductDao {

    void addSeckillProduct(SeckillProduct seckillProduct);

    List<SeckillProduct> getSeckillProducts(String timeId);

}
