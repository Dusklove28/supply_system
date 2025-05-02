package com.ningling.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private long userId;//复合主键1用户id
    private long productId;//复合主键2商品id
    private String name;//商品名称
    private String imageUrl;//图片链接
    private int quantity;//数量
    private double price;//价格
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
