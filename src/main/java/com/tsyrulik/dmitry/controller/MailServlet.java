package com.tsyrulik.dmitry.controller;

import com.tsyrulik.dmitry.model.mail.MailThread;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/jsp/mailServlet")
public class MailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        Properties properties = new Properties();
        ServletContext context = getServletContext();
        String fileName = context.getInitParameter("mail");
        properties.load(context.getResourceAsStream(fileName));
        MailThread mailOperator = new MailThread(req.getParameter("to"), req.getParameter("subject"),
                req.getParameter("body"), properties);
        mailOperator.start();
        req.getRequestDispatcher("/jsp/common/sendResult.jsp").forward(req, resp);
    }
}