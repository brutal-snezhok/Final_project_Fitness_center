package com.tsyrulik.dmitry.model.exception;

public class DAOFitnessException extends Exception {
    public DAOFitnessException() {
    }

    public DAOFitnessException(String message) {
        super(message);
    }

    public DAOFitnessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOFitnessException(Throwable cause) {
        super(cause);
    }

    public DAOFitnessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}