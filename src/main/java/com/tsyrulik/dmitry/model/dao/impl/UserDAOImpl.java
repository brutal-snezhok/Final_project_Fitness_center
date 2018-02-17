package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.constant.DAOConstant;
import com.tsyrulik.dmitry.model.dao.UserDAO;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import com.tsyrulik.dmitry.model.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private static final String FIND_ALL_USERS_SQL = "SELECT `iduser`,`name`,`surname`,`years_old`,`sex`,`email`,`password`,`role_name` AS `role` FROM `user` LEFT JOIN `role` ON `user`.`role_idrole` = `role`.`idrole` ORDER BY `user`.`iduser`;";

    private static final String CREATE_USER_SQL = "INSERT INTO `user` (`name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String CREATE_USER_SQL_WITH_ID = "INSERT INTO `user` (`iduser`,`name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_USER_BY_ID_SQL = "SELECT `user`.`iduser`, `user`.`name`, `user`.`surname`, `user`.`years_old`, `user`.`sex`, `user`.`email`, `user`.`password`, `role_name` AS `role` FROM `user`\n" +
            "  LEFT JOIN `role` ON `role`.`idrole` = `user`.`role_idrole` WHERE `user`.`iduser` = ?;";

    private static final String FIND_USER_BY_EMAIL_SQL = "SELECT `user`.`iduser`, `user`.`name`, `user`.`surname`, `user`.`years_old`, `user`.`sex`, `user`.`email`, `user`.`password`, `role_name` AS `role` FROM `user` " +
            "LEFT JOIN `role` ON `role`.`idrole` = `user`.`role_idrole` WHERE `user`.`email` = ?;";

    private static final String UPDATE_BY_USER= "UPDATE `user` SET `iduser`=?, `name`=?, `surname`=?, `years_old`=?,`sex`=?, `email`=?, `password`=?," +
            "`role_idrole`=? WHERE `iduser`=?;";

    private static final String FIND_ROLE_SQL = "SELECT `user`.`role_idrole` FROM `user` WHERE `user`.`id` = ?;";

    private static final String UPDATE_BY_ADMIN_SQL = "UPDATE `user` SET `password`=?, `role`=? WHERE `id`=?;";

    private static final String SQL_SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT `iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`,`role_name` AS `role`" +
            "    FROM user LEFT JOIN role ON `user`.`role_idrole` = `role`.`idrole`" +
            "    WHERE email = ? AND password = ?";

    private static final String DELETE_USER_BY_ID = "DELETE FROM `user` WHERE `iduser`=?;";



    public void createWithOutId(User user) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getYearOld());
            preparedStatement.setString(4, user.getSex());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getRole());

            preparedStatement.executeUpdate();
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void create(User user) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_SQL_WITH_ID)) {

            preparedStatement.setLong(1, user.getIdUser());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setInt(4, user.getYearOld());
            preparedStatement.setString(5, user.getSex());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setString(8, user.getRole());

            preparedStatement.executeUpdate();
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(FIND_ALL_USERS_SQL);
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
    public Optional<User> findUserById(long id) throws DAOFitnessException {
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
        User user = new User(resultSet.getLong(DAOConstant.ID_USER), resultSet.getString(DAOConstant.NAME), resultSet.getString(DAOConstant.SURNAME),
                resultSet.getInt(DAOConstant.YEARS_OLD), resultSet.getString(DAOConstant.SEX), resultSet.getString(DAOConstant.EMAIL),
                resultSet.getString(DAOConstant.PASSWORD), resultSet.getString(DAOConstant.ROLE_ID_ROLE));
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_SQL)) {
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
    public User findUserByEmailAndPassword(String username, String password) throws DAOFitnessException{

        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL_AND_PASSWORD)){
            User user = null;
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                setUserFromResultSet(resultSet, user);
            }
            return user;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    void setUserFromResultSet(ResultSet resultSet, User user) throws SQLException{
        user.setIdUser(resultSet.getLong("iduser"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setYearOld(resultSet.getInt("years_old"));
        user.setSex(resultSet.getString("sex"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getString("role"));

    }

    @Override
    public User updateUserByUser(User user) throws DAOFitnessException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE_BY_USER)){
            st.setLong(1, user.getIdUser());
            st.setString(2, user.getName());
            st.setString(3,user.getSurname());
            st.setInt(4, user.getYearOld());
            st.setString(5, user.getSex());
            st.setString(6, user.getEmail());
            st.setString(7, user.getPassword());
            st.setString(8, user.getRole());
            st.setLong(9, user.getIdUser());
            st.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }

    }

    @Override
    public boolean updateByAdmin(User user) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ADMIN_SQL);
             PreparedStatement userRolePreparedStatement = connection.prepareStatement(FIND_ROLE_SQL)) {
            userRolePreparedStatement.setString(1, user.getRole());
            ResultSet userRoleResultSet = userRolePreparedStatement.executeQuery();

            if (userRoleResultSet.next()) {
                if (!userRoleResultSet.getString(DAOConstant.ROLE_ID_ROLE).equals(user.getRole())) {
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
    public void deleteUser(long id) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }



}