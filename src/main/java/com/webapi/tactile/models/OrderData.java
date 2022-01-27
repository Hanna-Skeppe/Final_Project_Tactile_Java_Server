package com.webapi.tactile.models;


import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderData {

    // == fields ==
    @DecimalMin(value="0.0",inclusive = false, message= "Value must be positive")// Added Validation annotations
    private BigDecimal totalPrice;

    @NotBlank(message = "Must enter a payment reference")
    private String paymentReference;

    @NotEmpty(message = "Must enter at least one products")
    private List<Integer> productIds;
}
