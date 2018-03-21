package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.Appointment;
import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.entity.Exercises;
import com.tsyrulik.dmitry.model.entity.Food;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;
import java.util.Optional;

/**
 * The Interface ClientDAO.
 */
public interface ClientDAO {
    /**
     * Creates the client.
     *
     * @param client the client
     * @throws DAOFitnessException the dao fitness exception
     */
    void createClient(Client client) throws DAOFitnessException;

    /**
     * Find all.
     *
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    List<Client> findAllClients() throws DAOFitnessException;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<Client> findClientById(long id) throws DAOFitnessException;

    /**
     * Find by id.
     *
     * @param email the email
     * @return the optional
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<Client> findClientByEmail(String email) throws DAOFitnessException;

    /**
     * Update by client.
     *
     * @param client the client
     * @return the client
     * @throws DAOFitnessException the dao fitness exception
     */
    Client updateClient(Client client) throws DAOFitnessException;

    /**
     * Delete by id.
     *
     * @param id the id
     * @throws DAOFitnessException the dao fitness exception
     */
    void deleteClient(long id) throws DAOFitnessException;

    /**
     * Find food by id.
     *
     * @param id the id
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<Food> findFoodForClient(long id) throws DAOFitnessException;

    /**
     * Find exercises by id.
     *
     * @param id the id
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    Optional<Exercises> findExercisesForClient(long id) throws DAOFitnessException;

    /**
     * Find appointment by id.
     *
     * @param id the id
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    List<Appointment> findAllAppointmentForClient(long id) throws DAOFitnessException;

    /**
     * Find food by clientId.
     *
     * @param idClient the idClient
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    List<Food> findAllFoodForClientById(long idClient) throws DAOFitnessException;

    /**
     * Find exercises by idClient.
     *
     * @param idClient the idClient
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    List<Exercises> findAllExercisesForClientById(long idClient) throws DAOFitnessException;

    /**
     * Find exercises by nameOfExercises.
     *
     * @param nameOfExercise the nameOfExercise
     * @return the exercises
     * @throws DAOFitnessException the dao fitness exception
     */
    Exercises findExercisesForClientByIdEx(String nameOfExercise) throws DAOFitnessException;

    /**
     * Find exercises by idClient.
     *
     * @param idTrainer the id
     * @return the list
     * @throws DAOFitnessException the dao fitness exception
     */
    List<Client> findAllClientsOfThisTrainer(int idTrainer) throws DAOFitnessException;

    void deleteAll()  throws DAOFitnessException;
}
