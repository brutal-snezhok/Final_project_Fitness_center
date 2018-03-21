package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.Order;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;
import java.util.Optional;

/**
 * The Interface OrderDAO.
 */
public interface OrderDAO {
    /**
     * Creates the order.
     *
     * @param order the order
     * @throws DAOFitnessException the dao fitness exception
     */
    void createOrder(Order order) throws DAOFitnessException;

    /**
     * Find all.
     *
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    List<Order> findAllOrders() throws DAOFitnessException;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<Order> findOrderById(long id) throws DAOFitnessException;

    /**
     * Find by email.
     *
     * @param email the email
     * @return the optional
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<Order> findOrderByEmailClient(String email) throws DAOFitnessException;

    /**
     * Update.
     *
     * @param order the order
     * @return the order
     * @throws DAOFitnessException the dao fitness exception
     */
    Order updateClientOrder(Order order) throws DAOFitnessException;

    /**
     * Delete by id.
     *
     * @param id the id
     * @throws DAOFitnessException the dao fitness exception
     */
    void deleteOrder(long id) throws DAOFitnessException;
}
