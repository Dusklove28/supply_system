package com.ningling.service.impl;

import com.ningling.Entity.Carousel;
import com.ningling.VO.CarouselVO;
import com.ningling.mapper.CarouselMapper;
import com.ningling.service.CarouselService;
import com.ningling.utils.QiniuKodoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;
    @Autowired
    private QiniuKodoUtil qiniuKodoUtil;
    @Override
    public List<CarouselVO> getCarouselData() {
        //获取轮播图数据
        List<Carousel> carouselData = carouselMapper.getCarouselData();
        List<CarouselVO> carouselVO = new ArrayList<>();
//        LocalDateTime now = LocalDateTime.now();
//        // 格式化为 "yyyy-MM-dd HH:mm:ss"
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedTime = now.format(formatter);
        for (Carousel c : carouselData) {
            CarouselVO cVs = CarouselVO.builder()
                    .id(c.getImageId())
                    .imgUrl(c.getImageUrl())
                    .description(c.getDescription())
                    .build();
            carouselVO.add(cVs);
        }


        return carouselVO;
    }

    @Override
    public boolean addCarousel(Carousel carousel) {
        if(carouselMapper.insert(carousel)<=0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateCarousel(Carousel carousel) {
        if(carouselMapper.update(carousel) <= 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteCarousel(Long imageId) {
        Carousel carouselById = carouselMapper.getCarouselById(imageId);
        String url = carouselById.getImageUrl();
        //http://svjf1en7q.hd-bkt.clouddn.com/carousel%2F3c0f08d2-06bf-4b72-bec2-fbf544e2ddff.png
        // 1. 按 "%2F" 分割字符串
        String[] parts = url.split("%2F");

        // 2. 提取目标文件名
        String fileName = parts.length >= 2 ? parts[1] : "";

        // 3. 将文件名存入 String[] 数组
        String[] fileNameArray = { fileName };

        // 验证结果
        System.out.println(fileNameArray[0]); // 输出: 3c0f08d2-06bf-4b72-bec2-fbf544e2ddff.png

        if( carouselMapper.delete(imageId)<=0){
           return false;
       }

        qiniuKodoUtil.deleteFile(fileNameArray);

       return true;
    }
}
