package com.tsyrulik.dmitry.model.logic;

import com.tsyrulik.dmitry.model.dao.UserDAO;
import com.tsyrulik.dmitry.model.dao.impl.UserDAOImpl;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;
import com.tsyrulik.dmitry.model.util.MD5;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class UserReceiver.
 */
public class UserReceiver {
    private static final String ENGLISH_LOCALE = "en_US";
    private static final String RUSSIAN_LOCALE = "ru_RU";
    private static final String REG_EX_JSP = "/jsp.+";

    public User checkUser(String login, String password) throws LogicFitnessException {
        UserDAO dao = new UserDAOImpl();
        try {
            MD5 encryptor = new MD5();
            return dao.findUserByEmailAndPassword(login, encryptor.encrypt(password));
        }
        catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void addUser(User user) throws LogicFitnessException {
        UserDAO dao = new UserDAOImpl();
        try {
            MD5 encryptor = new MD5();
            user.setPassword(encryptor.encrypt(user.getPassword()));
            dao.create(user);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }


    public User addUser(String name, String surname, int years_old, String sex, String email,
                        String password, String role) throws LogicFitnessException {
        UserDAO daoUser = new UserDAOImpl();
        try {
            MD5 encryptor = new MD5();
            String encryptedPassword = encryptor.encrypt(password);
            User user = new User(0,  name,  surname, years_old, sex, email,
                     password, role);
            daoUser.create(user);
            return user;
        } catch (DAOFitnessException exc) {
            throw new LogicFitnessException(exc);
        }
    }



    public User findUserByEmail(String email) throws LogicFitnessException{
        UserDAO dao = new UserDAOImpl();
        try {
            return dao.findUserByEmail(email).get();
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public List<User> findAllUsers()  throws LogicFitnessException {
        UserDAO dao = new UserDAOImpl();
        try {
            return dao.findAllUsers();
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }


    public User findUserById(int id)  throws LogicFitnessException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            return userDAO.findUserById(id).get();
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public User updateUser(User user) throws LogicFitnessException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            return userDAO.updateUserByUser(user);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void deleteUser(long id) throws LogicFitnessException{
        UserDAO userDAO = new UserDAOImpl();
        try {
            userDAO.deleteUser(id);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public String changeLanguage(String locale){
        if (locale == null){
            return ENGLISH_LOCALE;
        }
        return ENGLISH_LOCALE.equals(locale) ? RUSSIAN_LOCALE : ENGLISH_LOCALE;
    }

    public String returnSamePage(String pagePath){
        String page = null;
        Pattern p = Pattern.compile(REG_EX_JSP);
        Matcher m = p.matcher(pagePath);
        if(m.find()){
            page = m.group();
        }
        return page;
    }

}