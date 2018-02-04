package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void create(User user) throws  DAOFitnessException;

    List<User> findAll() throws DAOFitnessException;

    Optional<User> findById(long id) throws  DAOFitnessException;

    Optional<User> findByEmail(String login) throws  DAOFitnessException;

    public User findUserByEmailAndPassword(String username, String password) throws DAOFitnessException;

    User updateByUser(User user) throws  DAOFitnessException;

    boolean updateByAdmin(User user) throws  DAOFitnessException;

    void delete(long id) throws DAOFitnessException;


}
