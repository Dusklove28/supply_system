package com.ningling.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail implements Serializable {
    private long orderDetailId;//订单id
    private long orderId;//用户id
    private long productId;//用户id
    private int quantity;//数量
    private double price;//单个订单的数量
    private String address;//地址
    private String remark;//备注
    private LocalDateTime createdTime;//创建时间
    private LocalDateTime updatedTime;//更新时间
}
