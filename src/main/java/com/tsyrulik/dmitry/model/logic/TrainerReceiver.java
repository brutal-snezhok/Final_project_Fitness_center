package com.tsyrulik.dmitry.model.logic;

import com.tsyrulik.dmitry.model.dao.TrainerDAO;
import com.tsyrulik.dmitry.model.dao.impl.TrainerDAOImpl;
import com.tsyrulik.dmitry.model.entity.*;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;
import com.tsyrulik.dmitry.model.exception.LogicFitnessException;

import java.util.List;

public class TrainerReceiver {

    public void addTrainer(Trainer trainer) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
             trainerDAO.createTrainer(trainer);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public List<Trainer> findAllTrainers()  throws LogicFitnessException {
        TrainerDAO dao = new TrainerDAOImpl();
        try {
            return dao.findAllTrainers();
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public Trainer findTrainerById(int idTrainer)  throws LogicFitnessException {
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.findTrainerById(idTrainer).get();
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public Trainer findTrainerByEmail(String email)  throws LogicFitnessException {
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.findTrainerByEmail(email).get();
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public User updateTrainer(Trainer trainer) throws LogicFitnessException {
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.updateTrainer(trainer);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void deleteTrainer(long idTrainer) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.deleteTrainer(idTrainer);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public Food createFoodForClient(Food food) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.createFoodForClient(food);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public Exercises createExercisesForClient(Exercises exercises) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            return trainerDAO.createExercisesForClient(exercises);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void createAppointmentsForClient(Appointment appointment) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.createAppointmentForClient(appointment);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void updateExercises(Exercises exercises) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.updateExercises(exercises);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void updateFood(Food food) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.updateFood(food);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void updateAppointmets(Appointment appointment) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.updateAppointmets(appointment);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void deleteFoodById(long idFood) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.deleteFoodById(idFood);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void deleteExercisesById(long idExercises) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.deleteExercisesById(idExercises);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }

    public void deleteAppointmentsById(long idAppointments) throws LogicFitnessException{
        TrainerDAO trainerDAO = new TrainerDAOImpl();
        try {
            trainerDAO.deleteAppointmentsById(idAppointments);
        } catch (DAOFitnessException e) {
            throw new LogicFitnessException(e);
        }
    }



}