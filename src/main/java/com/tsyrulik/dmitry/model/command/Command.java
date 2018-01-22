package com.tsyrulik.dmitry.model.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);
    // void refresh -возврат на ту же самую страницу
    // изнутри команды нельзя делать forward или redirect!!!!
}
