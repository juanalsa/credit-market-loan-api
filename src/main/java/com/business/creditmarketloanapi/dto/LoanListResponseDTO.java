package com.business.creditmarketloanapi.dto;

import com.business.creditmarketloanapi.global.dto.DataResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the loan list information
 *
 * @author Julian Alvarado
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanListResponseDTO extends DataResponseDTO {
    private List<LoanItemDTO> loanList;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;
}
