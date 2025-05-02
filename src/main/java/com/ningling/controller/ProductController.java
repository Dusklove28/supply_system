package com.ningling.controller;


import com.ningling.VO.PearsListVO;
import com.ningling.service.PearsService;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
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
    public Result<List<PearsListVO>> getPearsList(){
        return Result.success(pearsService.getPearsList());
    }

    @GetMapping("/getPearById/{productId}")
    public Result<PearsListVO> getPearById(@PathVariable Long productId){
        return Result.success(pearsService.getPearById(productId));
    }

    @GetMapping("/checkStock/{productId}")
    public Result checkStockById(@PathVariable int productId){
        if(!pearsService.checkStockById(productId)){
            return Result.error("库存不足");
        }
        return Result.success();
    }
}
