package com.kill.malldemo.Controller;

import com.kill.malldemo.Pojo.Product;
import com.kill.malldemo.Service.CollectService;
import com.kill.malldemo.Service.ProductService;
import com.kill.malldemo.util.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 3/8/21 8:39 pm
 **/
@Controller
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private ResultMessage resultMessage;

    @Resource(name = "collectService")
    private CollectService collectService;

    @Resource(name = "productService")
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/user/{productId}/{userId}", method = RequestMethod.POST)
    public ResultMessage addCollect(@PathVariable String productId, @PathVariable String userId) {
        collectService.addCollect(userId, productId);
        resultMessage.success("001", "商品收藏成功");
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping("/user/{userId}")
    public ResultMessage getUserCollect(@PathVariable String userId){
        List<Product> productList = productService.getCollect(userId);
        resultMessage.success("001", productList);
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/user/{productId}/{userId}")
    public ResultMessage delCollect(@PathVariable String productId, @PathVariable String userId) {
        collectService.delCollect(userId, productId);
        resultMessage.success("001", "删除收藏成功");
        return resultMessage;
    }
}
