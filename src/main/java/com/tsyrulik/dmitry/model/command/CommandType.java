package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.ClientReceiver;
import com.tsyrulik.dmitry.model.logic.TrainerReceiver;
import com.tsyrulik.dmitry.model.logic.UserReceiver;

public enum CommandType {
    LOGIN(new LoginCommand(new UserReceiver())),
    LOGOUT(new LogoutCommand()),
    SIGNUP(new RegistrationCommand(new UserReceiver())),
    ADMINCLIENT(new ClientListCommand(new ClientReceiver(), new TrainerReceiver())),
    ADMINTRAINER(new ClientListCommand(new ClientReceiver(), new TrainerReceiver())),
    CLIENTCABINET(new ListForClientCommand(new ClientReceiver()));


    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
