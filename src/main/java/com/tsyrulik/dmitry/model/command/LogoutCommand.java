package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.logic.DefaultReceiver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{
    private DefaultReceiver receiver = new DefaultReceiver();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PARAM_CHANGE_LANGUAGE = "changeLanguage";
    private static final String PARAM_USER = "user";

    public LogoutCommand() {
    }

    @Override
    public CommandPair execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(PARAM_USER);
        Object locale = request.getSession().getAttribute(PARAM_CHANGE_LANGUAGE);

        String page = receiver.getPath();
        System.out.println(page);
        request.getSession().invalidate();

        request.getSession().setAttribute(PARAM_CHANGE_LANGUAGE,locale);
        LOGGER.log(Level.INFO,user.getEmail() +" log out");
        return new CommandPair(CommandPair.DispatchType.FORWARD, page);
    }
}