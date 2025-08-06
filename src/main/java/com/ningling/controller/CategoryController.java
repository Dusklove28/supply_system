package com.ningling.controller;

import com.ningling.DTO.CategoryDTO;
import com.ningling.Entity.Category;
import com.ningling.VO.CategoryVO;
import com.ningling.service.CategoryService;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
@Api("分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @ApiOperation("/获取分类列表")
    public Result<List<CategoryVO>> getCategoryList(){
        return Result.success(categoryService.getCategoryList());
    }

    @PutMapping("/updateCategory}")
    @ApiOperation("/修改商品分类")
    public Result<?> updateCategory(@RequestBody CategoryDTO categoryDTO){
        if(!categoryService.updateCategory(categoryDTO)){
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }

    @PostMapping("/createCategory}")
    @ApiOperation("/新增商品分类")
    public Result<?> createCategory(@RequestBody Category category){
        if(!categoryService.createCategory(category)){
            return Result.error("新增失败");
        }
        return Result.success("新增成功");
    }

    @DeleteMapping("/deleteCategory/{categoryId}}")
    @ApiOperation("删除商品分类")
    public Result<?> deleteCategory(@PathVariable Long categoryId){
        if(!categoryService.deleteCategory(categoryId)){
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }
}
