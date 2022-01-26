package com.webapi.tactile.service;

import com.webapi.tactile.entities.OrdersEntity;
import com.webapi.tactile.models.OrderData;

public interface OrderService {
    String createOrder(OrderData orderData, String email);

}
