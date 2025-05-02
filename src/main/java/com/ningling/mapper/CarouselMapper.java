package com.ningling.mapper;

import com.ningling.Entity.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarouselMapper {

    @Select("select * from carousel_images")
    List<Carousel> getCarouselData();
}
