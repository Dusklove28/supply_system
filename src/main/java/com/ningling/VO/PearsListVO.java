package com.ningling.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回的商品数据格式")
public class PearsListVO {
    @ApiModelProperty("主键值")
    private long id;//编号
    @ApiModelProperty("商品名字")
    private String name;//名字
    @ApiModelProperty("商品简介")
    private String description;//描述
    @ApiModelProperty("价格")
    private double price;//价格
    @ApiModelProperty("图片链接")
    private String imgUrl;//描述
}
