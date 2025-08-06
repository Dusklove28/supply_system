package com.ningling.controller;


import com.ningling.Entity.Pears;
import com.ningling.VO.PearsListVO;
import com.ningling.service.PearsService;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pear")
@Api(tags = "梨果相关接口")
public class ProductController {

    @Autowired
    private PearsService pearsService;

    @GetMapping("/getPearsList")
    @ApiOperation("获取商品列表")
    public Result<List<PearsListVO>> getPearsList(){
        return Result.success(pearsService.getPearsList());
    }

    @GetMapping("/getPearById/{productId}")
    @ApiOperation("根据id查询商品")
    public Result<PearsListVO> getPearById(@PathVariable Long productId){
        return Result.success(pearsService.getPearById(productId));
    }

    @GetMapping("/checkStock/{productId}")
    @ApiOperation("根据id查询商品库存")
    public Result checkStockById(@PathVariable Long productId){
        if(!pearsService.checkStockById(productId)){
            return Result.error("库存不足");
        }
        return Result.success();
    }

    @PutMapping("/updateProduct/{productId}")
    @ApiOperation("更新商品")
    public Result updateProduct(@RequestBody Pears pears ,
                                @PathVariable Long productId){
        if(!pearsService.update(pears,productId)){
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }

    @GetMapping("/search")
    @ApiOperation("搜索商品列表")
    public Result<List<PearsListVO>> searchPearsList(@RequestParam String keyword){
        return Result.success(pearsService.searchPearsList(keyword));
    }

    @GetMapping("/getProductsByCategory/{classificationId}")
    @ApiOperation("根据分类搜索商品列表")
    public Result<List<PearsListVO>> getProductsByCategory(@PathVariable Long classificationId){
        return Result.success(pearsService.getProductsByCategory(classificationId));
    }
}
