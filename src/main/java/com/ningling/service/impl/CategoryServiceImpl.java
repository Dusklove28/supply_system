package com.ningling.service.impl;

import com.ningling.DTO.CategoryDTO;
import com.ningling.VO.CategoryVO;
import com.ningling.Entity.Category;
import com.ningling.mapper.CategoryMapper;
import com.ningling.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> getCategoryList() {

        List<Category> categoryList = categoryMapper.getCategoryList();
        //new出VO对象
        List<CategoryVO> categoryVOList  = new ArrayList<>();
        for (Category c : categoryList) {
            CategoryVO categoryVO = CategoryVO.builder()
                    .categoryId(c.getClassificationId())
                    .categoryName(c.getName())
                    .description(c.getDescription())
                    .createdTime(c.getCreatedTime())
                    .build();

            categoryVOList.add(categoryVO);
        }

        return categoryVOList;
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO) {

        Category category = Category.builder()
                .name(categoryDTO.getCategoryName())
                .description(categoryDTO.getDescription())
                .updatedTime(LocalDateTime.now())
                .build();

        if(categoryMapper.update(category) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean createCategory(Category category) {
        if(categoryMapper.createCategory(category) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
        if(categoryMapper.deleteCategory(categoryId) > 0){
            return true;
        }
        return false;
    }
}
