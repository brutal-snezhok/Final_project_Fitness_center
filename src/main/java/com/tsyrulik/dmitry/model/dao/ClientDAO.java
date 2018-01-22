package com.tsyrulik.dmitry.model.dao;

import com.tsyrulik.dmitry.model.entity.Client;
import com.tsyrulik.dmitry.model.exception.DAOFitnessException;

import java.util.List;

public class ClientDAO extends AbstractDAO<Client> {



    @Override
    public List<Client> findAll() throws DAOFitnessException {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Client entity) {
        return false;
    }

    @Override
    public boolean create(Client entity) throws DAOFitnessException {
        return false;
    }

    @Override
    public Client update(Client entity) {
        return null;
    }
}