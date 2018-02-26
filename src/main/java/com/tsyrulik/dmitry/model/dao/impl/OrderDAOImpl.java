package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.constant.DAOConstant;
import com.tsyrulik.dmitry.model.dao.OrderDAO;
import com.tsyrulik.dmitry.model.entity.Order;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import com.tsyrulik.dmitry.model.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {
    private static final String CREATE_ORDER_SQL = "INSERT INTO `order_client` (`type_of_training`, `number_of_lessons`, `client_idclient`, `trainer_idtrainer`)" +
            " VALUES (?, ?, ?, ?);";
    private static final String FIND_ALL_ORDERS_SQL = "SELECT `idorder`,`type_of_training`,`number_of_lessons`,`client_idclient`,`trainer_idtrainer` FROM `order_client` ORDER BY `idorder`;";
    private static final String FIND_ORDER_BY_ID_SQL = "SELECT `idorder`, `type_of_training`,`number_of_lessons`,`client_idclient`,`trainer_idtrainer` FROM `order_client` WHERE `idorder` = ?;";
    private static final String FIND_ORDER_BY_EMAIL_SQL = "SELECT `idorder`, `type_of_training`, `number_of_lessons`,`client_idclient`,`trainer_idtrainer` FROM `order_client` " +
            "INNER JOIN client ON `client`.`idclient`=`order_client`.`client_idclient` INNER JOIN `user` ON `client`.`user_iduser`=`user`.`iduser` WHERE `user`.`email` = ?;";
    private static final String UPDATE_ORDER= "UPDATE `order_client` SET `idorder`=?, `type_of_training`=?,`number_of_lessons`=?,`client_idclient`=?,`trainer_idtrainer`=? WHERE `idorder`=?;";
    private static final String DELETE_ORDER_BY_ID = "DELETE FROM `order_client` WHERE `idorder`=?;";

    @Override
    public void createOrder(Order order) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER_SQL)) {

            preparedStatement.setString(1, order.getTypeOfTraining());
            preparedStatement.setInt(2, order.getNumber_of_lessons());
            preparedStatement.setLong(3, order.getIdClient());
            preparedStatement.setInt(4, order.getIdTrainer());

            preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    private Order createOrderFromResult(ResultSet resultSet) throws SQLException {
        Order order = new Order(resultSet.getLong(DAOConstant.ID_ORDER), resultSet.getString(DAOConstant.TYPE_OF_TRAINING),
                resultSet.getBigDecimal(DAOConstant.COST_OF_LESSONS), resultSet.getInt(DAOConstant.NUMBER_OF_LESSONS),
                resultSet.getLong(DAOConstant.CLIENT_ID_CLIENT), resultSet.getInt(DAOConstant.TRAINER_ID_TRAINER));
        return order;
    }
    @Override
    public List<Order> findAllOrders() throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(FIND_ALL_ORDERS_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(createOrderFromResult(resultSet));
            }
            return orders;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }


    @Override
    public Optional<Order> findOrderById(long id) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ORDER_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Order> orderOptional = Optional.empty();
            if (resultSet.next()) {
                Order order = createOrderFromResult(resultSet);
                orderOptional = Optional.of(order);
            }
            return orderOptional;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Optional<Order> findOrderByEmailClient(String email) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ORDER_BY_EMAIL_SQL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            Optional<Order> orderOptional = Optional.empty();
            if (resultSet.next()) {
                Order order = createOrderFromResult(resultSet);
                orderOptional = Optional.of(order);
            }
            return orderOptional;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Order updateClientOrder(Order order) throws DAOFitnessException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)){
            preparedStatement.setLong(1, order.getIdOrder());
            preparedStatement.setString(2, order.getTypeOfTraining());
            preparedStatement.setInt(3, order.getNumber_of_lessons());
            preparedStatement.setLong(4, order.getIdClient());
            preparedStatement.setInt(5, order.getIdTrainer());
            preparedStatement.setLong(6, order.getIdOrder());

            preparedStatement.executeUpdate();
            return order;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void deleteOrder(long id) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }
}