package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.UserReceiver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class I18nCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String PARAM_CHANGE_LANGUAGE = "changeLanguage";
    private static final String PARAM_PAGE_PATH = "pagePath";

    private UserReceiver receiver;

    public I18nCommand(UserReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute(PARAM_CHANGE_LANGUAGE);
        String page = request.getParameter(PARAM_PAGE_PATH);
        request.getSession().setAttribute(PARAM_CHANGE_LANGUAGE, receiver.changeLanguage(locale));
        // router.setPagePath(request.getContextPath()+receiver.returnSamePage(page));
        LOGGER.log(Level.INFO, "Changing language");
        return page;
    }
}