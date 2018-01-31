package com.tsyrulik.dmitry.controller;


import com.tsyrulik.dmitry.model.command.ActionFactory;
import com.tsyrulik.dmitry.model.command.Command;
import com.tsyrulik.dmitry.model.command.EmptyCommand;
import com.tsyrulik.dmitry.model.exception.CommandFitnessException;
import com.tsyrulik.dmitry.model.manager.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet("/controller")
public class Controller extends HttpServlet {

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
            String page = command.execute(request);

            if (page != null){
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
            else{
                request.getSession().setAttribute("nullPage", MessageManager.getMessage("messages.nullpage"));
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (CommandFitnessException e) {
            e.printStackTrace();
        }


}
}

