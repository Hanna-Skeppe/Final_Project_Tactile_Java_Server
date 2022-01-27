package com.webapi.tactile.service;

import com.webapi.tactile.models.OrderData;

public interface OrderService {
    Integer createOrder(OrderData orderData, String email);

}
