package com.ningling.service;

import com.ningling.DTO.CategoryDTO;
import com.ningling.Entity.Category;
import com.ningling.VO.CategoryVO;

import java.util.List;

public interface CategoryService {
    List<CategoryVO> getCategoryList();

    boolean updateCategory(CategoryDTO categoryDTO);

    boolean createCategory(Category category);

    boolean deleteCategory(Long categoryId);

}
