package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.UserReceiver;

public enum CommandType {
    LOGIN(new LoginCommand(new UserReceiver())),
    LOGOUT(new LogoutCommand());
    //ALL_ABONENTS(new AbonentCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
