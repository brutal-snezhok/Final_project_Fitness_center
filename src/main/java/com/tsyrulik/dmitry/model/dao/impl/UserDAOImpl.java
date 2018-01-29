package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.constant.DAOConstant;
import com.tsyrulik.dmitry.model.dao.UserDAO;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private static final String FIND_ALL_USERS_SQL = "SELECT `user`.`iduser`, `user`.`name`, `user`.`surname`, `user`.`years_old`, `user`.`sex`, `user`.`email`, `user`.`password`, `user`.`role_idrole`, " +
            " FROM `user` LEFT JOIN `role` ON `role`.`idrole` = `user`.`role_idrole` ORDER BY `user`.`iduser`;";

    private static final String CREATE_USER_SQL = "INSERT INTO `user` (`name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String FIND_USER_BY_ID_SQL = "SELECT `user`.`iduser`, `user`.`name`, `user`.`surname`, `user`.`years_old`, `user`.`sex`, `user`.`email`, `user`.`password`, `user`.`role_idrole`," +
            "LEFT JOIN `role` ON `role`.`idrole` = `user`.`role_idrole` WHERE `user`.`id` = ?;";

    private static final String FIND_USER_BY_LOGIN_SQL = "SELECT `user`.`iduser`, `user`.`name`, `user`.`surname`, `user`.`years_old`, `user`.`sex`, `user`.`email`, `user`.`password`, `user`.`role_idrole`," +
            "LEFT JOIN `role` ON `role`.`idrole` = `user`.`role_idrole` WHERE `user`.`email` = ?;";

    private static final String UPDATE_BY_USER_SQL = "UPDATE `user` SET `name`=?, `surname`=?, `years_old`=?,`sex`=?, `email`=?, `password`=?," +
            "`role_idrole`=? WHERE `id`=?;";

    private static final String FIND_ROLE_SQL = "SELECT `user`.`role_idrole` FROM `user` WHERE `user`.`id` = ?;";

    private static final String UPDATE_BY_ADMIN_SQL = "UPDATE `user` SET `password`=?, `role`=? WHERE `id`=?;";

    @Override
    public List<User> findAll() throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(FIND_ALL_USERS_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(createUserFromResult(resultSet));
            }
            return users;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public boolean create(User user) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getYearOld());
            preparedStatement.setString(4, user.getSex());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getRole());

            if (findByEmail(user.getEmail()) == null) {
                //TODO
                preparedStatement.execute();
                return true;
            }
            return false;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Optional<User> findById(long id) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                User user = createUserFromResult(resultSet);
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    private User createUserFromResult(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getLong(DAOConstant.ID), resultSet.getString(DAOConstant.NAME), resultSet.getString(DAOConstant.SURNAME),
                resultSet.getInt(DAOConstant.YEARS_OLD), resultSet.getString(DAOConstant.SEX), resultSet.getString(DAOConstant.EMAIL),
                resultSet.getString(DAOConstant.PASSWORD), resultSet.getString(DAOConstant.ROLE));
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN_SQL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            Optional<User> userOptional = Optional.empty();
            if (resultSet.next()) {
                User user = createUserFromResult(resultSet);
                userOptional = Optional.of(user);
            }
            return userOptional;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }


    @Override
    public boolean updateByUser(User user) throws DAOFitnessException {
        ///!!!!!!!!!!!!!!!!!!!!!111
        return false;
    }

    @Override
    public boolean updateByAdmin(User user) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ADMIN_SQL);
             PreparedStatement userRolePreparedStatement = connection.prepareStatement(FIND_ROLE_SQL)) {
            userRolePreparedStatement.setLong(1, user.getId());
            ResultSet userRoleResultSet = userRolePreparedStatement.executeQuery();

            if (userRoleResultSet.next()) {
                if (!userRoleResultSet.getString(DAOConstant.ROLE).equals(user.getRole())) {
                   //TODO
                }
                //TODO

                preparedStatement.executeUpdate();
                return true;
            }
            return false;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }



}