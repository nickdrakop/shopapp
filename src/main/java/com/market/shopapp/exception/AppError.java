/**
 @author nick.drakopoulos
 */

package com.market.shopapp.exception;

public enum AppError {

    ERROR_FINDING_PRODUCTS(1, "One or more products were not found in the database.");

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
