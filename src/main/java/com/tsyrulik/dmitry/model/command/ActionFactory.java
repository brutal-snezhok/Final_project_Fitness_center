package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ActionFactory {
    private final static Logger LOGGER = LogManager.getLogger();

    public static Optional<Command> defineCommand(String commandName) throws CommandFitnessException {
        Optional<Command> current = Optional.empty();
        if(commandName == null){
            return current;
        }
        try{
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(type.getCommand());
        }
        catch (IllegalArgumentException e){
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new CommandFitnessException(e);
        }
        return current;
    }
}