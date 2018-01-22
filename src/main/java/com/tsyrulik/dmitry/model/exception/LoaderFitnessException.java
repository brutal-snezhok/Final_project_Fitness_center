package com.tsyrulik.dmitry.model.exception;

public class LoaderFitnessException extends RuntimeException {
    public LoaderFitnessException() {
    }

    public LoaderFitnessException(String message) {
        super(message);
    }

    public LoaderFitnessException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoaderFitnessException(Throwable cause) {
        super(cause);
    }

    public LoaderFitnessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}