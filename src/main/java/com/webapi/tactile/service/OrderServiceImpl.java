package com.webapi.tactile.service;

import com.webapi.tactile.entities.AppUsersEntity;
import com.webapi.tactile.entities.OrdersEntity;
import com.webapi.tactile.entities.ProductToOrderEntity;
import com.webapi.tactile.entities.ProductsEntity;
import com.webapi.tactile.models.OrderData;
import com.webapi.tactile.repository.AppUserRepository;
import com.webapi.tactile.repository.OrderRepository;
import com.webapi.tactile.repository.ProductRepository;
import com.webapi.tactile.repository.ProductToOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    ProductToOrderRepository productRowRepository;
    AppUserRepository userRepository;
    ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductToOrderRepository productRowRepository,
            AppUserRepository userRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRowRepository = productRowRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public String createOrder(OrderData orderData, String email) {

        int userId = userRepository.findByEmail(email).get().getId();

        //Mappa OrderData och email till en OrdersEntity:
        OrdersEntity orderEntity = new OrdersEntity();
        orderEntity.setAppUserId(userId); ;
        orderEntity.setCreationDate(new java.sql.Date(new Date().getTime()));
        orderEntity.setTotalPrice(orderData.getTotalPrice());
        orderEntity.setPaymentReference(orderData.getPaymentReference());

        //Skapa kollektion för orderrader
        Collection<ProductsEntity> orderRows = new ArrayList<>();

        //Loopa genom array i OrderData och skapa en orderrad för varje produktId i arrayen
        for (Integer productId: orderData.getProductIds())
        {
//            ProductToOrderEntity productToOrder = new ProductToOrderEntity();
//            productToOrder.setProductId(productId);
            orderRows.add(productRepository.getById(productId));
        }

        //Här settar vi kollektionen i OrdersEntity:
        orderEntity.setProductToOrdersById(orderRows);

        // sparar orderentity med orderrader:
        orderRepository.save(orderEntity);
        return "Order complete";
    }

    public String createOrderRow(ProductToOrderEntity productRow) {
        productRowRepository.save(productRow); // Sparar  orderrad
        return "Row added to Order";
    }


}
