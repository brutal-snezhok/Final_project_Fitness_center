package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.*;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.*;
import com.tsyrulik.dmitry.model.manager.MessageManager;
import com.tsyrulik.dmitry.model.validator.SignUpValdator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class LoginCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PATH_PAGE_LOGIN = "/jsp/login.jsp";
    //private static final String PATH_PAGE_MAIN = "/jsp/main.jsp";
    private static final String PATH_PAGE_MAIN_CLIENT = "/jsp/client/client_cabinet.jsp";
    private static final String PATH_PAGE_MAIN_TRAINER = "/jsp/trainer/trainer_cabinet.jsp";
    private static final String PATH_PAGE_MAIN_ADMIN = "/jsp/admin/admin_page.jsp";
    private UserReceiver receiver;
    private ClientReceiver clientReceiver;
    private TrainerReceiver trainerReceiver;
    private ReviewReceiver reviewReceiver;
    private OrderReceiver orderReceiver;

    public LoginCommand(UserReceiver receiver, ClientReceiver clientReceiver, TrainerReceiver trainerReceiver,
                        ReviewReceiver reviewReceiver, OrderReceiver orderReceiver) {
        this.receiver = receiver;
        this.clientReceiver = clientReceiver;
        this.trainerReceiver = trainerReceiver;
        this.reviewReceiver = reviewReceiver;
        this.orderReceiver = orderReceiver;
    }

    @Override
    public CommandPair execute(HttpServletRequest request) throws CommandFitnessException {
       String page = null;
       String loginValue = request.getParameter(PARAM_LOGIN);
       String passValue = request.getParameter(PARAM_PASSWORD);

        if (SignUpValdator.isUserPasswordCorrect(passValue) && SignUpValdator.isUserEmailCorrect(loginValue)){
            try {
                    User user = receiver.checkUser(loginValue, passValue);
                    if (user != null){
                        List<Trainer> trainers = trainerReceiver.findAllTrainers();
                        request.getSession(true).setAttribute("user", user);
                        request.getSession().setAttribute("trainers", trainers);
                        request.getSession().setAttribute("reviews", reviewReceiver.findAllReviews());

                        if (user.getRole().equals(UserType.ADMIN.getTypeName())){
                            List<Client> clients = clientReceiver.findAllClients();
                            request.getSession().setAttribute("clients", clients);
                            page = PATH_PAGE_MAIN_ADMIN;
                        }
                        else if (user.getRole().equals(UserType.TRAINER.getTypeName())){
                            Trainer trainer = trainerReceiver.findTrainerByEmail(loginValue);
                            request.getSession().setAttribute("trainer", trainer);
                            List<Client> clientListOfThisTrainer = clientReceiver.findAllClientsOfThisTrainer(trainer.getIdTrainer());
                            request.getSession().setAttribute("clientsOfTrainer", clientListOfThisTrainer);
                            List<ClientInf> clientInfList = new ArrayList<>();
                            for (Client client : clientListOfThisTrainer){
                                List<Food> foods = clientReceiver.findAllFoodForClients(client.getIdClient());
                                List<Exercises> exercises = clientReceiver.findAllExercisesForClients(client.getIdClient());
                               // Order order = orderReceiver.findOrdertByClientEmail(client.getEmail());
                                clientInfList.add(new ClientInf(client, foods, exercises));
                            }

                           request.getSession().setAttribute("clientInfList", clientInfList);
                            page = PATH_PAGE_MAIN_TRAINER;
                        }
                        else{
                            Client client = clientReceiver.findClientByEmail(loginValue);
                            request.getSession().setAttribute("client", client);
                            List<Food> foods = clientReceiver.findAllFoodForClients(client.getIdClient());
                            request.getSession().setAttribute("foods", foods);
                            List<Exercises> exercises = clientReceiver.findAllExercisesForClients(client.getIdClient());
                            request.getSession().setAttribute("exercises", exercises);
                            page = PATH_PAGE_MAIN_CLIENT;
                        }
                    }
                else{
                    request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("messages.login.error"));
                    page = PATH_PAGE_LOGIN;
                }
            } catch (LogicFitnessException e) {
                request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("messages.login.error"));
                throw new CommandFitnessException(e);
            }
        }
        else{
            request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("messages.login.empty"));
        }
        return  new CommandPair(CommandPair.DispatchType.REDIRECT, page);
    }
}