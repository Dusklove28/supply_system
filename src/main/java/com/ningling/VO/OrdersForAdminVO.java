package com.ningling.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersForAdminVO {
    private long orderId;//订单Id
    private long userId;//用户Id
    private long productId;//商品Id
    private String username;//用户Id
    private String productName;//商品Id
    private double totalAmount;//总金额
    private int status;//状态
    private int quantity;//数量
    private double price;//单价
    private String address;//地址
    private String remark;//备注
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private PearsListVO items;
}
