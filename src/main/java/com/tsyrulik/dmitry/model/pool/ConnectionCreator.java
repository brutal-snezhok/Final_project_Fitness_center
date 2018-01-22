package com.tsyrulik.dmitry.model.pool;

import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.loader.DbConfig;
import com.tsyrulik.dmitry.model.loader.DbConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static final Logger LOGGER = LogManager.getLogger();
    private String url;
    private String user;
    private String password;
    private String driver;

    public ConnectionCreator() {
        init();
    }


    public Connection getConnection() throws PoolFitnessException {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new PoolFitnessException(e.getMessage(), e.getCause());
        }
    }

    private void init() throws PoolFitnessException {
        DbConfigReader reader = new DbConfigReader();
        DbConfig dbConfig = reader.readProperties();

        url = dbConfig.getUrl();
        user = dbConfig.getUser();
        password = dbConfig.getPassword();
        driver = dbConfig.getDriver();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new PoolFitnessException(e.getMessage(), e.getCause());
        }
    }
}