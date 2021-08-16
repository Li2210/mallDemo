package com.kill.malldemo.Service;

import com.kill.malldemo.Pojo.SeckillProduct;
import com.kill.malldemo.Pojo.SeckillTime;
import com.kill.malldemo.vo.SeckillProductVo;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 7/8/21 2:28 pm
 **/
public interface SeckillService {

    List<SeckillProductVo> getSeckillProduct(String timeId);

    void addSeckillProduct(SeckillProduct seckillProduct);

    List<SeckillTime> getTime();

}
