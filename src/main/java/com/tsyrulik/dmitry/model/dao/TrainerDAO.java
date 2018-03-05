package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.Appointment;
import com.tsyrulik.dmitry.model.entity.Exercises;
import com.tsyrulik.dmitry.model.entity.Food;
import com.tsyrulik.dmitry.model.entity.Trainer;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;
import java.util.Optional;

/**
 * The Interface TrainerDAO.
 */
public interface TrainerDAO {
    /**
     * Creates the trainer.
     *
     * @param trainer the trainer
     * @throws DAOFitnessException the dao fitness exception
     */
    void createTrainer(Trainer trainer) throws DAOFitnessException;

    /**
     * Find all.
     *
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    List<Trainer> findAllTrainers() throws DAOFitnessException;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<Trainer> findTrainerById(long id) throws DAOFitnessException;

    /**
     * Find by email.
     *
     * @param email the email
     * @return the optional
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<Trainer> findTrainerByEmail(String email) throws DAOFitnessException;

    /**
     * Update.
     *
     * @param trainer the trainer
     * @return the trainer
     * @throws DAOFitnessException the dao fitness exception
     */
    Trainer updateTrainer(Trainer trainer) throws DAOFitnessException;

    /**
     * Delete by id.
     *
     * @param id the id
     * @throws DAOFitnessException the dao fitness exception
     */
    void deleteTrainer(long id) throws DAOFitnessException;

    /**
     * Creates the food.
     *
     * @param food the food
     * @throws DAOFitnessException the dao fitness exception
     */
    Food createFoodForClient(Food food) throws DAOFitnessException;

    /**
     * Creates the exercises.
     *
     * @param exercises the exercises
     * @throws DAOFitnessException the dao fitness exception
     */
    Exercises createExercisesForClient(Exercises exercises) throws DAOFitnessException;

    /**
     * Creates the appointment.
     *
     * @param appointment the appointment
     * @throws DAOFitnessException the dao fitness exception
     */
    void createAppointmentForClient(Appointment appointment) throws DAOFitnessException;

    /**
     * Update exercises.
     *
     * @param exercises the exercises
     * @return the exercises
     * @throws DAOFitnessException the dao fitness exception
     */
    Exercises updateExercises(Exercises exercises) throws DAOFitnessException;

    /**
     * Update food.
     *
     * @param food the food
     * @return the food
     * @throws DAOFitnessException the dao fitness exception
     */
    Food updateFood(Food food) throws DAOFitnessException;

    /**
     * Update appointment.
     *
     * @param appointment the appointment
     * @return the exercises
     * @throws DAOFitnessException the dao fitness exception
     */
    Appointment updateAppointmets(Appointment appointment) throws DAOFitnessException;

    /**
     * Delete food by id.
     *
     * @param idFood the id
     * @throws DAOFitnessException the dao fitness exception
     */
    void deleteFoodById(long idFood) throws DAOFitnessException;

    /**
     * Delete exercises by id.
     *
     * @param idExercises the id
     * @throws DAOFitnessException the dao fitness exception
     */
    void deleteExercisesById(long idExercises) throws DAOFitnessException;

    /**
     * Delete appointment by id.
     *
     * @param idAppointments the id
     * @throws DAOFitnessException the dao fitness exception
     */
    void deleteAppointmentsById(long idAppointments) throws DAOFitnessException;
}
