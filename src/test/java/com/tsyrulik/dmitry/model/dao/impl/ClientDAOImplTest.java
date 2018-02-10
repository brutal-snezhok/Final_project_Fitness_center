package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAOImplTest {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "12345";
    private final static String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";

    private List<User> allClients;
    private ClientDAOImpl clientDAO = new ClientDAOImpl();

    @BeforeMethod
    public void setUp() throws Exception {
        allClients = new ArrayList<>();

        int poolSize = 5;
        ConnectionPool.getInstance(URL, USERNAME, PASSWORD, poolSize);

        ConnectionPool.getInstance(1);
        allClients.add(new Client(2, "Pety", "Saplov", 23, "M",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client", (long)1, (double)15, (long)2));
        allClients.add(new Client(4, "Danila", "Letov", 21, "M",
                "letov@gmail.com", "6982e45352af5526754d83df2d1635", "client",(long)2, (double)0.65, (long)4));
    }

    @Test
    public void findAllClients() throws DAOFitnessException {
        List<Client> actual = clientDAO.findAllClients();
        Assert.assertEquals(actual.get(0), allClients.get(0));
    }

    @Test
    public void findClientById() throws DAOFitnessException {
        int id = 1;
        Optional<Client> client = clientDAO.findClientById(id);
        Assert.assertEquals(allClients.get(id - 1), client.get());
    }

    @Test
    public void findClientByEmail() throws DAOFitnessException {
        String email = "goodmail@gmail.com";
        Optional<Client> client = clientDAO.findClientByEmail(email);
        Assert.assertEquals(allClients.get(0), client.get());
    }
    @Test
    public void testCreateAndDeleteClient() throws Exception {
        Client client = new Client(4, "Kirill", "Pavlov", 23, "M",
                "vavl@gmail.com", "58bad6b697dff48f4927941962f23e90", "3", (long)2, (double)20, (long)4);
        allClients.add(client);
        Client expectedClient = (Client) allClients.get(2);
        clientDAO.createClient(client);
        Assert.assertEquals(client, expectedClient);
        allClients.remove(2);
        clientDAO.deleteClient(2);
    }

    @Test
    public void updateClientTest() throws DAOFitnessException {
        Client expectedClient = new Client(2, "PetyPety", "SaplovSaplov", 32, "F",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "3",(long)1, (double)15, (long)2);
        Client actualClient = clientDAO.updateClient(expectedClient);
        Assert.assertEquals(actualClient, expectedClient);
        Client expectedClientTwo = new Client(2, "Pety", "Saplov", 23, "M",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "3",(long)1, (double)15, (long)2);
        Client actualClientTwo = clientDAO.updateClient(expectedClientTwo);
        Assert.assertEquals(actualClientTwo, expectedClientTwo);
    }

}