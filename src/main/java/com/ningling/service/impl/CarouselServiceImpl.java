package com.ningling.service.impl;

import com.ningling.Entity.Carousel;
import com.ningling.VO.CarouselVO;
import com.ningling.mapper.CarouselMapper;
import com.ningling.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;
    @Override
    public List<CarouselVO> getCarouselData() {
        //获取轮播图数据
        List<Carousel> carouselData = carouselMapper.getCarouselData();
        List<CarouselVO> carouselVO = new ArrayList<>();
//        LocalDateTime now = LocalDateTime.now();
//        // 格式化为 "yyyy-MM-dd HH:mm:ss"
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedTime = now.format(formatter);
        for (Carousel c : carouselData) {
            CarouselVO cVs = CarouselVO.builder()
                    .id(c.getImageId())
                    .imgUrl(c.getImageUrl())
                    .title(c.getDescription())
                    .build();
            carouselVO.add(cVs);
        }


        return carouselVO;
    }
}
