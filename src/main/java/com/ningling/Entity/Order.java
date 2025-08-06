package com.ningling.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Serializable {
    private Long orderId;//订单id
    private Long userId;//用户id
    private double totalAmount;//总金额
    private int status;//状态1:已支付 0:未支付
    private LocalDateTime createdTime;//创建时间
    private LocalDateTime updatedTime;//更新时间
}
