package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.constant.DAOConstant;
import com.tsyrulik.dmitry.model.dao.ClientDAO;
import com.tsyrulik.dmitry.model.entity.*;
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
    private static final String CREATE_CLIENT_SQL = "INSERT INTO `client` (`discount`, `client_user_iduser`)  VALUES (?, ?);";
    private static final String FIND_CLIENT_BY_ID_SQL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idclient`, `discount`,`client_user_iduser` " +
            "FROM `user` INNER JOIN `client` ON client.client_user_iduser=user.iduser WHERE `client`.`idclient` = ?;";
    private static final String FIND_ALL_CLIENTS_SQL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idclient`, `discount`,`client_user_iduser` " +
            "FROM `user` RIGHT JOIN `client` ON client.client_user_iduser=user.iduser";
    private static final String FIND_CLIENT_BY_EMAIL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idclient`, `discount`,`client_user_iduser` " +
            "FROM `user` INNER JOIN `client` ON client.client_user_iduser=user.iduser WHERE `user`.`email`=?;";
    private static final String UPDATE_CLIENT = "UPDATE `client` SET `client`.idclient=?, `client`.discount=?  WHERE `client_user_iduser`=?;";
    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM `client` WHERE `idclient`=?;";
    private static final String SELECT_USER_FROM_CLIENT_TABLE_SQL = "SELECT `user_iduser` FROM `client` WHERE `idclient`=?;";
    private static final String FIND_FOOD_FOR_CLIENT = "SELECT `idfood`, `name_of_dish`,`data_receipt`,`time_of_receipt`" +
            "FROM `user` LEFT JOIN client ON `client`.`client_user_iduser`=`user`.`iduser` " +
            "LEFT JOIN `appointments` ON `appointments`.`appointments_client_idclient`=`client`.`idclient` " +
            "LEFT JOIN `food` ON `food`.`idfood`=`appointments`.`food_idfood` WHERE `client`.`idclient`=?;";
    private static final String FIND_EXERCISES_FOR_CLEINT = "SELECT `idexercises`, `muscle_group`, `names_of_exercises`, `equipment`" +
            "FROM `user` LEFT JOIN client ON `client`.`client_user_iduser`=`user`.`iduser` " +
            "LEFT JOIN `appointments` ON `appointments`.`appointments_client_idclient`=`client`.`idclient` " +
            "LEFT JOIN `exercises` ON `exercises`.`idexercises`=`appointments`.`exercises_idexercises` WHERE `client`.`idclient`=?;";
    private static final String FIND_ALL_FOOD_FOR_CLIENT_BY_ID = "SELECT `idfood`, `name_of_dish`, `data_receipt`, `time_of_receipt` " +
            "FROM `user` LEFT JOIN client ON `client`.`client_user_iduser`=`user`.`iduser`" +
            "LEFT JOIN `appointments` ON `appointments`.`appointments_client_idclient`=`client`.`idclient`" +
            "LEFT JOIN `food` ON `food`.`idfood`=`appointments`.`food_idfood` WHERE `client`.`idclient`=?;";
    private static final String FIND_ALL_EXERCISES_FOR_CLIENT_BY_ID = "SELECT `idexercises`, `muscle_group`, `names_of_exercises`, `equipment` " +
            "FROM `user` LEFT JOIN client ON `client`.`client_user_iduser`=`user`.`iduser` " +
            "LEFT JOIN `appointments` ON `appointments`.`appointments_client_idclient`=`client`.`idclient` " +
            "LEFT JOIN `exercises` ON `exercises`.`idexercises`=`appointments`.`exercises_idexercises` WHERE `client`.`idclient`=?;";
    private static final String FIND_APPOINTMENT_FOR_CLIENT = "SELECT `idappointments`, `exercises_idexercises`, `food_idfood`, `appointments_client_idclient` " +
            "FROM `appointments` WHERE appointments_client_idclient=?;";
    private static final String FIND_APPOINTMENT_FOR_CLIENT_BY_ID_EXERCISES = "SELECT `idappointments`, `exercises_idexercises`, `food_idfood`, `appointments_client_idclient` " +
            "FROM `appointments` WHERE `exercises_idexercises`=?;";
    private static final String FIND_APPOINTMENT_FOR_CLIENT_BY_ID_FOOD = "SELECT `idappointments`, `exercises_idexercises`, `food_idfood`, `appointments_client_idclient` " +
            "FROM `appointments` WHERE `food_idfood`=?;";
    private static final String FIND_EXERCISE_BY_NAME_EXERCISE = "SELECT `idexercises`, `muscle_group`, `names_of_exercises`, `equipment` " +
            "FROM `exercises` WHERE `names_of_exercises`=?;";

    private static final String FIND_ALL_ClIENTS_THIS_TRAINER_SQL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idclient`, `discount`,`client_user_iduser` " +
            "FROM `user` RIGHT JOIN `client` ON client.client_user_iduser=user.iduser " +
            "LEFT JOIN order_client ON client.idclient=order_client.order_client_idclient WHERE `order_trainer_idtrainer`=?;";
    private static final String DELETE_ALL = "DELETE FROM client WHERE client.idclient > 0;";

    @Override
    public void createClient(Client client) throws DAOFitnessException {
        User user = createUserFromClient(client);
        user = new UserDAOImpl().createWithMaxId(user);
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT_SQL)) {
            preparedStatement.setDouble(1, client.getDiscount());
            preparedStatement.setLong(2, user.getIdUser());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    private User createUserFromClient(Client client) {
        return new User(client.getIdUser(), client.getName(), client.getSurname(), client.getYearOld(), client.getSex(),
                client.getEmail(), client.getPassword(), client.getRole());
    }

    @Override
    public List<Client> findAllClients() throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(FIND_ALL_CLIENTS_SQL);
            List<Client> clients;
            try (ResultSet resultSet = statement.getResultSet()) {
                clients = new ArrayList<>();
                while (resultSet.next()) {
                    clients.add(createClientFromResult(resultSet));
                }
            }
            return clients;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    private Client createClientFromResult(ResultSet resultSet) throws SQLException, DAOFitnessException {
        long id = resultSet.getLong(DAOConstant.USER_ID_USER);
        Optional<User> user = new UserDAOImpl().findUserById(id);
        return new Client(user.get(), resultSet.getInt(DAOConstant.ID_CLIENT),
                resultSet.getDouble(DAOConstant.DISCOUNT), resultSet.getInt(DAOConstant.USER_ID_USER));
    }

    @Override
    public Optional<Client> findClientById(long id) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_BY_ID_SQL)) {
            statement.setLong(1, id);
            Optional<Client> clientOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                clientOptional = Optional.empty();
                if (resultSet.next()) {
                    clientOptional = Optional.of(createClientFromResult(resultSet));
                }
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
            Optional<Client> clientOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                clientOptional = Optional.empty();
                if (resultSet.next()) {
                    clientOptional = Optional.of(createClientFromResult(resultSet));
                }
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
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(UPDATE_CLIENT)) {
            st.setLong(1, client.getIdClient());
            st.setDouble(2, client.getDiscount());
            st.setLong(3, client.getClientIdUser());
            st.executeUpdate();
            return client;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }

    }

    User selectUserFromClientTable(long idClient, String zapr) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(zapr)) {
            preparedStatement.setLong(1, idClient);
            User user;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                user = new User();
                if (resultSet.next()) {
                    user.setIdUser(resultSet.getInt(DAOConstant.USER_ID_USER));
                }
            }
            return user;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void deleteClient(long id) throws DAOFitnessException {
        User user = selectUserFromClientTable(id, SELECT_USER_FROM_CLIENT_TABLE_SQL);
        new UserDAOImpl().deleteUser(user.getIdUser());
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_BY_ID)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }


    @Override
    public Optional<Food> findFoodForClient(long id) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FOOD_FOR_CLIENT)) {
            statement.setLong(1, id);
            Optional<Food> foodOptional;
            try (ResultSet resultSet = statement.executeQuery()) {
                foodOptional = Optional.empty();
                if (resultSet.next()) {
                    foodOptional = Optional.of(createFoodFromResult(resultSet));
                }
            }
            return foodOptional;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public List<Appointment> findAllAppointmentForClient(long id) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_APPOINTMENT_FOR_CLIENT)) {
            statement.setLong(1, id);
            List<Appointment> appointments;
            try (ResultSet resultSet = statement.executeQuery()) {
                appointments = new ArrayList<>();
                while (resultSet.next()) {
                    appointments.add(createAppointmentFromResult(resultSet));
                }
            }
            return appointments;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    private Appointment createAppointmentFromResult(ResultSet resultSet) throws DAOFitnessException {
        try {
            return new Appointment(resultSet.getInt(DAOConstant.ID_APPOINTMENTS), resultSet.getInt(DAOConstant.EXERCISES_IDEXERCISES),
                    resultSet.getInt(DAOConstant.FOOD_IDFOOD), resultSet.getInt(DAOConstant.CLIENT_IDCLIENT));
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    public Food createFoodFromResult(ResultSet resultSet) throws SQLException, DAOFitnessException {
        return new Food(resultSet.getInt(DAOConstant.ID_FOOD),
                resultSet.getString(DAOConstant.NAME_OF_DISH),
                resultSet.getDate(DAOConstant.DATA_RECEIPT).toLocalDate(),
                resultSet.getTime(DAOConstant.TIME_OF_RECEIPT).toLocalTime());
    }

    @Override
    public Optional<Exercises> findExercisesForClient(long id) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_EXERCISES_FOR_CLEINT)) {
            preparedStatement.setLong(1, id);
            Optional<Exercises> exercisesOptional;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exercisesOptional = Optional.empty();
                if (resultSet.next()) {
                    exercisesOptional = Optional.of(createExercisesFromResult(resultSet));
                }
            }
            return exercisesOptional;

        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    public Exercises createExercisesFromResult(ResultSet resultSet) throws SQLException {
        return new Exercises(resultSet.getInt(DAOConstant.ID_EXERCISES),
                resultSet.getString(DAOConstant.MUSCLE_GROUP),
                resultSet.getString(DAOConstant.NAMES_OF_EXERCISES),
                resultSet.getString(DAOConstant.EQUIPMET));
    }

    @Override
    public List<Food> findAllFoodForClientById(long idClient) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_FOOD_FOR_CLIENT_BY_ID)) {
            statement.setLong(1, idClient);
            List<Food> foods;
            try (ResultSet resultSet = statement.executeQuery()) {
                foods = new ArrayList<>();
                while (resultSet.next()) {
                    foods.add(createFoodFromResult(resultSet));
                }
            }
            return foods;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public List<Exercises> findAllExercisesForClientById(long idClient) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_EXERCISES_FOR_CLIENT_BY_ID)) {
            statement.setLong(1, idClient);
            List<Exercises> exercises;
            try (ResultSet resultSet = statement.executeQuery()) {
                exercises = new ArrayList<>();
                while (resultSet.next()) {
                    exercises.add(createExercisesFromResult(resultSet));
                }
            }
            return exercises;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Exercises findExercisesForClientByIdEx(String nameOfExercise) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_EXERCISE_BY_NAME_EXERCISE)) {
            preparedStatement.setString(1, nameOfExercise);
            Exercises exercises;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                exercises = null;
                if (resultSet.next()) {
                    exercises = createExercisesFromResult(resultSet);
                }
            }
            return exercises;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }


    @Override
    public List<Client> findAllClientsOfThisTrainer(int idTrainer) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ClIENTS_THIS_TRAINER_SQL)) {
            statement.setLong(1, idTrainer);
            List<Client> clients;
            try (ResultSet resultSet = statement.executeQuery()) {
                clients = new ArrayList<>();
                while (resultSet.next()) {
                    clients.add(createClientFromResult(resultSet));
                }
            }
            return clients;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void deleteAll() throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }


}