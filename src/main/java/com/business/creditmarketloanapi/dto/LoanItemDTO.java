package com.business.creditmarketloanapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Represents the information of the loan list item
 *
 * @author Julian Alvarado
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanItemDTO {
    private Long id;
    private Double amount;
    private Integer term;
    private Double rate;
    private Long user_id;
    private String target;
    private Date date;
}
