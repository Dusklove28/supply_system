package com.ningling.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierPageQueryDTO implements Serializable {

    private int pageNum;//分页数量

    private int pageSize;//分页大小

    private String name;//用户名

    private String contactPerson;

    private String phone;
}
