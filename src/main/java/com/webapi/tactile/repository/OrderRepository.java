package com.webapi.tactile.repository;

import com.webapi.tactile.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrdersEntity, Integer> {
}
