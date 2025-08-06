package com.ningling.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    private List<OrderPayment> orders;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderPayment {
        private Long orderId;
        private BigDecimal amount;
        private String paymentMethod;
        private List<OrderItem> items;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class OrderItem {
        private Long productId;
        private String name;
        private BigDecimal price;
        private Integer quantity;
        private String imgUrl;
    }
}
