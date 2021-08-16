package com.kill.malldemo.Controller;

import com.kill.malldemo.Pojo.Product;
import com.kill.malldemo.Service.ProductService;
import com.kill.malldemo.util.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author lishen
 * @Date 31/7/21 10:15 pm
 **/
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ResultMessage resultMessage;

    @Resource(name = "productService")
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/category/limit/{categoryId}", method = RequestMethod.GET)
    public ResultMessage getProductByCategoryId(@PathVariable int categoryId) {
        List<Product> productList = productService.getProductByCategoryId(categoryId);
        resultMessage.success("001", productList);
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResultMessage getProductById(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        resultMessage.success("001", product);
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/category/hot", method = RequestMethod.GET)
    public ResultMessage getHotProduct() {
        List<Product> list = productService.getHotProduct();
        resultMessage.success("001", list);
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/page/{currentPage}/{pageSize}/{categoryId}", method = RequestMethod.GET)
    public Map<String, Object> getProductByPage(@PathVariable String currentPage,
                                                @PathVariable String pageSize,
                                                @PathVariable String categoryId)
    {
        PageInfo<Product> pageInfo = productService.getProductByPage(currentPage, pageSize, categoryId);

        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "001");
        map.put("data", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

}
