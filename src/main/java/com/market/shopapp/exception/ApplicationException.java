/**
 @author nick.drakopoulos
 */

package com.market.shopapp.exception;

public class ApplicationException extends RuntimeException {
    private AppError appError;

    public ApplicationException(AppError appError, Throwable ex) {
        super(ex);
        this.appError = appError;
    }

    public ApplicationException(AppError appError) {
        super(appError.getDescription());
        this.appError = appError;
    }

    public ApplicationException(AppError appError, String message) {
        super(message);
        this.appError = appError;
    }

    public AppError getAppError() {
        return appError;
    }

    public void setAppError(AppError appError) {
        this.appError = appError;
    }
}
