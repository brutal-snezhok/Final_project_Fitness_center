package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The Interface UserDAO.
 */
public interface UserDAO {
    /**
     * Creates the user.
     *
     * @param user the user
     * @throws DAOFitnessException the dao fitness exception
     */
    void create(User user) throws  DAOFitnessException;

    /**
     * Find all.
     *
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    List<User> findAllUsers() throws DAOFitnessException;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<User> findUserById(long id) throws DAOFitnessException;

    /**
     * Find by email.
     *
     * @param email the email
     * @return the optional
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<User> findUserByEmail(String email) throws DAOFitnessException;

    /**
     * Find by email and password.
     *
     * @param username the email
     * @param password the password
     * @return the user
     * @throws DAOFitnessException the dao fitness exception
     */
    User findUserByEmailAndPassword(String username, String password) throws DAOFitnessException;

    /**
     * Update.
     *
     * @param user the user
     * @return the user
     * @throws DAOFitnessException the dao fitness exception
     */
    User updateUserByUser(User user) throws  DAOFitnessException;

    /**
     * Delete by id.
     *
     * @param id the id
     * @throws DAOFitnessException the dao fitness exception
     */
    void deleteUser(long id) throws DAOFitnessException;

    void deleteAll()  throws DAOFitnessException;

}
