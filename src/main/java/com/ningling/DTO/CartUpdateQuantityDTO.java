package com.ningling.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartUpdateQuantityDTO implements Serializable {
    private int quantity;//数量
}
