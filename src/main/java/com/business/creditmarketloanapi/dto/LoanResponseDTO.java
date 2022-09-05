package com.business.creditmarketloanapi.dto;

import com.business.creditmarketloanapi.global.dto.DataResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanResponseDTO extends DataResponseDTO {
    private Long loan_id;
    private Double installment;
}
