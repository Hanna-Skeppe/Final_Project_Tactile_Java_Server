package com.webapi.tactile.models;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderData {

    private BigDecimal totalPrice;
    private String paymentReference;
    private List<Integer> productIds;
}
