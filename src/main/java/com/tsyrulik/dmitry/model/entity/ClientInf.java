package com.tsyrulik.dmitry.model.entity;

import java.util.List;

public class ClientInf {
    private Client client;
    private List<Food> food;
    private List<Exercises> exercises;
    private Order order;

    public ClientInf(Client client, List<Food> food, List<Exercises> exercises) {
        this.client = client;
        this.food = food;
        this.exercises = exercises;
        this.order = new Order();
    }


    public ClientInf(Client client, List<Food> food, List<Exercises> exercises, Order order) {
        this.client = client;
        this.food = food;
        this.exercises = exercises;
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public List<Exercises> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercises> exercises) {
        this.exercises = exercises;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientInf clientInf = (ClientInf) o;

        if (client != null ? !client.equals(clientInf.client) : clientInf.client != null) return false;
        if (food != null ? !food.equals(clientInf.food) : clientInf.food != null) return false;
        if (exercises != null ? !exercises.equals(clientInf.exercises) : clientInf.exercises != null) return false;
        return order != null ? order.equals(clientInf.order) : clientInf.order == null;
    }

    @Override
    public int hashCode() {
        int result = client != null ? client.hashCode() : 0;
        result = 31 * result + (food != null ? food.hashCode() : 0);
        result = 31 * result + (exercises != null ? exercises.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientInf{" +
                "client=" + client +
                ", food=" + food +
                ", exercises=" + exercises +
                ", order=" + order +
                '}';
    }
}