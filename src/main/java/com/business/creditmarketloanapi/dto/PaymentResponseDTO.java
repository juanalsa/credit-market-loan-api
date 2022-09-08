package com.business.creditmarketloanapi.dto;

import com.business.creditmarketloanapi.global.dto.DataResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of a payment record
 * <p>
 * Please see the {@link com.business.creditmarketloanapi.global.dto.DataResponseDTO} class
 * that was used to encapsulate the different response types
 *
 * @author Julian Alvarado
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponseDTO extends DataResponseDTO {
    private Long id;
    private Long loan_id;
    private Double debt;
}
