package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ClientReceiver;

import javax.servlet.http.HttpServletRequest;

public class ListForClientCommand implements Command {
    private static final String PARAM_FOOD = "food";
    private static final String PARAM_EXERCISES = "exercises";
    private static final String CLIENT_CABINET = "/jsp/client/client_cabinet.jsp";
    private ClientReceiver receiver;

    public ListForClientCommand(ClientReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandFitnessException {
        Client client = (Client) request.getSession(true).getAttribute("client");
        String page;
        try {
            request.getSession().setAttribute(PARAM_EXERCISES,receiver.findAllExercisesForClients(client.getIdClient()));
            request.getSession().setAttribute(PARAM_FOOD, receiver.findAllFoodForClients(client.getIdClient()));
            page = CLIENT_CABINET;
            return page;
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}