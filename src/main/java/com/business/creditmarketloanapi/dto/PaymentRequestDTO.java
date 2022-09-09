package com.business.creditmarketloanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Represents the data necessary to record the payment of a loan
 *
 * @author Julian Alvarado
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequestDTO {
    @NotNull(message = "Loan ID is required")
    private Long loan_id;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be greater than zero")
    private Double amount;
}
