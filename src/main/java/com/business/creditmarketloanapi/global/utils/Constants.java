package com.business.creditmarketloanapi.global.utils;

import java.util.Set;

/**
 * Represents constants used in the different API functionalities to refer to
 * model values or identifiers
 *
 * @author Julian Alvarado
 */
public class Constants {
    public static final Integer DECIMAL_NUMBER_SCALE = 2;
    public static final String NEW_TARGET = "NEW";
    public static final Long NEW_TARGET_MIN_NUM_LOANS = 1L;
    public static final Long NEW_TARGET_MAX_NUM_LOANS = 2L;
    public static final Long NEW_TARGET_MIN_VOL_LOANS = 3L;
    public static final Long NEW_TARGET_MAX_VOL_LOANS = 4L;
    public static final Long NEW_TARGET_RATE = 5L;
    public static final Long NEW_TARGET_MAX_AMOUNT_LOANS = 6L;

    public static final String FREQUENT_TARGET = "FREQUENT";
    public static final Long FREQUENT_TARGET_MIN_NUM_LOANS = 7L;
    public static final Long FREQUENT_TARGET_MAX_NUM_LOANS = 8L;
    public static final Long FREQUENT_TARGET_MIN_VOL_LOANS = 9L;
    public static final Long FREQUENT_TARGET_MAX_VOL_LOANS = 10L;
    public static final Long FREQUENT_TARGET_RATE = 11L;
    public static final Long FREQUENT_TARGET_MAX_AMOUNT_LOANS = 12L;

    public static final String PREMIUM_TARGET = "PREMIUM";
    public static final Long PREMIUM_TARGET_MIN_NUM_LOANS = 13L;
    public static final Long PREMIUM_TARGET_MAX_NUM_LOANS = 14L;
    public static final Long PREMIUM_TARGET_MIN_VOL_LOANS = 15L;
    public static final Long PREMIUM_TARGET_MAX_VOL_LOANS = 16L;
    public static final Long PREMIUM_TARGET_RATE = 17L;
    public static final Long PREMIUM_TARGET_MAX_AMOUNT_LOANS = 18L;
    public static final Set<String> TARGET = Set.of("NEW", "FREQUENT", "PREMIUM");
}
