package com.webapi.tactile.service;


import com.webapi.tactile.entities.OrdersEntity;
import com.webapi.tactile.entities.ProductsEntity;
import com.webapi.tactile.models.OrderData;
import com.webapi.tactile.repository.AppUserRepository;
import com.webapi.tactile.repository.OrderRepository;
import com.webapi.tactile.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Service
public class OrderServiceImpl implements OrderService {

    // == fields ==
    OrderRepository orderRepository;
    AppUserRepository userRepository;
    ProductRepository productRepository;

    // == constructors ==
    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            AppUserRepository userRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // == public methods ==
    @Override
    public Integer createOrder(OrderData orderData, String email) {

        int userId = userRepository.findByEmail(email).get().getId();

        //Mappa OrderData och email till en OrdersEntity:
        OrdersEntity orderEntity = new OrdersEntity();
        orderEntity.setAppUserId(userId);
        orderEntity.setCreationDate(new java.sql.Date(new Date().getTime()));
        orderEntity.setTotalPrice(orderData.getTotalPrice());
        orderEntity.setPaymentReference(orderData.getPaymentReference());

        //Skapa kollektion för orderrader:
        Collection<ProductsEntity> orderRows = new ArrayList<>();

        //Loopa genom array i OrderData och skapa en orderrad för varje produktId i arrayen:
        for (Integer productId: orderData.getProductIds())
        {
            orderRows.add(productRepository.getById(productId));
        }

        //Här settar vi kollektionen i OrdersEntity:
        orderEntity.setProductToOrdersById(orderRows);

        //Sparar orderentity med orderrader:
        var finalOrder = orderRepository.save(orderEntity);
        return finalOrder.getId();
    }
}
