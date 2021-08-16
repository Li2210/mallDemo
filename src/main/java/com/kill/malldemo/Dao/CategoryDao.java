package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.Carousel;
import com.kill.malldemo.Pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 31/7/21 5:33 pm
 **/
@Repository(value="categoryDao")
public interface CategoryDao {

    List<Category> getCategoryList();

}
