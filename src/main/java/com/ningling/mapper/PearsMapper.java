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
    Pears getPearById(int productId);

    @Select("select stock from products where product_id  = #{productId}")
    int checkStockById(int productId);
}
