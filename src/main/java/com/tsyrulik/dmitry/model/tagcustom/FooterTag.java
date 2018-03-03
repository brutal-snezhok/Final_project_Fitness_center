package com.tsyrulik.dmitry.model.tagcustom;

import com.tsyrulik.dmitry.model.manager.MessageManager;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {

    private static final String FOOTER = MessageManager.getMessage("messages.copyright");


    @Override
    public int doStartTag() {

        try {
            JspWriter writer = pageContext.getOut();
            writer.write(FOOTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}