package com.tsyrulik.dmitry.model.tagcustom;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FooterTag extends TagSupport {

    @Override
    public int doStartTag() {
       String locale = (String) pageContext.getSession().getAttribute("changeLanguage");
       Locale current = ("en_US".equals(locale)) ? new Locale("en", "US") : new Locale("ru", "RU");
       ResourceBundle rb = ResourceBundle.getBundle("messages", current);
       String FOOTER = rb.getString("messages.copyright");
        try {
            JspWriter writer = pageContext.getOut();
            writer.write(FOOTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}