package com.tsyrulik.dmitry.model.logic;

import com.tsyrulik.dmitry.model.dao.OrderDAO;
import com.tsyrulik.dmitry.model.dao.impl.OrderDAOImpl;
import com.tsyrulik.dmitry.model.entity.Order;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;

import java.util.List;

public class OrderReceiver {

    public void createOrder(Order order) throws LogicFitnessException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
             orderDAO.createOrder(order);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public Order findOrdertByClientEmail(String email)  throws LogicFitnessException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
            return orderDAO.findOrderByEmailClient(email).get();
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public List<Order> findAllOrders(String email)  throws LogicFitnessException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
            return orderDAO.findAllOrders();
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public Order UpdateOrder(Order order)  throws LogicFitnessException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
            return orderDAO.updateClientOrder(order);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void DeleteOrder(Order order)  throws LogicFitnessException {
        OrderDAO orderDAO = new OrderDAOImpl();
        try {
             orderDAO.deleteOrder(order.getIdOrder());
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

}