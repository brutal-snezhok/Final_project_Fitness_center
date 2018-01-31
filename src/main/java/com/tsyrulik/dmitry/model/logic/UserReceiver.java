package com.tsyrulik.dmitry.model.logic;

import com.tsyrulik.dmitry.model.dao.UserDAO;
import com.tsyrulik.dmitry.model.dao.impl.UserDAOImpl;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.util.MD5;

public class UserReceiver {

    public User checkUser(String login, String password) throws LogicFitnessException {
        UserDAO dao = new UserDAOImpl();
        try {
            MD5 encryptor = new MD5();
            return dao.findUserByEmailAndPassword(login, encryptor.encrypt(password));
        } catch (DAOFitnessException e) {
            e.printStackTrace();
        }
        // return login.equalsIgnoreCase(password);
        return null;
    }
    //обращение к dao
    //select user
    //find user by id
    //update user
    //регистрируемся и заносим данные в бд createUser
   // public boolean addUser

}