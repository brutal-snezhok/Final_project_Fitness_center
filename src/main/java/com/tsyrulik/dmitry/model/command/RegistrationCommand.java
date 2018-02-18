package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.Trainer;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.entity.UserType;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.ClientReceiver;
import com.tsyrulik.dmitry.model.logic.TrainerReceiver;
import com.tsyrulik.dmitry.model.logic.UserReceiver;
import com.tsyrulik.dmitry.model.manager.MessageManager;
import com.tsyrulik.dmitry.model.validator.RegisterValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger("RegistrationCommand");
    private static final String PARAM_NAME = "first_name";
    private static final String PARAM_SURNAME = "second_name";
    private static final String PARAM_YEARS_OLD = "years_old";
    private static final String PARAM_SEX = "radio-sex";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_ROLE = "radio-role";

    private static final String PATH_PAGE_LOGIN = "/jsp/login.jsp";
    private static final String PATH_PAGE_REGISTER = "/jsp/register.jsp";

    private UserReceiver receiver;
    private ClientReceiver clientReceiver = new ClientReceiver();
    private TrainerReceiver trainerReceiver = new TrainerReceiver();

    public RegistrationCommand(UserReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandFitnessException {
        String page = null;
        String nameValue = request.getParameter(PARAM_NAME);
        String surnameValue = request.getParameter(PARAM_SURNAME);
        String yersOldValue = request.getParameter(PARAM_YEARS_OLD);
        String sex = request.getParameter(PARAM_SEX);
        String emailValue = request.getParameter(PARAM_EMAIL);
        String passValue = request.getParameter(PARAM_PASSWORD);
        String clientOrTrainer = request.getParameter(PARAM_ROLE);
        //receiver
        if (RegisterValidator.checkRegistration(nameValue, surnameValue, yersOldValue, emailValue,passValue)) {
            if (clientOrTrainer.equals("Client")){
                Client client = new Client(new User(nameValue, surnameValue, Integer.parseInt(yersOldValue),
                        sex, emailValue, passValue, String.valueOf(UserType.CLIENT.ordinal())),(double)0);
                request.getSession(true).setAttribute("client", client);
                try {
                    clientReceiver.addClient(client);
                } catch (LogicFitnessException e) {
                    throw new CommandFitnessException(e);
                }
            }
            else {
                Trainer trainer = new Trainer(new User(nameValue, surnameValue, Integer.parseInt(yersOldValue),
                        sex, emailValue, passValue, String.valueOf(UserType.TRAINER.ordinal())));
                request.getSession(true).setAttribute("trainer", trainer);
                try {
                    trainerReceiver.addTrainer(trainer);
                } catch (LogicFitnessException e) {
                    throw new CommandFitnessException();
                }
            }
            request.setAttribute("successMessage", MessageManager.getMessage("messages.registration.success"));
            page = PATH_PAGE_LOGIN;
            LOGGER.info(nameValue + " is registered now");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("messages.login.error"));
            page = PATH_PAGE_REGISTER;
        }
        return page;
    }
}