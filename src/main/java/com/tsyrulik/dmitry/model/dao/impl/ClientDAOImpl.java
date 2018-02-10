package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.constant.DAOConstant;
import com.tsyrulik.dmitry.model.dao.ClientDAO;
import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import com.tsyrulik.dmitry.model.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAOImpl implements ClientDAO {
    private static final String FIND_USERS_BY_WITH_DISCOUNT_SQL = "SELECT `user`.`iduser`, `user`.`name`, `user`.`surname`, `user`.`years_old`, `user`.`sex`, `user`.`email`, `user`.`password`, `role_name` AS `role` FROM `user` " +
            "LEFT JOIN `role` ON `role`.`idrole` = `user`.`role_idrole` WHERE `user`.`email` = ?;";
    private static final String CREATE_CLIENT_SQL = "INSERT INTO `client` (`idclient`,`discount`, `user_iduser`)  VALUES (?, ?, ?);";
    private static final String FIND_CLIENT_BY_ID_SQL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idclient`, `discount`,`user_iduser` FROM `user` INNER JOIN `client` ON client.user_iduser=user.iduser WHERE `client`.`idclient` = ?;";
    private static final String FIND_ALL_CLIENTS_SQL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idclient`, `discount`,`user_iduser` FROM `user` INNER JOIN `client` ON client.user_iduser=user.iduser";
    private static final String FIND_CLIENT_BY_EMAIL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idclient`, `discount`,`user_iduser` FROM `user` INNER JOIN `client` ON client.user_iduser=user.iduser WHERE `user`.`email`=?;";
    private static final String UPDATE_CLIENT= "UPDATE `client` SET `client`.idclient=?, `client`.discount=?  WHERE `user_iduser`=?;";
    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM `test`.`client` WHERE `idclient`=?;";
    private static final String SELECT_USER_FROM_CLIENT_TABLE = "SELECT `user_iduser` FROM `client` WHERE idclient=?;";

    @Override
    public void createClient(Client client) throws DAOFitnessException {
        User user = createUserFromClient(client);
        new UserDAOImpl().create(user);
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT_SQL)){
            preparedStatement.setLong(1, client.getIdClient());
            preparedStatement.setDouble(2, client.getDiscount());
            preparedStatement.setLong(3, client.getClientIdUser());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }
    private User createUserFromClient(Client client){
        return new User(client.getIdUser(), client.getName(), client.getSurname(), client.getYearOld(), client.getSex(),
                client.getEmail(),client.getPassword(), client.getRole());
    }

    @Override
    public List<Client> findAllClients() throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(FIND_ALL_CLIENTS_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(createClientFromResult(resultSet));
            }
            return clients;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    private Client createClientFromResult(ResultSet resultSet) throws SQLException, DAOFitnessException {
        long id = resultSet.getLong(DAOConstant.USER_ID_USER);
        Optional<User> user = new UserDAOImpl().findUserById(id);
        Client client = new Client(user.get(), resultSet.getLong(DAOConstant.ID_CLIENT),
                resultSet.getDouble(DAOConstant.DISCOUNT),resultSet.getLong(DAOConstant.USER_ID_USER));
        return client;
    }

    @Override
    public Optional<Client> findClientById(long id) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Client> clientOptional = Optional.empty();
            if (resultSet.next()) {
                clientOptional = Optional.of(createClientFromResult(resultSet));
            }
            return clientOptional;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Optional<Client> findClientByEmail(String email) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            Optional<Client> clientOptional = Optional.empty();
            if (resultSet.next()) {
                clientOptional = Optional.of(createClientFromResult(resultSet));
            }
            return clientOptional;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Client updateClient(Client client) throws DAOFitnessException {
        User user = createUserFromClient(client);
        new UserDAOImpl().updateUserByUser(user);
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE_CLIENT)){
            st.setLong(1, client.getIdClient());
            st.setDouble(2, client.getDiscount());
            st.setLong(3, client.getClientIdUser());
            st.executeUpdate();
            return client;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }

    }

    private User selectUserFromClientTable(long idClient) throws DAOFitnessException {
            try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_FROM_CLIENT_TABLE)){
                preparedStatement.setLong(1, idClient);
                ResultSet resultSet = preparedStatement.executeQuery();
                User user = new User();
                if(resultSet.next()){
                    user.setIdUser(resultSet.getLong(DAOConstant.USER_ID_USER));
                }
                return user;
            }
         catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void deleteClient(long id) throws DAOFitnessException {
        User user = selectUserFromClientTable(id);
        new UserDAOImpl().deleteUser(user.getIdUser());
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {
             preparedStatement.setLong(1, id);

             preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    public List<User> findUsersWithDiscount() {

        return null;
    }
}