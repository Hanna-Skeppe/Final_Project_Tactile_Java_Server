package com.webapi.tactile.controller;

import com.stripe.exception.StripeException;
import com.webapi.tactile.models.CheckoutItem;
import com.webapi.tactile.models.OrderData;
import com.webapi.tactile.models.StripeResponse;
import com.webapi.tactile.service.EmailService;
import com.webapi.tactile.service.OrderServiceImpl;
import com.webapi.tactile.service.PaymentService;
import com.webapi.tactile.utils.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = Mappings.REACT_HOST)
@RestController
@RequestMapping(Mappings.ORDER)
public class OrderController {

    // == fields ==
    OrderServiceImpl orderService;
    PaymentService paymentService;
    EmailService emailService;
    // == constructors ==
    @Autowired
    public OrderController(
            OrderServiceImpl orderService,
            PaymentService paymentService,
            EmailService emailService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.emailService = emailService;
    }

    // == public methods ==
    @PostMapping(Mappings.CHECKOUT)
    public ResponseEntity<StripeResponse> checkoutSession(@RequestBody List<CheckoutItem> checkoutItemList) throws StripeException {
        Session session = paymentService.createSession(checkoutItemList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse,HttpStatus.OK);
    }

    // Den här funktionen ska lägga in:
    // - en rad i 'Order' i SQL
    // - flera rader i 'product_to_order' (varje rad motsvarar en produkt i varukorgen)

    @PostMapping(Mappings.CREATE)
    public Map<String, Integer> createOrder(@Valid @RequestBody OrderData orderData) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int confirmationNumber = orderService.createOrder(orderData, email); // Added confirmation number
        return Collections.singletonMap("confirmationNumber", confirmationNumber);
    }

    @PostMapping(Mappings.EMAILCONF)
    public String sendEmail(@RequestBody String orderConfText) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        emailService.sendSimpleMessage(email, "Order Confirmation: Tactile Crafts", orderConfText);
        return "email was sent";
    }

    //Added below will catch all validation errors, return bad_request with the errors.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
