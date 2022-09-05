package com.business.creditmarketloanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanRequestDTO {
    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "Term is required")
    @Min(value = 1, message = "Term must be greater than zero")
    private Integer term;

    @NotNull(message = "User ID is required")
    private Long user_id;
}
