package com.ningling.VO;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
public class CartsItemVO {

    @ApiModelProperty("商品id")
    private long id;
    @ApiModelProperty("图片url")
    private String imgUrl;
    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("商品价格")
    private double price;
    @ApiModelProperty("商品数量")
    private int quantity;
}
