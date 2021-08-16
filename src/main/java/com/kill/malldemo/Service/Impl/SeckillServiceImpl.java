package com.kill.malldemo.Service.Impl;

import com.kill.malldemo.Dao.ProductDao;
import com.kill.malldemo.Dao.SeckillProductDao;
import com.kill.malldemo.Dao.SeckillTimeDao;
import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import com.kill.malldemo.Pojo.Product;
import com.kill.malldemo.Pojo.SeckillProduct;
import com.kill.malldemo.Pojo.SeckillTime;
import com.kill.malldemo.Service.SeckillService;
import com.kill.malldemo.util.RedisKey;
import com.kill.malldemo.vo.SeckillProductVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author lishen
 * @Date 7/8/21 2:29 pm
 **/
@Service("seckillService")
public class SeckillServiceImpl implements SeckillService {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource(name = "seckillTimeDao")
    private SeckillTimeDao seckillTimeDao;
    @Resource(name = "seckillProductDao")
    private SeckillProductDao seckillProductDao;
    @Resource(name = "productDao")
    private ProductDao productDao;

    @Override
    public List<SeckillProductVo> getSeckillProduct(String timeId) {
        // 先查看缓存 是否有列表
        List<SeckillProductVo> seckillProductVos = redisTemplate.opsForList().range(RedisKey.SECKILL_PRODUCT_LIST + timeId, 0, -1);

        if(seckillProductVos != null) {
            return seckillProductVos;
        }
        // 缓存中没有 加入数据库
        List<SeckillProduct> seckillProducts = null;
        seckillProducts = seckillProductDao.getSeckillProducts(timeId);
        if (seckillProducts != null) {
            for(SeckillProduct s : seckillProducts) {
                if(getSeckillProductVo(s).getEndTime() > new Date().getTime()) {
                    seckillProductVos.add(getSeckillProductVo(s));
                }
            }
            redisTemplate.opsForList().leftPushAll(RedisKey.SECKILL_PRODUCT_LIST + timeId, seckillProductVos);
            //设置过期时间
            long l = seckillProductVos.get(0).getEndTime() - new Date().getTime();
            redisTemplate.expire(RedisKey.SECKILL_PRODUCT_LIST, l, TimeUnit.MILLISECONDS);

        } else {
            throw new BusinessException(ErrorCode.GET_SECKILL_NOT_FOUND);
        }

        return seckillProductVos;
    }

    private SeckillProductVo getSeckillProductVo(SeckillProduct seckillProduct) {
        SeckillProductVo seckillProductVo = new SeckillProductVo();
        Product p = productDao.getProductById(seckillProduct.getProductId());
        SeckillTime s = seckillTimeDao.getSeckillTimeById(seckillProduct.getTimeId());

        seckillProductVo.setId(seckillProduct.getId());
        seckillProductVo.setProductId(seckillProduct.getProductId());
        seckillProductVo.setSeckillPrice(seckillProduct.getSeckillPrice());
        seckillProductVo.setSeckillStock(seckillProduct.getSeckillStock());
        seckillProductVo.setTimeId(seckillProduct.getTimeId());

        seckillProductVo.setStartTime(s.getStartTime());
        seckillProductVo.setEndTime(s.getEndTime());
        seckillProductVo.setProductName(p.getProductName());
        seckillProductVo.setProductPrice(p.getProductSellingPrice());
        seckillProductVo.setProductPicture(p.getProductPicture());

        return seckillProductVo;
    }

    @Override
    public void addSeckillProduct(SeckillProduct seckillProduct) {
        Date time = getDate();
        long startTime = time.getTime() / 1000 * 1000 + 1000 * 60 * 60;
        long endTime = startTime + 1000 * 60 * 60;
        SeckillTime seckillTime = new SeckillTime();
        seckillTime.setStartTime(startTime);
        seckillTime.setEndTime(endTime);

        SeckillTime previous = seckillTimeDao.querySeckillTime(seckillTime);
        if(previous == null) {
            seckillTimeDao.addSeckillTime(seckillTime);
            seckillProduct.setTimeId(seckillTime.getId());
        } else {
            seckillProduct.setTimeId(previous.getId());
        }
        seckillProductDao.addSeckillProduct(seckillProduct);
    }

    /**
     * 获取当前时间的整点
     * @return
     */
    public Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    @Override
    public List<SeckillTime> getTime() {
        Date time = getDate();
        List<SeckillTime> seckillTimes = seckillTimeDao.getTime(time.getTime() / 1000 * 1000);
        return seckillTimes;
    }
}
