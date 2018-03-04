package com.tsyrulik.dmitry.controller;


import com.tsyrulik.dmitry.model.command.ActionFactory;
import com.tsyrulik.dmitry.model.command.Command;
import com.tsyrulik.dmitry.model.command.CommandPair;
import com.tsyrulik.dmitry.model.command.EmptyCommand;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.manager.LocaleManager;
import com.tsyrulik.dmitry.model.manager.MessageManager;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/jsp/controller")
public class Controller extends HttpServlet {
    private final static Logger LOGGER = LogManager.getLogger("Controller");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter("command"));
            Command command = commandOptional.orElse(new EmptyCommand());

            CommandPair commandPair = command.execute(request);
            String locale = (String) request.getSession().getAttribute("changeLanguage");
            System.out.println(locale);
            LocaleManager localeManager = LocaleManager.defineLocale(locale);

            if (commandPair.getDispatchType() == CommandPair.DispatchType.FORWARD) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(commandPair.getPage());
                dispatcher.forward(request, response);
            } else {
                String defaultPage = "/index.jsp";
                if (commandPair.getPage().isEmpty()) {
                    request.getSession().setAttribute("nullPage", localeManager.getMessage("messages.nullpage"));
                    response.sendRedirect(request.getContextPath() + defaultPage);
                }
                String page = commandPair.getPage();

                //request.getSession().setAttribute("pagePath", page);
                System.out.println(request.getContextPath() + page);
                response.sendRedirect(request.getContextPath() + page);
            }

        } catch (CommandFitnessException e) {
            request.getSession().setAttribute("nullPage", MessageManager.getMessage("messages.nullpage"));
            LOGGER.log(Level.ERROR, e.getMessage());
            request.getRequestDispatcher("/jsp/error/error.jsp").forward(request, response);
        }

    }


    @Override
    public void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        for (int i = 0; i < connectionPool.poolSize(); i++) {
            try {
                connectionPool.closeConnection(connectionPool.getConnection());
            } catch (PoolFitnessException exc) {
                LOGGER.log(Level.ERROR, exc.getMessage());
            }
        }
    }
}

