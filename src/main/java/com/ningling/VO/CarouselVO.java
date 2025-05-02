package com.ningling.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回的轮播图数据")
public class CarouselVO {

    @ApiModelProperty("图片id")
    private long id;
    @ApiModelProperty("图片地址")
    private String imgUrl;
    @ApiModelProperty("图片标题")
    private String title;
}
