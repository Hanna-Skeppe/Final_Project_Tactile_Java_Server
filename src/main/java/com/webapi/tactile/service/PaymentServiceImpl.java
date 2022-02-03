package com.webapi.tactile.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.webapi.tactile.models.CheckoutItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PaymentServiceImpl implements PaymentService{

    @Value("${baseURL}")
    private String baseUrl;

    @Value("${stripe_secret_key}")
    private String apiKey;

    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItem checkoutItem) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("sek")
                .setUnitAmount( ((long) checkoutItem.getPrice()) * 100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItem.getProductName())
                                .build())
                .build();
    }

    SessionCreateParams.LineItem createSessionLineItem(CheckoutItem checkoutItem) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkoutItem))
                .setQuantity(Long.parseLong(String.valueOf(checkoutItem.getQuantity())))
                .build();
    }

    public Session createSession(List<CheckoutItem> checkoutItemList) throws StripeException {

        String successURL = baseUrl + "order/checkout";
        String failedURL = baseUrl + "order/cancelled";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();
        for (CheckoutItem checkoutItemDto : checkoutItemList) {
            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.KLARNA)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedURL)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl(successURL)
                .build();
        return Session.create(params);
    }
}
