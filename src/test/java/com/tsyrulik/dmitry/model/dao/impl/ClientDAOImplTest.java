package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.Exercises;
import com.tsyrulik.dmitry.model.entity.Food;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.manager.PropertyManager;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAOImplTest {
    private static PropertyManager manager = new PropertyManager("dbConfig");
    private final static String URL = manager.getProperty("db-test.url");
    private final static String USERNAME = manager.getProperty("db-test.user");
    private final static String PASSWORD = manager.getProperty("db-test.password");

    private List<User> allClients;
    private ClientDAOImpl clientDAO = new ClientDAOImpl();
    private Food examplefood;
    private Exercises exampleExercises;

    @BeforeMethod
    public void setUp() throws Exception {
        allClients = new ArrayList<>();

        int poolSize = 5;
        ConnectionPool.getInstance(URL, USERNAME, PASSWORD, poolSize);

        ConnectionPool.getInstance(1);
        examplefood = new Food((long) 7, "Шоколад 100 гр", LocalDate.of(2017, 9, 20), LocalTime.of(15, 0, 0));
        exampleExercises = new Exercises((long) 6, "широчайшие мышцы спины",
                "тяга верхнего блока перед собой; тяга гантели одной рукой; тяга гантели к поясу; отжимания стоя на руках; подтягивания широким хватом",
                "гантели, турник, тренажер");

        allClients.add(new Client(2, "Pety", "Saplov", 23, "M",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client", (long) 1, (double) 15, (long) 2));
        allClients.add(new Client(4, "Danila", "Letov", 21, "M",
                "letov@gmail.com", "6982e45352af5526754d83df2d1635", "client", (long) 2, (double) 0.65, (long) 4));
    }

//    @Test
//    public void findAllClientsTest() throws DAOFitnessException {
//        List<Client> actual = clientDAO.findAllClients();
//        Assert.assertEquals(actual.get(0), allClients.get(0));
//    }

//    @Test
//    public void findClientByIdTest() throws DAOFitnessException {
//        int id = 1;
//        Optional<Client> client = clientDAO.findClientById(id);
//        Assert.assertEquals(allClients.get(id - 1), client.get());
//    }

//    @Test
//    public void findClientByEmailTest() throws DAOFitnessException {
//        String email = "goodmail@gmail.com";
//        Optional<Client> client = clientDAO.findClientByEmail(email);
//        Assert.assertEquals(allClients.get(0), client.get());
//    }

    @Test
    public void CreateAndDeleteClientTest() throws Exception {
        Client client = new Client(4, "Kirill", "Pavlov", 23, "M",
                "vavl@gmail.com", "58bad6b697dff48f4927941962f23e90", "3", (long) 2, (double) 20, (long) 4);
        allClients.add(client);
        Client expectedClient = (Client) allClients.get(2);
        clientDAO.createClient(client);
        Assert.assertEquals(client, expectedClient);
        allClients.remove(2);
        clientDAO.deleteClient(client.getIdClient());
    }

    @Test
    public void updateClientTest() throws DAOFitnessException {
        Client expectedClient = new Client(2, "PetyPety", "SaplovSaplov", 32, "F",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "3", (long) 1, (double) 15, (long) 2);
        Client actualClient = clientDAO.updateClient(expectedClient);
        Assert.assertEquals(actualClient, expectedClient);
        Client expectedClientTwo = new Client(2, "Pety", "Saplov", 23, "M",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "3", (long) 1, (double) 15, (long) 2);
        Client actualClientTwo = clientDAO.updateClient(expectedClientTwo);
        Assert.assertEquals(actualClientTwo, expectedClientTwo);
    }

//    @Test
//    public void findFoodForClientTest() throws DAOFitnessException {
//        int id = 1;
//        Optional<Food> food = clientDAO.findFoodForClient(id);
//        Assert.assertEquals(examplefood, food.get());
//    }
//
//    @Test
//    public void findExercisesForClientTest() throws DAOFitnessException {
//        int id = 1;
//        Optional<Exercises> exercises = clientDAO.findExercisesForClient(id);
//        Assert.assertEquals(exampleExercises, exercises.get());
//    }
}