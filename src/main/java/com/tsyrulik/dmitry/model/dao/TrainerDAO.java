package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.Appointment;
import com.tsyrulik.dmitry.model.entity.Exercises;
import com.tsyrulik.dmitry.model.entity.Food;
import com.tsyrulik.dmitry.model.entity.Trainer;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;
import java.util.Optional;

public interface TrainerDAO {
    void createTrainer(Trainer trainer) throws DAOFitnessException;

    List<Trainer> findAllTrainers() throws DAOFitnessException;

    Optional<Trainer> findTrainerById(long id) throws DAOFitnessException;

    Optional<Trainer> findTrainerByEmail(String email) throws DAOFitnessException;

    Trainer updateTrainer(Trainer trainer) throws DAOFitnessException;

    void deleteTrainer(long id) throws DAOFitnessException;

    void createFoodForClient(Food food) throws DAOFitnessException;

    void createExercisesForClient(Exercises exercises) throws DAOFitnessException;

    void createAppointmentForClient(Appointment appointment) throws DAOFitnessException;

    Exercises updateExercises(Exercises exercises) throws DAOFitnessException;

    Food updateFood(Food food) throws DAOFitnessException;

    Appointment updateAppointmets(Appointment appointment) throws DAOFitnessException;

    void deleteFoodById(long idFood) throws DAOFitnessException;

    void deleteExercisesById(long idExercises) throws DAOFitnessException;

    void deleteAppointmentsById(long idAppointments) throws DAOFitnessException;
}
