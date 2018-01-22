package com.tsyrulik.dmitry.model.exception;

public class PoolFitnessException extends RuntimeException {

    public PoolFitnessException() {
        super();
    }

    public PoolFitnessException(String message) {
        super(message);
    }

    public PoolFitnessException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolFitnessException(Throwable cause) {
        super(cause);
    }

    protected PoolFitnessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}