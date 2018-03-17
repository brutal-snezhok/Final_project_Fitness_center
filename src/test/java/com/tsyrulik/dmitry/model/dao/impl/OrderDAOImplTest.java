package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.dao.ClientDAO;
import com.tsyrulik.dmitry.model.dao.OrderDAO;
import com.tsyrulik.dmitry.model.dao.TrainerDAO;
import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.Order;
import com.tsyrulik.dmitry.model.entity.Trainer;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.manager.PropertyManager;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImplTest {
    private static PropertyManager manager = new PropertyManager("dbConfig");
    private final static String URL = manager.getProperty("db-test.url");
    private final static String USERNAME = manager.getProperty("db-test.user");
    private final static String PASSWORD = manager.getProperty("db-test.password");
    private final static int POOL_SIZE = Integer.parseInt(manager.getProperty("db-test.pool_size"));

    private List<Order> allOrders;
    private OrderDAO orderDAO = new OrderDAOImpl();
    private ClientDAO clientDAO = new ClientDAOImpl();
    private TrainerDAO trainerDAO = new TrainerDAOImpl();

    @BeforeClass
    public void setUp() throws Exception {
        allOrders = new ArrayList<>();
        ConnectionPool.getInstance(URL, USERNAME, PASSWORD, POOL_SIZE);
        ConnectionPool.getInstance(1);
    }

    @BeforeMethod
    public void testCreateOrderSetUp() throws DAOFitnessException {
        clientDAO.createClient(new Client(1, "Pety", "Saplov", 23, "M",
                "goodmail@gmail.com", "58bad6b697dff48f4927941962f23e90", "client", (long) 1,
                (double) 15, (long) 2));
        trainerDAO.createTrainer(new Trainer(3, "Kosty", "Pyshyk", 35, "M",
                "pyshhyk@gmail.com", "6982e82c0b21af5526754d83df2d1635", "trainer", 1,
                "кмс", new BigDecimal(10), 3));
    }

    @Test
    public void testCreateOrder() throws DAOFitnessException {
        Order order = new Order("для спортсменов", new BigDecimal(40000), 10, (long) 1, 1);
        allOrders.add(order);
        orderDAO.createOrder(order);
        Assert.assertEquals(orderDAO.findAllOrders().size(), allOrders.size());
    }

    @AfterMethod
    public void afterCreateOrder() throws DAOFitnessException {
        allOrders.clear();
        orderDAO.deleteAll();
        clientDAO.deleteAll();
        trainerDAO.deleteAll();
    }

//    @Test
//    public void testFindAllOrders() throws Exception {
//        List<Order> actual = orderDAO.findAllOrders();
//        Assert.assertEquals(actual, allOrders);
//    }
//
//    @Test
//    public void testFindOrderByIdClient() throws Exception {
//        int id = 1;
//        Optional<Order> order = orderDAO.findOrderById(id);
//        Assert.assertEquals(allOrders.get(id -1), order.get());
//    }
//
//    @Test
//    public void testFindOrderByEmailClient() throws Exception {
//        String email = "goodmail@gmail.com";
//        Optional<Order> order = orderDAO.findOrderByEmailClient(email);
//        Assert.assertEquals(allOrders.get(0), order.get());
//    }
//
//    @Test
//    public void testUpdateClientOrder() throws Exception {
//        Order expectedOrder = new Order((long)1, "нормальный", new BigDecimal(40000), 10, (long)1, 1);
//        Order actualOrder = orderDAO.updateClientOrder(expectedOrder);
//        Assert.assertEquals(actualOrder, expectedOrder);
//        Order expectedOrderTwo = new Order((long)1, "щадящий", new BigDecimal(160), 5, (long)1, 1);
//        Order actualOrderTwo = orderDAO.updateClientOrder(expectedOrderTwo);
//        Assert.assertEquals(actualOrderTwo, expectedOrderTwo);
//    }

//    @Test
//    public void testCreateDeleteOrder() throws Exception {
//        Order order = new Order((long) 2, "для спортсменов", new BigDecimal(40000), 10, (long) 1, 1);
//        allOrders.add(order);
//        Order expectedOrder = allOrders.get(1);
//        orderDAO.createOrder(order);
//        Assert.assertEquals(order, expectedOrder);
//        allOrders.remove(1);
//        orderDAO.deleteOrder(2);
//    }

}