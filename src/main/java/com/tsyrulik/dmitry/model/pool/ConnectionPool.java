package com.tsyrulik.dmitry.model.pool;

import com.tsyrulik.dmitry.model.exception.LoaderFitnessException;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.loader.DbConfig;
import com.tsyrulik.dmitry.model.loader.DbConfigReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger("ConnectionPool");
    private static ConnectionPool INSTANCE ;
    private static final BlockingDeque<ProxyConnection> POOL = new LinkedBlockingDeque<>();
    private static final ConnectionCreator CREATOR = new ConnectionCreator();
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        lock.lock();
        try {
            if (INSTANCE == null) {
                INSTANCE = new ConnectionPool();
            }
        } finally {
            lock.unlock();
        }
        return INSTANCE;
    }


    public static void initPool() throws PoolFitnessException {
        if (!isInitialized.get()) {
            try {
                DbConfigReader reader = new DbConfigReader();
                DbConfig dbConfig = reader.readProperties();
                int poolSize = dbConfig.getPoolSize();

                for (int i = 0; i < poolSize; i++) {
                    ProxyConnection connection = (ProxyConnection) CREATOR.getConnection();
                    POOL.push(connection);
                }

                isInitialized.set(true);
            } catch (LoaderFitnessException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
                throw new PoolFitnessException(e.getMessage(), e.getCause());
            }
        }
    }


    public static void destroyPool() throws PoolFitnessException {
        if (isInitialized.get()) {
            try {
                for (int i = 0; i < POOL.size(); i++) {
                    Connection connection = POOL.poll();
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
                throw new PoolFitnessException(e.getMessage(), e.getCause());
            }
        }
    }


    public Connection getConnection() throws PoolFitnessException {
        try {
            Connection connection = POOL.take();
            if (!connection.isValid(1)) {
                connection = CREATOR.getConnection();
            }
            return connection;
        } catch (InterruptedException | SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new PoolFitnessException(e.getMessage(), e.getCause());
        }
    }
    public void releaseConnection(ProxyConnection connection) throws PoolFitnessException {
        try {
            POOL.put(connection);
        } catch (InterruptedException exc) {
            throw new PoolFitnessException(exc);
        }
    }

    public void recycleConnection(Connection connection) throws PoolFitnessException {
        try {
            if (connection == null || !connection.isValid(1)) {
                connection = CREATOR.getConnection();
            }
            POOL.put((ProxyConnection) connection);
        } catch (InterruptedException | SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new PoolFitnessException(e.getMessage(), e.getCause());
        }
    }

}