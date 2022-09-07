package com.business.creditmarketloanapi.dto;

import com.business.creditmarketloanapi.global.dto.DataResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of a loan request
 * <p>
 * Please see the {@link com.business.creditmarketloanapi.global.dto.DataResponseDTO} class
 * that was used to encapsulate the different response types
 *
 * @author Julian Alvarado
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanResponseDTO extends DataResponseDTO {
    private Long loan_id;
    private Double installment;
}
