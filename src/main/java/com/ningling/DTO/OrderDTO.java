package com.ningling.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private long userId;//用户id
    private long productId;//用户id
    private String address;//地址
    private int quantity;//数量
    private double price;//总金额
    private double totalAmount;//总金额
    private String phone;//手机号
    private String remark;//备注
    private int status;//状态0:待付款,1待发货,2待收货,3已退款
}
