package com.ningling.mapper;

import com.ningling.Entity.Carousel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarouselMapper {

    @Select("select * from carousel_images")
    List<Carousel> getCarouselData();

    int insert(Carousel carousel);

    @Delete("delete from carousel_images where image_id = #{imageId}")
    int delete(Long imageId);

    @Select("select * from carousel_images where image_id = #{imageId}")
    Carousel getCarouselById(Long imageId);

    int update(Carousel carousel);
}
