package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void create(User user) throws  DAOFitnessException;

    List<User> findAllUsers() throws DAOFitnessException;

    Optional<User> findUserById(long id) throws DAOFitnessException;

    Optional<User> findUserByEmail(String email) throws DAOFitnessException;

    User findUserByEmailAndPassword(String username, String password) throws DAOFitnessException;

    User updateUserByUser(User user) throws  DAOFitnessException;

    boolean updateByAdmin(User user) throws  DAOFitnessException;

    void deleteUser(long id) throws DAOFitnessException;


}
