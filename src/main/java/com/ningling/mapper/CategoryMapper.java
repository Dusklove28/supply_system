package com.ningling.mapper;

import com.ningling.Entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> getCategoryList();

    int update(Category category);

    int createCategory(Category category);

    int deleteCategory(Long categoryId);


}
