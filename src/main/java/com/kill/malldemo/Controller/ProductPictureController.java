package com.kill.malldemo.Controller;

import com.kill.malldemo.Pojo.ProductPicture;
import com.kill.malldemo.Service.ProductPictureService;
import com.kill.malldemo.util.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 1/8/21 7:21 pm
 **/
@Controller
@RequestMapping("/productPicture")
public class ProductPictureController {

    @Resource
    private ResultMessage resultMessage;

    @Resource(name = "productPictureService")
    private ProductPictureService productPictureService;

    @ResponseBody
    @RequestMapping("/product/{productId}")
    public ResultMessage getProductPictureByProductId(@PathVariable int productId) {
        List<ProductPicture> productPictureList = productPictureService.getProductPictureByProductId(productId);
        resultMessage.success("001", productPictureList);
        return resultMessage;
    }

}
