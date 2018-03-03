package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ClientReceiver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientListCommand implements Command{
    private static final String PARAM_CLIENTS = "clients";
    private static final String ADMIN_PAGE = "/jsp/admin/admin_page.jsp";
    private ClientReceiver receiverClient;

    public ClientListCommand(ClientReceiver receiverClient) {
        this.receiverClient = receiverClient;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
        String page;
        try {
            List<Client> clients = receiverClient.findAllClients();
            request.getSession().setAttribute(PARAM_CLIENTS,clients);
            page = ADMIN_PAGE;
            return  new CommandPair(CommandPair.DispatchType.REDIRECT, page);
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }
}