package com.business.creditmarketloanapi.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Represents the general structure for an error response
 *
 * @author Julian Alvarado
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponseDTO {
    private HttpStatus status;
    private List<String> response;
}
