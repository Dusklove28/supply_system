package com.ningling.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderClearDTO {
    private Long userId;
    private List<OrderDTO> items;
    private double totalAmount;
    private Integer status;
}
