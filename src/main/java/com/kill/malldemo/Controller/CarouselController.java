package com.kill.malldemo.Controller;

import com.kill.malldemo.Pojo.Carousel;
import com.kill.malldemo.Service.CarouselService;
import com.kill.malldemo.util.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description TODO
 * @Author lishen
 * @Date 28/7/21 5:37 pm
 **/
@Controller
public class CarouselController {

    @Resource
    private ResultMessage resultMessage;

    @Resource(name = "carouselService")
    private CarouselService carouselService;

    @ResponseBody
    //@CrossOrigin
    @RequestMapping(value = "/resources/carousels", method = RequestMethod.GET)
    public ResultMessage getCarousels(){
        List<Carousel> carouselList = carouselService.getCarouselList();
        resultMessage.success("001", carouselList);
        return resultMessage;
    }

}
