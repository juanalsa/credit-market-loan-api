package com.business.creditmarketloanapi.global.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Represents the exceptions generated on validations to model properties
 *
 * @author Julian Alvarado
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AttributeException extends Exception {
    /**
     * Main exception constructor
     *
     * @param message The message generated or reported by the exception
     */
    public AttributeException(String message) {
        super(message);
    }
}
