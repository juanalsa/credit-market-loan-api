package com.business.creditmarketloanapi.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponseDTO {
    private HttpStatus status;
    private List<String> response;
}
