package com.tsyrulik.dmitry.model.manager;

import java.util.ResourceBundle;

public class MessageManager {
//    EN(ResourceBundle.getBundle("resources.message", new Locale("en", "US"))),
//    RU(ResourceBundle.getBundle("resources.message", new Locale("ru", "RU")));

    private final static ResourceBundle bundle = ResourceBundle.getBundle("messages");

    //private ResourceBundle bundle;

//    MessageManager(ResourceBundle bundle){
//        this.bundle = bundle;
//    }

    public static String getMessage(String key){
        return bundle.getString(key);
    }
}
