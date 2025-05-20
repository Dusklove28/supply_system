package com.ningling.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carousel {

    private long imageId;//图片id
    private String imageUrl;//图片地址
    private  String description;//图片描述
    private LocalDateTime createdAt;//创建时间
    private LocalDateTime updatedAt;//更新时间
}
