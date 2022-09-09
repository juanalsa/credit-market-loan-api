package com.business.creditmarketloanapi.dto;

import com.business.creditmarketloanapi.global.dto.DataResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the debt response of a loan
 *
 * @author Julian Alvarado
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DebtResponseDTO extends DataResponseDTO {
    private Double balance;
}
