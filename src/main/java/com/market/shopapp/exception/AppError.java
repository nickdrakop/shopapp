/**
 @author nick.drakopoulos
 */

package com.market.shopapp.exception;

public enum AppError {

    ERROR_FINDING_PRODUCTS(1, "One or more products were not found in the database."),
    INVALID_DATE_FORMAT(2, "Invalid format for date parameter.");

    private final int code;
    private final String description;

    AppError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getErrorCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
