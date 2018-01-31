package com.tsyrulik.dmitry.model.manager;

import java.util.ResourceBundle;

public class MessageManager {
//    EN(ResourceBundle.getBundle("resources.message", new Locale("en", "US"))),
//    RU(ResourceBundle.getBundle("resources.message", new Locale("ru", "RU")));

    private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");

    public static String getMessage(String key){
        return BUNDLE.getString(key);
    }
}
