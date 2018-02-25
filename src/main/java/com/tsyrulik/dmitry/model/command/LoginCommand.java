package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.entity.UserType;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.logic.UserReceiver;
import com.tsyrulik.dmitry.model.manager.MessageManager;
import com.tsyrulik.dmitry.model.validator.SignUpValdator;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PATH_PAGE_LOGIN = "/jsp/login.jsp";
    //private static final String PATH_PAGE_MAIN = "/jsp/main.jsp";
    private static final String PATH_PAGE_MAIN_CLIENT = "/jsp/client/client_cabinet.jsp";
    private static final String PATH_PAGE_MAIN_TRAINER = "/jsp/trainer/trainer_cabinet.jsp";
    private static final String PATH_PAGE_MAIN_ADMIN = "/jsp/admin/admin_page.jsp";
    private UserReceiver receiver;

    public LoginCommand(UserReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandFitnessException {
       String page = null;
       String loginValue = request.getParameter(PARAM_LOGIN);
       String passValue = request.getParameter(PARAM_PASSWORD);

       // дописать SignUpValdator.isUserEmailCorrect(loginValue)
        if (SignUpValdator.isUserPasswordCorrect(passValue)){
            try {
                    User user = receiver.checkUser(loginValue, passValue);
                    if (user != null){
                        request.getSession(true).setAttribute("user", user);
                        if (user.getRole().equals(UserType.ADMIN.getTypeName())){
                            page = PATH_PAGE_MAIN_ADMIN;
                        }
                        else if (user.getRole().equals(UserType.TRAINER.getTypeName())){
                            page = PATH_PAGE_MAIN_TRAINER;
                        }
                        else{
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
        return page;
    }
}