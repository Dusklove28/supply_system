package com.ningling.service;

import com.ningling.Entity.Carousel;
import com.ningling.VO.CarouselVO;

import java.util.List;

public interface CarouselService {

    List<CarouselVO> getCarouselData();

    boolean addCarousel(Carousel carousel);

    boolean updateCarousel(Carousel carousel);

    boolean deleteCarousel(Long id);
}
