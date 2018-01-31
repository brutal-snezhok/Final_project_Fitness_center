package com.tsyrulik.dmitry.model.exception;

public class CommandFitnessException extends Exception {
    public CommandFitnessException() {
    }

    public CommandFitnessException(String message) {
        super(message);
    }

    public CommandFitnessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandFitnessException(Throwable cause) {
        super(cause);
    }
}