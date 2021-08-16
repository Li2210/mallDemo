package com.kill.malldemo.Dao;

import com.kill.malldemo.Pojo.Carousel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 28/7/21 5:24 pm
 **/
@Repository(value="carouselDao")
public interface CarouselDao {

    List<Carousel> getCarouselList();

}
