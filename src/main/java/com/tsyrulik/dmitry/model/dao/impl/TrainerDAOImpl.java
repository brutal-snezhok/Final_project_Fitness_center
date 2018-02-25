package com.tsyrulik.dmitry.model.dao.impl;

import com.tsyrulik.dmitry.model.constant.DAOConstant;
import com.tsyrulik.dmitry.model.dao.TrainerDAO;
import com.tsyrulik.dmitry.model.entity.*;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.PoolFitnessException;
import com.tsyrulik.dmitry.model.pool.ConnectionPool;
import com.tsyrulik.dmitry.model.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrainerDAOImpl implements TrainerDAO {
    private static final String CREATE_TRAINER_SQL = "INSERT INTO `trainer` (`education_or_level`, `cost_per_lesson`, `user_iduser`)  VALUES (?, ?, ?);";
    private static final String FIND_ALL_TRAINERS_SQL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idtrainer`, `education_or_level`,`cost_per_lesson`,`user_iduser` FROM `user` INNER JOIN `trainer` ON trainer.user_iduser=user.iduser";
    private static final String FIND_TRAINER_BY_ID_SQL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idtrainer`, `education_or_level`,`cost_per_lesson`,`user_iduser` FROM `user` INNER JOIN `trainer` ON trainer.user_iduser=user.iduser WHERE `trainer`.`idtrainer` = ?;";
    private static final String FIND_TRAINER_BY_EMAIL = "SELECT `user`.`iduser`, `name`, `surname`, `years_old`, `sex`, `email`, `password`, `role_idrole`, `idtrainer`, `education_or_level`,`cost_per_lesson`,`user_iduser` FROM `user` INNER JOIN `trainer` ON trainer.user_iduser=user.iduser WHERE `user`.`email`=?;";
    private static final String UPDATE_TRAINER_SQL= "UPDATE `trainer` SET `trainer`.`idtrainer`=?, `education_or_level`=?, `cost_per_lesson`=?  WHERE `user_iduser`=?;";
    private static final String SELECT_USER_FROM_TRAINER_TABLE = "SELECT `user_iduser` FROM `trainer` WHERE idtrainer=?;";
    private static final String DELETE_TRAINER_BY_ID = "DELETE FROM `trainer` WHERE `idtrainer`=?;";
    private static final String CREATE_FOOD_FOR_CLIENT = "INSERT INTO `food` (`name_of_dish`, `data_receipt`, `time_of_receipt`) VALUES (?, ?, ?);";
    private static final String CREATE_EXERCISES_FOR_CLIENT = "INSERT INTO `exercises` (`muscle_group`,`names_of_exercises`,`equipment`) VALUES (?, ?, ?);";
    private static final String CREATE_APPOINTMET_FOR_CLIENT = "INSERT INTO `appointments` (`exercises_idexercises`,`food_idfood`, `client_idclient`) VALUES (?, ?, ?);";
    private static final String UPDATE_EXERCISES= "UPDATE `exercises` SET `exercises`.`idexercises`=?, `exercises`.`muscle_group`=?, `exercises`.`names_of_exercises`=?, `exercises`.`equipment`=?  WHERE `idexercises`=?;";
    private static final String UPDATE_FOOD= "UPDATE `food` SET `food`.`name_of_dish`=?, `food`.`data_receipt`=?, `food`.`time_of_receipt`=?  WHERE `idfood`=?;";
    private static final String UPDATE_APPOINTMENTS= "UPDATE `appointments` SET `appointments`.`idappointments`=?, `appointments`.`exercises_idexercises`=?, `appointments`.`food_idfood`=?, `appointments`.`client_idclient`=?  WHERE `idappointments`=?;";
    private static final String DELETE_FOOD_BY_ID = "DELETE FROM `food` WHERE `idfood`=?;";
    private static final String DELETE_EXERCISES_BY_ID = "DELETE FROM `exercises` WHERE `idexercises`=?;";
    private static final String DELETE_APPOINTMENTS_BY_ID = "DELETE FROM `appointments` WHERE `idappointments`=?;";
    private static final String SELECT_MAX_ID_EXERCISE = "SELECT max(`idexercises`) FROM `exercises`;";
    private static final String SELECT_MAX_ID_TRAINER = "SELECT max(`idtrainer`) FROM `trainer`;";

    @Override
    public void createTrainer(Trainer trainer) throws DAOFitnessException {
        User user = createUserFromTrainer(trainer);
        user = new UserDAOImpl().createWithMaxId(user);
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TRAINER_SQL)){
            preparedStatement.setString(1, trainer.getEducation());
            preparedStatement.setBigDecimal(2, trainer.getCostPerHour());
            preparedStatement.setInt(3, (int) user.getIdUser());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }
    private User createUserFromTrainer(Trainer trainer){
        return new User(trainer.getIdUser(), trainer.getName(), trainer.getSurname(), trainer.getYearOld(), trainer.getSex(),
                trainer.getEmail(),trainer.getPassword(), trainer.getRole());
    }
    private Trainer createTrainerFromResult(ResultSet resultSet) throws SQLException, DAOFitnessException {
        long id = resultSet.getLong(DAOConstant.USER_ID_USER);
        Optional<User> user = new UserDAOImpl().findUserById(id);
        Trainer trainer = new Trainer(user.get(), resultSet.getInt(DAOConstant.ID_TRAINER),
                resultSet.getString(DAOConstant.EDUCATION_OR_LEVEL),
                resultSet.getBigDecimal(DAOConstant.COST_PER_LESSON),resultSet.getInt(DAOConstant.USER_ID_USER));
        return trainer;
    }

    @Override
    public List<Trainer> findAllTrainers() throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(FIND_ALL_TRAINERS_SQL);
            ResultSet resultSet = statement.getResultSet();
            List<Trainer> trainers = new ArrayList<>();
            while (resultSet.next()) {
                trainers.add(createTrainerFromResult(resultSet));
            }
            return trainers;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Optional<Trainer> findTrainerById(long id) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TRAINER_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Trainer> trainerOptional = Optional.empty();
            if (resultSet.next()) {
                trainerOptional = Optional.of(createTrainerFromResult(resultSet));
            }
            return trainerOptional;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Optional<Trainer> findTrainerByEmail(String email) throws DAOFitnessException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_TRAINER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            Optional<Trainer> trainerOptional = Optional.empty();
            if (resultSet.next()) {
                trainerOptional = Optional.of(createTrainerFromResult(resultSet));
            }
            return trainerOptional;
        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) throws DAOFitnessException {
        User user = createUserFromTrainer(trainer);
        new UserDAOImpl().updateUserByUser(user);
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE_TRAINER_SQL)){
            st.setInt(1, trainer.getIdTrainer());
            st.setString(2, trainer.getEducation());
            st.setBigDecimal(3, trainer.getCostPerHour());
            st.setInt(4, trainer.getTrainerIdUser());
            st.executeUpdate();
            return trainer;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }
    private User selectUserFromTrainerTable(long idTrainer) throws DAOFitnessException {
       return  new ClientDAOImpl().selectUserFromClientTable(idTrainer, SELECT_USER_FROM_TRAINER_TABLE);
    }
    @Override
    public void deleteTrainer(long id) throws DAOFitnessException {
        User user = selectUserFromTrainerTable(id);
        new UserDAOImpl().deleteUser(user.getIdUser());
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRAINER_BY_ID)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    public Food executeFood(Food food, String str) throws DAOFitnessException{
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(str)){
            preparedStatement.setString(1, food.getNameOfDish());
            preparedStatement.setDate(2, Date.valueOf(food.getDateReceipt()));
            preparedStatement.setTime(3, Time.valueOf(food.getTimeOfReceipt()));
            preparedStatement.executeUpdate();
            return food;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Food createFoodForClient(Food food) throws DAOFitnessException {
        return  executeFood(food, CREATE_FOOD_FOR_CLIENT);
    }

    @Override
    public Exercises createExercisesForClient(Exercises exercises) throws DAOFitnessException{
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EXERCISES_FOR_CLIENT);
            PreparedStatement prestatement = connection.prepareStatement(SELECT_MAX_ID_EXERCISE)){
            preparedStatement.setString(1, exercises.getMuscleGroup());
            preparedStatement.setString(2, exercises.getNameOfExercises());
            preparedStatement.setString(3, exercises.getEquipment());
            preparedStatement.executeUpdate();
            ResultSet resultSet = prestatement.executeQuery();
            if (resultSet.next()){
                exercises.setIdExercises(resultSet.getLong(1));
            }
            return exercises;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void createAppointmentForClient(Appointment appointment) throws DAOFitnessException {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_APPOINTMET_FOR_CLIENT)){
            preparedStatement.setLong(1, appointment.getAppIdExercises());
            preparedStatement.setLong(2, appointment.getAppIdFood());
            preparedStatement.setLong(3, appointment.getAppIdClient());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Exercises updateExercises(Exercises exercises) throws DAOFitnessException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE_EXERCISES)){
            st.setLong(1, exercises.getIdExercises());
            st.setString(2, exercises.getMuscleGroup());
            st.setString(3, exercises.getNameOfExercises());
            st.setString(4, exercises.getEquipment());
            st.executeUpdate();
            return exercises;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Food updateFood(Food food) throws DAOFitnessException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE_FOOD)){
            st.setString(1, food.getNameOfDish());
            st.setDate(2, Date.valueOf(food.getDateReceipt()));
            st.setTime(3, Time.valueOf(food.getTimeOfReceipt()));
            st.setLong(4, food.getIdFood());
            st.executeUpdate();
            return food;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public Appointment updateAppointmets(Appointment appointment) throws DAOFitnessException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement st = connection.prepareStatement(UPDATE_APPOINTMENTS)){
            st.setLong(1, appointment.getAppIdAppointment());
            st.setLong(2, appointment.getAppIdExercises());
            st.setLong(3, appointment.getAppIdFood());
            st.setLong(4, appointment.getAppIdClient());
            st.executeUpdate();
            return appointment;
        } catch (SQLException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void deleteFoodById(long idFood) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOOD_BY_ID)) {
            preparedStatement.setLong(1, idFood);

            preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void deleteExercisesById(long idExercises) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXERCISES_BY_ID)) {
            preparedStatement.setLong(1, idExercises);

            preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

    @Override
    public void deleteAppointmentsById(long idAppointments) throws DAOFitnessException {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_APPOINTMENTS_BY_ID)) {
            preparedStatement.setLong(1, idAppointments);

            preparedStatement.executeUpdate();

        } catch (SQLException | PoolFitnessException e) {
            throw new DAOFitnessException(e);
        }
    }

}