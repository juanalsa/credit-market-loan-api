package com.business.creditmarketloanapi.dto;

import com.business.creditmarketloanapi.global.dto.DataResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 * Represents the general response structure of a request
 *
 * @author Julian Alvarado
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO {
    private HttpStatus status;

    @Autowired
    private DataResponseDTO response;
}
