package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<T extends User> {
    private static final Logger LOGGER = LogManager.getLogger("AbstractDAO");
    private Connection connection ;

    public abstract List<T> findAll() throws DAOFitnessException;

    public abstract boolean delete(int id);

    public abstract boolean delete(T entity);

    public abstract boolean create(T entity) throws  DAOFitnessException;

    public abstract T update(T entity);

    void close(Statement statement){
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }
    void close(Connection connection) throws  DAOFitnessException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new  DAOFitnessException(e.getMessage(), e.getCause());
        }
    }
    void setConnection(Connection connection) {
        this.connection = connection;
    }
}