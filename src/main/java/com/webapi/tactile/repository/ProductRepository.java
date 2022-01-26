package com.webapi.tactile.repository;

import com.webapi.tactile.entities.OrdersEntity;
import com.webapi.tactile.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductsEntity, Integer> {
}
