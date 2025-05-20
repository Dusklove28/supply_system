package com.ningling.controller;


import com.ningling.Entity.Carousel;
import com.ningling.VO.CarouselVO;
import com.ningling.service.CarouselService;
import com.ningling.utils.QiniuKodoUtil;
import com.ningling.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "轮播图接口")
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private QiniuKodoUtil qiniuKodoUtil;

    @GetMapping("/getCarouselData")
    public Result<List<CarouselVO>> getCarouselDara(){
        return Result.success(carouselService.getCarouselData());
    }

    @PostMapping("/upload")
    @ApiOperation("上传轮播图")
    public Result<Map<String,String>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return Result.error("文件不能为空");
            }

            String imageUrl = qiniuKodoUtil.uploadFile(file);
            Map<String, String> data = new HashMap<>();
            data.put("url",imageUrl);
            return Result.success(data);

        } catch (IOException e) {
            log.error("图片上传失败", e);
            return Result.error("上传失败: " + e.getMessage());
        }
    }

    // CarouselController.java 添加增删改接口
    @PostMapping("/addCarousel")
    @ApiOperation("新增轮播图")
    public Result<?> addCarousel(@RequestBody Carousel carousel) {
        carouselService.addCarousel(carousel);
        return Result.success("新增成功");
    }

    @PutMapping("/updateCarousel/{imageId}")
    @ApiOperation("修改轮播图")
    public Result<?> updateCarousel(
            @PathVariable Long imageId,
            @RequestBody Carousel carousel
    ) {
        carousel.setImageId(imageId);
        carouselService.updateCarousel(carousel);
        return Result.success("更新成功");
    }

    @DeleteMapping("/deleteCarousel/{imageId}")
    @ApiOperation("删除轮播图")
    public Result<?> deleteCarousel(@PathVariable Long imageId) {
        carouselService.deleteCarousel(imageId);
        return Result.success("删除成功");
    }
}
