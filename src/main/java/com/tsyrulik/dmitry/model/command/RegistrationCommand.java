package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.UserReceiver;
import com.tsyrulik.dmitry.model.manager.MessageManager;
import com.tsyrulik.dmitry.model.validator.RegisterValidator;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_YEARS_OLD = "years_old";
    private static final String PARAM_SEX = "sex";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";

    private static final String PATH_PAGE_LOGIN = "/jsp/login.jsp";
    private static final String PATH_PAGE_MAIN = "/jsp/main.jsp";

    private UserReceiver receiver;

    public RegistrationCommand(UserReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String nameValue = request.getParameter(PARAM_NAME);
        String surnameValue = request.getParameter(PARAM_SURNAME);
        String yersOldValue = request.getParameter(PARAM_YEARS_OLD);
        String sex = request.getParameter(PARAM_SEX);
        String emailValue = request.getParameter(PARAM_EMAIL);
        String passValue = request.getParameter(PARAM_PASSWORD);
        //receiver
        if (RegisterValidator.checkRegistration(nameValue, surnameValue, yersOldValue, emailValue,passValue)) {
            request.setAttribute("successMessage", MessageManager.getMessage("messages.registration.success"));
            //page = PropertyManager.getProperty("path.page.login");
            page = "/jsp/login.jsp";
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("messages.login.error"));
            //page = PropertyManager.getProperty("path.page.register");
            page = "/jsp/register.jsp";
        }
        return page;
    }
}