package com.ningling.mapper;

import com.ningling.Entity.Pears;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PearsMapper {

    @Select("select * from products")
    List<Pears> getPearsList();

    @Select("select * from products where product_id  = #{productId}")
    Pears getPearById(Long productId);

    @Select("select stock from products where product_id  = #{productId}")
    int checkStockById(Long productId);

    int update(Pears pears);

    List<Pears> searchPearsList(String keyword);

    List<Pears> getProductsByCategory(Long classificationId);


    int delete(Long userId);
}
