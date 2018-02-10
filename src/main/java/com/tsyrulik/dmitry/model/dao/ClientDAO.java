package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    void createClient(Client client) throws DAOFitnessException;

    List<Client> findAllClients() throws DAOFitnessException;

    Optional<Client> findClientById(long id) throws DAOFitnessException;

    Optional<Client> findClientByEmail(String email) throws DAOFitnessException;

    Client updateClient(Client client) throws DAOFitnessException;

    void deleteClient(long id) throws DAOFitnessException;
}
