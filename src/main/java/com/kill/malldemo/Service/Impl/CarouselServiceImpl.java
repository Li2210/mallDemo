package com.kill.malldemo.Service.Impl;

import com.kill.malldemo.Dao.CarouselDao;
import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import com.kill.malldemo.Pojo.Carousel;
import com.kill.malldemo.Service.CarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 28/7/21 5:26 pm
 **/
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {

    @Resource(name = "carouselDao")
    private CarouselDao carouselDao;

    @Override
    public List<Carousel> getCarouselList() {
        List<Carousel> allCarousel = null;
        try {
            allCarousel = carouselDao.getCarouselList();
            if (allCarousel == null) {
                throw new BusinessException(ErrorCode.GET_CAROUSEL_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.GET_CAROUSEL_ERROR);
        }
        return allCarousel;
    }

}
