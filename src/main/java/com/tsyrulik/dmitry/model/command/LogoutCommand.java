package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.DefaultReceiver;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{
    private DefaultReceiver receiver = new DefaultReceiver();

    public LogoutCommand() {
    }

    @Override
    public CommandPair execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return  new CommandPair(CommandPair.DispatchType.REDIRECT,  receiver.getPath());
    }
}