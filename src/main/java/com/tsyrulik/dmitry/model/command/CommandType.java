package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.*;

public enum CommandType {
    LOGIN(new LoginCommand(new UserReceiver(), new ClientReceiver(), new TrainerReceiver(), new ReviewReceiver())),
    LOGOUT(new LogoutCommand()),
    SIGN_UP(new RegistrationCommand(new UserReceiver())),
    ADMIN_CLIENT(new ClientListCommand(new ClientReceiver())),
    ADMIN_TRAINER(new TrainerListCommand(new TrainerReceiver())),
    CLIENT_CABINET(new FoodExercisesCommand(new ClientReceiver())),
    TRAINER_CABINET(new TrainerCommand(new ClientReceiver(), new TrainerReceiver())),
    UPDATE_CLIENT(new AdminClientCommand(new ClientReceiver())),
    UPDATE_TRAINER(new AdminTrainerCommand(new TrainerReceiver())),
    ORDER(new OrderCommand(new TrainerReceiver(), new OrderReceiver())),
    ADD_REVIEW(new AddReviewCommand(new ReviewReceiver())),
    DELETE_REVIEW(new DeleteReviewCommand(new ReviewReceiver()));



    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
