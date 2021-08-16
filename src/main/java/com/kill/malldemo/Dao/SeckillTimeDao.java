package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.SeckillTime;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 7/8/21 2:32 pm
 **/
@Repository(value = "seckillTimeDao")
public interface SeckillTimeDao {

    List<SeckillTime> getTime(long endTime);

    SeckillTime querySeckillTime(SeckillTime seckillTime);

    void addSeckillTime(SeckillTime seckillTime);

    SeckillTime getSeckillTimeById(int id);

}
