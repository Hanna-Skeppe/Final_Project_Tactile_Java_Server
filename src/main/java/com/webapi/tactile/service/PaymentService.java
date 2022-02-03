package com.webapi.tactile.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.webapi.tactile.models.CheckoutItem;

import java.util.List;

public interface PaymentService {
    Session createSession(List<CheckoutItem> checkoutItemList) throws StripeException;
}
