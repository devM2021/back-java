package com.dev.exeptions;

public class AppResponseEntityException extends RuntimeException {
    public AppResponseEntityException() {
    }

    public AppResponseEntityException(String message) {
        super(message);
    }

    public AppResponseEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppResponseEntityException(Throwable cause) {
        super(cause);
    }
}
