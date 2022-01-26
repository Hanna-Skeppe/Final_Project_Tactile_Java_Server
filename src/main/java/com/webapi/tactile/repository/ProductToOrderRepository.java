package com.webapi.tactile.repository;

import com.webapi.tactile.entities.OrdersEntity;
import com.webapi.tactile.entities.ProductToOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductToOrderRepository  extends JpaRepository<ProductToOrderEntity, Integer> {
}
