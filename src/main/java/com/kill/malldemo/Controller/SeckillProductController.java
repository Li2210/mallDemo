package com.kill.malldemo.Controller;

import com.kill.malldemo.Pojo.SeckillProduct;
import com.kill.malldemo.Pojo.SeckillTime;
import com.kill.malldemo.Service.SeckillService;
import com.kill.malldemo.util.ResultMessage;
import com.kill.malldemo.vo.SeckillProductVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 7/8/21 5:16 pm
 **/
@Controller
@RequestMapping("/seckill/product")
public class SeckillProductController {

    @Resource
    private ResultMessage resultMessage;
    @Resource(name = "seckillService")
    private SeckillService seckillService;

    @ResponseBody
    @RequestMapping(value = "/time/{timeId}", method = RequestMethod.GET)
    public ResultMessage getSeckillProduct(@PathVariable String timeId) {
        List<SeckillProductVo> seckillProductVoList = seckillService.getSeckillProduct(timeId);
        resultMessage.success("001", seckillProductVoList);
        return resultMessage;
    }

    /**
     * 添加秒杀商品
     * @param seckillProduct
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultMessage addSeckillProduct(@RequestBody SeckillProduct seckillProduct) {
        seckillService.addSeckillProduct(seckillProduct);
        resultMessage.success("001", "添加成功");
        return resultMessage;
    }

    /**
     * 获取时间
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public ResultMessage getTime() {
        List<SeckillTime> seckillTimes = seckillService.getTime();
        resultMessage.success("001", seckillTimes);
        return resultMessage;
    }

}
