package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.DefaultReceiver;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{
    private DefaultReceiver receiver = new DefaultReceiver();

    public LogoutCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return receiver.getPath();
    }
}