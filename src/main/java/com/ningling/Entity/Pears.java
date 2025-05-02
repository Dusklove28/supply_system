package com.ningling.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class Pears {
    private long productId;//编号
    private String name;//名字
    private String imageUrl;//图片地址
    private String description;//描述
    private double price;//价格
    private int stock;//库存
    private String classification;//分类
    private String brand;//品牌
    private int supplierId;//供应商Id
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
