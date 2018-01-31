package com.tsyrulik.dmitry.model.exception;

public class LogicFitnessException extends Exception {
    public LogicFitnessException() {
    }

    public LogicFitnessException(String message) {
        super(message);
    }

    public LogicFitnessException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicFitnessException(Throwable cause) {
        super(cause);
    }
}