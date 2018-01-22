package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.UserReceiver;
import com.tsyrulik.dmitry.model.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PATH_PAGE_LOGIN = "/jsp/login.jsp";
    private static final String PATH_PAGE_MAIN = "/jsp/main.jsp";
    private UserReceiver receiver;

    public LoginCommand(UserReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) {
       String page = null;
       String loginValue = request.getParameter(PARAM_LOGIN);
       String passValue = request.getParameter(PARAM_PASSWORD);
       //validation
        if (loginValue != null && !loginValue.isEmpty() && passValue != null && !passValue.isEmpty()){
            if (receiver.checkUser(loginValue, passValue)){
                request.setAttribute("user", loginValue);
                page = PATH_PAGE_MAIN;
            }
            else{
                request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("message.loginerror"));
                page = PATH_PAGE_LOGIN;
            }
        }
        else{
            request.setAttribute("errorLoginPassMessage", MessageManager.getMessage("message.loginempty"));
        }
        return page;
    }
}