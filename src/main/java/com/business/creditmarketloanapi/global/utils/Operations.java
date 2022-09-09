package com.business.creditmarketloanapi.global.utils;

/**
 * Represents data transformation or formatting operations
 *
 * @author Julian Alvarado
 */
public class Operations {
    /**
     * Allows to remove brackets from a string
     *
     * @param message The string to modify
     * @return The modified string without brackets
     */
    public static String trimBrackets(String message) {
        return message.replaceAll("[\\[\\]]", "");
    }
}
