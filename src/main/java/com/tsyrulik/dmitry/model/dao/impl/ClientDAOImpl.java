package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.constant.DAOConstant;
import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.User;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import com.tsyrulik.dmitry.model.pool.ProxyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientDAOImpl extends UserDAOImpl  {
    private static final String FIND_USERS_BY_WITH_DISCOUNT_SQL = "SELECT `user`.`iduser`, `user`.`name`, `user`.`surname`, `user`.`years_old`, `user`.`sex`, `user`.`email`, `user`.`password`, `role_name` AS `role` FROM `user` " +
            "LEFT JOIN `role` ON `role`.`idrole` = `user`.`role_idrole` WHERE `user`.`email` = ?;";
    private static final String CREATE_CLIENT_SQL = "INSERT INTO `client` (`idclient`,`discount`, `user_iduser`)  VALUES (?, ?, ?);";
    private static final String FIND_CLIENT_BY_ID_SQL = "SELECT `client`.`idclient`, `client`.`discount`, `client`.`user_iduser` FROM client WHERE `client`.`idclient` = ?;";

    public void createClient(Client client) throws DAOFitnessException {
        User user = createUserFromParameter(client);
        create(user);
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT_SQL)){
            preparedStatement.setLong(1, client.getIdUser());
            preparedStatement.setDouble(2, client.getDiscount());
            preparedStatement.setLong(3, client.getClientIdUser());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }
    private User createUserFromParameter(Client client){
        return new User(client.getIdUser(), client.getName(), client.getSurname(), client.getYearOld(), client.getSex(),
                client.getEmail(),client.getPassword(), client.getRole());
    }


    public Optional<Client> findClientById(long id) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Client> clientOptional = Optional.empty();
            if (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getLong(DAOConstant.ID_CLIENT));
                client.setDiscount(resultSet.getDouble(DAOConstant.DISCOUNT));
                client.setClientIdUser(resultSet.getLong(DAOConstant.USER_ID_USER));
                clientOptional = Optional.of(client);
            }
            return clientOptional ;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }


    public List<User> findUsersWithDiscount() {

        return null;
    }
}