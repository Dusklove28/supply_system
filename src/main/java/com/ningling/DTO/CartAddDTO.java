package com.ningling.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "a-b传递的数据模型")
public class CartAddDTO {

        @ApiModelProperty("用户id")
        private long userId;

        @ApiModelProperty("商品id")
        private long productId;

        @ApiModelProperty("商品名字")
        private String name;

        @ApiModelProperty("数量")
        private int quantity;

        @ApiModelProperty("价格")
        private double price;

        @ApiModelProperty("图片")
        private String imgUrl;

}
