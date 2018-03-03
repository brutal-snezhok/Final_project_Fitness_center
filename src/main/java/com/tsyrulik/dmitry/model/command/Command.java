package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.exception.CommandFitnessException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandPair execute(HttpServletRequest request) throws CommandFitnessException;
    // void refresh -возврат на ту же самую страницу
    // изнутри команды нельзя делать forward или redirect!!!!
}
