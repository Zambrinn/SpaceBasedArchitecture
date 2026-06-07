package com.spacebased.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank(message = "Client name must not be blank")
        String clientName,
        @NotNull(message = "The total amount must not be null")
        @Positive(message = "The total amount must be greater than zero")
        BigDecimal totalAmount
) {
}
