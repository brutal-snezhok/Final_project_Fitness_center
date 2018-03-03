package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ClientReceiver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AdminClientCommand implements Command {
    private static final String PARAM_CLIENTS = "clients";
    private static final String ADMIN_PAGE = "/jsp/admin/admin_page.jsp";
    private ClientReceiver receiverClient;

    private static final String PARAM_ID_CLIENT = "idClient";
    private static final String PARAM_NAME_CLIENT = "nameClient";
    private static final String PARAM_SURNAME_CLIENT = "surnameClient";
    private static final String PARAM_YEARS_OLD_CLIENT = "yearOldClient";
    private static final String PARAM_SEX_CLIENT = "sexClient";
    private static final String PARAM_CHECKBOX_CLIENT = "selectClient";

    public AdminClientCommand(ClientReceiver receiverClient) {
        this.receiverClient = receiverClient;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
        String[] checkboxClient = request.getParameterValues(PARAM_CHECKBOX_CLIENT);
        ArrayList<Client> clients = new ArrayList<>();


        String actionButtonRemoveClient = request.getParameter("RemoveClientButton");
        String actionButtonUpdateClient = request.getParameter("UpdateClientButton");

        String page;
        try {
            if(checkboxClient != null){
                for (int i = 0; i < checkboxClient.length; i++){
                    Client currentClient = receiverClient.findClientByEmail(checkboxClient[i]);
                    clients.add(currentClient);
                }
            }

            for(int i = 0; i < clients.size(); i++) {
                if (actionButtonRemoveClient != null) {
                    receiverClient.deleteClient(clients.get(i).getIdClient());
                }

                if (actionButtonUpdateClient!= null){
                    int idClient = Integer.parseInt(request.getParameter(PARAM_ID_CLIENT));
                    String name = request.getParameter(PARAM_NAME_CLIENT);
                    String surname = request.getParameter(PARAM_SURNAME_CLIENT);
                    String yearsOld = request.getParameter(PARAM_YEARS_OLD_CLIENT);
                    String sex = request.getParameter(PARAM_SEX_CLIENT);
                    Client client = receiverClient.findClientById(idClient);
                    client.setName(name);
                    client.setSurname(surname);
                    client.setYearOld(Integer.parseInt(yearsOld));
                    client.setSex(sex);
                    receiverClient.updateClient(client);
                }
            }

            request.getSession().setAttribute(PARAM_CLIENTS, receiverClient.findAllClients());
            page = ADMIN_PAGE;
            return new CommandPair(CommandPair.DispatchType.REDIRECT, page);
        } catch (LogicFitnessException e) {
            throw new CommandFitnessException(e.getMessage(), e);
        }
    }

}