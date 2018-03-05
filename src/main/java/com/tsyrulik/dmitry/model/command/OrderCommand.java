package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.Order;
import com.tsyrulik.dmitry.model.entity.Trainer;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.OrderReceiver;
import com.tsyrulik.dmitry.model.logic.TrainerReceiver;
import com.tsyrulik.dmitry.model.manager.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class OrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ORDER_PAGE = "/jsp/client/order.jsp";
    private static final String PARAM_RADIO_TRAINER = "selectTrainer";
    private static final String PARAM_SELECT_MODE = "listOfModes";
    private static final String PARAM_COUNT_OF_TRAINING = "countOfTraining";
    private TrainerReceiver receiverTrainer;
    private OrderReceiver orderReceiver;

    public OrderCommand(TrainerReceiver receiverTrainer, OrderReceiver orderReceiver) {
        this.receiverTrainer = receiverTrainer;
        this.orderReceiver = orderReceiver;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
        String page;
        String radioTrainer = request.getParameter(PARAM_RADIO_TRAINER);
        String selectMode = request.getParameter(PARAM_SELECT_MODE);
        String countOfTraining = request.getParameter(PARAM_COUNT_OF_TRAINING);
        Client client = (Client) request.getSession().getAttribute("client");
        Order order = new Order();
        try{
            Trainer trainer = receiverTrainer.findTrainerByEmail(radioTrainer);

            order.setTypeOfTraining(selectMode);
            order.setNumber_of_lessons(Integer.parseInt(countOfTraining));
            order.setIdClient(client.getIdClient());
            order.setIdTrainer(trainer.getIdTrainer());
            orderReceiver.createOrder(order);
            LOGGER.log(Level.INFO,"Create order by " + client.getEmail());

            request.setAttribute("successfullOrder", MessageManager.getMessage("messages.orderDone"));

            page = ORDER_PAGE;
        }
        catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
        return new CommandPair(CommandPair.DispatchType.REDIRECT, page);
    }

}