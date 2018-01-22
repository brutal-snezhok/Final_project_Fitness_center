package com.tsyrulik.dmitry.model.logic;

public class UserReceiver {

    public boolean checkUser(String login, String password){
        return login.equalsIgnoreCase(password);
    }
    //обращение к dao
    //select user
    //find user by id
    //update user
}