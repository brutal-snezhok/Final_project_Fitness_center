package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll() throws DAOFitnessException;

    boolean create(User user) throws  DAOFitnessException;

    Optional<User> findById(long id) throws  DAOFitnessException;

    Optional<User> findByEmail(String login) throws  DAOFitnessException;

    boolean updateByUser(User user) throws  DAOFitnessException;

    boolean updateByAdmin(User user) throws  DAOFitnessException;

    boolean delete(long id);


}
