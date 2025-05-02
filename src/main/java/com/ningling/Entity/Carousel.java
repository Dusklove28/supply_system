package com.ningling.Entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Carousel {

    private long imageId;//图片id
    private String imageUrl;//图片地址
    private  String description;//图片描述
    private LocalDateTime createdAt;//创建时间
    private LocalDateTime updateAt;//更新时间
}
