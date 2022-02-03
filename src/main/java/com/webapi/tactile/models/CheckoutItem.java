package com.webapi.tactile.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutItem {

    private String productName;
    private int quantity;
    private double price;
    private long productId;
    private int userId;

}
