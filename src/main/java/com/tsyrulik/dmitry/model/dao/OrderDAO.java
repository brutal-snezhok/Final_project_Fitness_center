package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.Order;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    void createOrder(Order order) throws DAOFitnessException;

    List<Order> findAllOrders() throws DAOFitnessException;

    Optional<Order> findOrderById(long id) throws DAOFitnessException;

    Optional<Order> findOrderByEmailClient(String email) throws DAOFitnessException;

    Order updateClientOrder(Order order) throws DAOFitnessException;

    void deleteOrder(long id) throws DAOFitnessException;
}
