package com.tsyrulik.dmitry.model.command;

import com.tsyrulik.dmitry.model.logic.DefaultReceiver;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command{
    private DefaultReceiver receiver = new DefaultReceiver();

    @Override
    public CommandPair execute(HttpServletRequest request) {

        return new CommandPair(CommandPair.DispatchType.FORWARD, receiver.getPath());
    }
}