package com.ningling.controller;


import com.ningling.Entity.Carousel;
import com.ningling.VO.CarouselVO;
import com.ningling.service.CarouselService;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "轮播图接口")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/getCarouselData")
    public Result<List<CarouselVO>> getCarouselDara(){
        return Result.success(carouselService.getCarouselData());
    }
}
