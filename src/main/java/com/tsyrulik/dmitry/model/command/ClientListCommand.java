package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.Trainer;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ClientReceiver;
import com.tsyrulik.dmitry.model.logic.TrainerReceiver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientListCommand implements Command{
    private static final String PARAM_CLIENTS = "clients";
    private static final String PARAM_TRAINERS = "trainers";
    private static final String ADMIN_PAGE = "/jsp/admin/admin_page";
    private ClientReceiver receiverClient;
    private TrainerReceiver receiverTrainer;

    public ClientListCommand(ClientReceiver receiverClient, TrainerReceiver receiverTrainer) {
        this.receiverClient = receiverClient;
        this.receiverTrainer = receiverTrainer;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandFitnessException {
        String page;
        try {
            List<Client> clients = receiverClient.findAllClients();
            List<Trainer> trainers = receiverTrainer.findAllTrainers();
            request.getSession().setAttribute(PARAM_CLIENTS,clients);
            request.getSession().setAttribute(PARAM_TRAINERS,trainers);
            page = ADMIN_PAGE;
            return page;
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}