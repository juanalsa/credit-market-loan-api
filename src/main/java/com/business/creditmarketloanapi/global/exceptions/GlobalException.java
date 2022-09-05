package com.business.creditmarketloanapi.global.exceptions;

import com.business.creditmarketloanapi.global.dto.ErrorResponseDTO;
import com.business.creditmarketloanapi.global.utils.Operations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {
    private List<String> messages = new ArrayList<>();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> throwGeneralException(Exception e) {
        messages.clear();
        messages.add(e.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, messages));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> throwNotFoundException(ResourceNotFoundException e) {
        messages.clear();
        messages.add(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(HttpStatus.NOT_FOUND, messages));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> throwValidationException(MethodArgumentNotValidException e) {
        messages.clear();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            messages.add(err.getDefaultMessage());
        });
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST, messages));
    }

    @ExceptionHandler(AttributeException.class)
    public ResponseEntity<ErrorResponseDTO> throwAttributeException(AttributeException e) {
        messages.clear();
        messages.add(e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST, messages));
    }
}
