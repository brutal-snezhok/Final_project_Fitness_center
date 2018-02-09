package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.dao.UserDAO;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import com.tsyrulik.dmitry.model.util.MD5;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImplTest {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "12345";
    private final static String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";

    private List<User> allUsers;
    private UserDAO userDAO = new UserDAOImpl();

    @BeforeMethod
    public void setUp() throws Exception {

        allUsers = new ArrayList<>();

        int poolSize = 1;
        ConnectionPool.getInstance(URL, USERNAME, PASSWORD, poolSize);

        ConnectionPool.getInstance(1);
        allUsers.add(new User(1, "Vasy", "Philimonov", 21, "M",
                "phily@yandex.ru", "1f82cdf9195b31244721c6026587fb78", "admin"));
        allUsers.add(new User(2, "Pety", "Saplov", 23, "M",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client"));
        allUsers.add(new User(3, "Kosty", "Pyshyk", 35, "M",
                "pyshhyk@gmail.com", "6982e82c0b21af5526754d83df2d1635", "trainer" ));
    }

    @Test
    public void findAll() throws DAOFitnessException {
        List<User> actual = userDAO.findAll();
        Assert.assertEquals(actual, allUsers);
    }

    @Test
    public void findById() throws DAOFitnessException  {
        int id = 2;
        Optional<User> user = userDAO.findById(id);
        Assert.assertEquals(allUsers.get(id -1), user.get());
    }

    @Test
    public void findUserByEmailAndPassword() throws DAOFitnessException, NoSuchAlgorithmException {
        String email = "goodmail@gmail.com";
        String password = "password24";
        MD5 encryptor = new MD5();
        String encryptPassword = encryptor.encrypt(password);
        User expectedUser = allUsers.get(1);
        User actualUser = userDAO.findUserByEmailAndPassword(email, encryptPassword);
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void findUserByWrongUsernameOrPassword() throws DAOFitnessException, NoSuchAlgorithmException {
        String email = "WrongUsername";
        String password = "WrongPassword";
        MD5 encryptor = new MD5();
        String encryptPassword = encryptor.encrypt(password);
        User actualUser = userDAO.findUserByEmailAndPassword(email, encryptPassword);
        Assert.assertEquals(actualUser, null);
    }

    @Test
    public void findUserByEmail() throws DAOFitnessException {
        String email = "pyshhyk@gmail.com";
        User expectedUser = allUsers.get(2);
        Optional<User> actualUser = userDAO.findByEmail(email);
        Assert.assertEquals(actualUser.get(), expectedUser);
    }
    @Test
    public void createUser() throws DAOFitnessException {
        User user = new User(4, "Finel", "Finel", 21, "F",
                "finel@yandex.ru", "0ad137533510b9647ca82a7ea7d31a9e", "3");
        allUsers.add(user);
        User expectedUser = allUsers.get(3);
        userDAO.create(user);
        Assert.assertEquals(user, expectedUser);
        allUsers.remove(3);
        userDAO.delete(4);
    }

    @Test
    public void updateByUser() throws DAOFitnessException {
        User expectedUser = new User(2, "PetyPety", "Saplov", 32, "F",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "3");
        User actualUser = userDAO.updateByUser(expectedUser);
        Assert.assertEquals(actualUser, expectedUser);
        User expectedUserTwo = new User(2, "Pety", "Saplov", 23, "M",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "3");
        User actualUserTwo = userDAO.updateByUser(expectedUserTwo);
        Assert.assertEquals(actualUserTwo, expectedUserTwo);
    }
}