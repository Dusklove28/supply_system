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
public class SuppilerPageQueryVO {

    private Long supplierId;//供应商Id
    private String name;//供应商姓名
    private String contactPerson;//联系人
    private String phone;//电话号
    private String email;//邮箱
    private String address;//地址
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}
