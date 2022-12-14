package com.business.creditmarketloanapi.global.exceptions;

import com.business.creditmarketloanapi.global.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Define the behavior and response to handle the different exceptions of the application
 *
 * @author Julian Alvarado
 */
@RestControllerAdvice
public class GlobalException {
    private List<String> messages = new ArrayList<>();

    /**
     * Exception handler for application in general
     *
     * @param e The exception caught by handler
     * @return HTTP response with status code and error messages reported by the exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> throwGeneralException(Exception e) {
        messages.clear();
        messages.add(e.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, messages));
    }

    /**
     * Exception handler for resources not found
     *
     * @param e The exception ResourceNotFoundException caught by the handler
     * @return HTTP response with status code and error messages reported by the exception
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> throwNotFoundException(ResourceNotFoundException e) {
        messages.clear();
        messages.add(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(HttpStatus.NOT_FOUND, messages));
    }

    /**
     * Exception handler for errors generated by invalid arguments or parameters
     *
     * @param e The exception MethodArgumentNotValidException caught by the handler
     * @return HTTP response with status code and error messages reported by the exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> throwValidationException(MethodArgumentNotValidException e) {
        messages.clear();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            messages.add(err.getDefaultMessage());
        });
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST, messages));
    }

    /**
     * Exception handler for errors generated by model property validation
     *
     * @param e The exception AttributeException caught by the handler
     * @return HTTP response with status code and error messages reported by the exception
     */
    @ExceptionHandler(AttributeException.class)
    public ResponseEntity<ErrorResponseDTO> throwAttributeException(AttributeException e) {
        messages.clear();
        messages.add(e.getMessage());
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST, messages));
    }

    /**
     * Exception handler for errors generated by validating request parameters or
     * path variables in the Controller
     *
     * @param e The exception ConstraintViolationException caught by the handler
     * @return HTTP response with status code and error messages reported by the exception
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> throwConstraintViolationException(ConstraintViolationException e) {
        messages.clear();
        e.getConstraintViolations().forEach((cv) -> messages.add(cv.getMessage()));
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST, messages));
    }
}
