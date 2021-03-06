package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ClientReceiver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class FoodExercisesCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_FOOD = "food";
    private static final String PARAM_EXERCISES = "exercises";
    private static final String CLIENT_CABINET = "/jsp/client/client_cabinet.jsp";
    private ClientReceiver receiver;

    public FoodExercisesCommand(ClientReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
        Client client = (Client) request.getSession(true).getAttribute("client");
        String actionButton = request.getParameter("Refuse");
        if(actionButton.equals("Отказ")){
            actionButton = "Refuse";
        }
        if(actionButton.equals("Refuse")){
            //удаляем
        }
        String page;
        try {
            request.getSession().setAttribute(PARAM_EXERCISES,receiver.findAllExercisesForClients(client.getIdClient()));
            request.getSession().setAttribute(PARAM_FOOD, receiver.findAllFoodForClients(client.getIdClient()));
            LOGGER.log(Level.INFO,"Set attribute food and exercises");
            page = CLIENT_CABINET;
            return new CommandPair(CommandPair.DispatchType.FORWARD, page);
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}