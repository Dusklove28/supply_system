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
public class Supplier implements Serializable {
    private Long supplierId;//供应商ID
    private String name;//供应商姓名
    private String contactPerson;//联系人
    private String phone;//电话号
    private String email;//邮箱
    private String address;//地址
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
