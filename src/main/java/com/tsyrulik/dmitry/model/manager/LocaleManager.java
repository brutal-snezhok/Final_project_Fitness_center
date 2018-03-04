package com.tsyrulik.dmitry.model.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocaleManager {
    EN(ResourceBundle.getBundle("messages", new Locale("en", "US"))),
    RU(ResourceBundle.getBundle("messages", new Locale("ru", "RU")));
    private ResourceBundle bundle;

    LocaleManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    public String getMessage(String key) {
        return bundle.getString(key);
    }

    public static LocaleManager defineLocale(String locale){
        LocaleManager localeManager = null;
        if (locale == null){
            localeManager = localeManager.RU;
        }
        localeManager = ("en_US".equals(locale)) ? localeManager.EN : localeManager.RU;
        return localeManager;
    }
}
