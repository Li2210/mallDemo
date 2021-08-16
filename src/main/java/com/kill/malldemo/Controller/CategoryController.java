package com.kill.malldemo.Controller;

import com.kill.malldemo.Pojo.Carousel;
import com.kill.malldemo.Pojo.Category;
import com.kill.malldemo.Service.CategoryService;
import com.kill.malldemo.util.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 31/7/21 7:41 pm
 **/
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private ResultMessage resultMessage;

    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultMessage getCategories() {
        List<Category> categoryList = categoryService.getCategoryList();
        resultMessage.success("001", categoryList);
        return resultMessage;
    }

}
