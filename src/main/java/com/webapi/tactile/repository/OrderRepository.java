package com.webapi.tactile.repository;

import com.webapi.tactile.entities.AppUsersEntity;
import com.webapi.tactile.entities.OrdersEntity;
import com.webapi.tactile.service.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrdersEntity, Integer> {
}
