package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.ClientReceiver;
import com.tsyrulik.dmitry.model.logic.TrainerReceiver;
import com.tsyrulik.dmitry.model.logic.UserReceiver;

public enum CommandType {
    LOGIN(new LoginCommand(new UserReceiver())),
    LOGOUT(new LogoutCommand()),
    SIGNUP(new RegistrationCommand(new UserReceiver())),
    ADMINCLIENT(new ClientListCommand(new ClientReceiver())),
    ADMINTRAINER(new TrainerListCommand(new TrainerReceiver())),
    CLIENTCABINET(new FoodExercisesCommand(new ClientReceiver())),
    TRAINERCABINET(new TrainerCommand(new ClientReceiver(), new TrainerReceiver())),
    UPDATECLIENT(new AdminClientCommand(new ClientReceiver())),
    UPDATETRAINER(new AdminTrainerCommand(new TrainerReceiver()));



    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
