package com.ningling.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPageQueryDTO implements Serializable {

    private int pageNum;//分页数量

    private int pageSize;//分页大小

    private String username;//用户名
}
