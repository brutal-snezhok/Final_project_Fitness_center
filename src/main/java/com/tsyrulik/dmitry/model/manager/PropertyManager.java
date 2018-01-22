package com.tsyrulik.dmitry.model.manager;

import java.util.ResourceBundle;

public class PropertyManager {
    private ResourceBundle resourceBundle;
    //извлекает инфу из файла
    public PropertyManager(String path, String filename){
        resourceBundle = ResourceBundle.getBundle(path + "," + filename);
    }
    public String getProperty(String key){
        return resourceBundle.getString(key);
    }
}