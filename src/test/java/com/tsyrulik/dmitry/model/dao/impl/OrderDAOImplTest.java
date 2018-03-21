package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.dao.OrderDAO;
import com.tsyrulik.dmitry.model.entity.Order;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImplTest {
    private final static String USERNAME = "root";
    private final static String PASSWORD = "12345";
    private final static String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";

    private List<Order> allOrders;
    private OrderDAO orderDAO = new OrderDAOImpl();

    @BeforeMethod
    public void setUp() throws Exception {
        allOrders = new ArrayList<>();

        int poolSize = 5;
        ConnectionPool.getInstance(URL, USERNAME, PASSWORD, poolSize);

        ConnectionPool.getInstance(1);
        allOrders.add(new Order((long)1, "щадящий", new BigDecimal(160), 5, (long)1, 1));

    }

    @Test
    public void testFindAllOrders() throws Exception {
        List<Order> actual = orderDAO.findAllOrders();
        Assert.assertEquals(actual, allOrders);
    }

    @Test
    public void testFindOrderByIdClient() throws Exception {
        int id = 1;
        Optional<Order> order = orderDAO.findOrderById(id);
        Assert.assertEquals(allOrders.get(id -1), order.get());
    }

    @Test
    public void testFindOrderByEmailClient() throws Exception {
        String email = "goodmail@gmail.com";
        Optional<Order> order = orderDAO.findOrderByEmailClient(email);
        Assert.assertEquals(allOrders.get(0), order.get());
    }

    @Test
    public void testUpdateClientOrder() throws Exception {
        Order expectedOrder = new Order((long)1, "нормальный", new BigDecimal(40000), 10, (long)1, 1);
        Order actualOrder = orderDAO.updateClientOrder(expectedOrder);
        Assert.assertEquals(actualOrder, expectedOrder);
        Order expectedOrderTwo = new Order((long)1, "щадящий", new BigDecimal(160), 5, (long)1, 1);
        Order actualOrderTwo = orderDAO.updateClientOrder(expectedOrderTwo);
        Assert.assertEquals(actualOrderTwo, expectedOrderTwo);
    }

    @Test
    public void testCreateDeleteOrder() throws Exception {
        Order order = new Order((long)2, "для спортсменов", new BigDecimal(40000), 10, (long)1, 1);
        allOrders.add(order);
        Order expectedOrder =  allOrders.get(1);
        orderDAO.createOrder(order);
        Assert.assertEquals(order, expectedOrder);
        allOrders.remove(1);
        orderDAO.deleteOrder(2);
    }

}