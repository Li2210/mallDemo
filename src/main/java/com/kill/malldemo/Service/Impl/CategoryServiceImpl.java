package com.kill.malldemo.Service.Impl;

import com.kill.malldemo.Dao.CategoryDao;
import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import com.kill.malldemo.Pojo.Category;
import com.kill.malldemo.Service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 31/7/21 7:23 pm
 **/
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Resource(name = "categoryDao")
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryDao.getCategoryList();
            if (categoryList == null) {
                throw new BusinessException(ErrorCode.GET_CATEGORY_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.GET_CATEGORY_ERROR);
        }
        return categoryList;
    }

}
