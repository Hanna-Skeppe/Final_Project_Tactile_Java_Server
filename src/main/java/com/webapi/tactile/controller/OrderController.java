package com.webapi.tactile.controller;


import com.webapi.tactile.entities.OrdersEntity;
import com.webapi.tactile.entities.ProductToOrderEntity;
import com.webapi.tactile.models.OrderData;
import com.webapi.tactile.models.UserVerification;
import com.webapi.tactile.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    // Den här funktionen ska lägga in:
    // - en rad i 'Order' i SQL
    // - flera rader i 'product_to_order' (varje rad motsvarar en produkt i varukorgen)

    // Testa att ett order objekt utan orderrader
    @PostMapping("/create")
    public String createOrder(@RequestBody OrderData orderData) { // Skapa egen modell som bara innehåller det vi behöver
        log.info("orderData={}", orderData);
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderService.createOrder(orderData, email);
        return "OK";
    }

    @PostMapping("/create/row") // lägga till en orderrad i befintlig Order
    public String createOrderRow(@RequestBody ProductToOrderEntity productRow) {
        return orderService.createOrderRow(productRow);
    }



//    @PostMapping("/verify_token")
//    public UserVerification getUserDetails() {
//        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return userService.verifyUser(email);
//    }

}
