package com.tsyrulik.dmitry.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Order {
    private int idOrder;
    private String typeOfTraining;
    private BigDecimal costOfLessons;
    private int number_of_lessons;
    private int idClient;
    private int idTrainer;

    public Order() {
    }

    public Order(String typeOfTraining, BigDecimal costOfLessons,
                 int number_of_lessons, int idClient, int idTrainer) {
        this.typeOfTraining = typeOfTraining;
        this.number_of_lessons = number_of_lessons;
        this.costOfLessons = costOfLessons;
        this.idClient = idClient;
        this.idTrainer = idTrainer;
    }

    public Order(int idOrder, String typeOfTraining, BigDecimal costOfLessons,
                 int number_of_lessons, int idClient, int idTrainer) {
        this.idOrder = idOrder;
        this.typeOfTraining = typeOfTraining;

        this.number_of_lessons = number_of_lessons;
        this.idClient = idClient;
        this.idTrainer = idTrainer;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setTypeOfTraining(String typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }

    public int getNumber_of_lessons() {
        return number_of_lessons;
    }

    public void setNumber_of_lessons(int number_of_lessons) {
        this.number_of_lessons = number_of_lessons;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(int idTrainer) {
        this.idTrainer = idTrainer;
    }

    public BigDecimal getCostOfLessons() {
        return costOfLessons;
    }

    public void setCostOfLessons(BigDecimal costOfLessons) {
        this.costOfLessons = costOfLessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder &&
                number_of_lessons == order.number_of_lessons &&
                idClient == order.idClient &&
                idTrainer == order.idTrainer &&
                Objects.equals(typeOfTraining, order.typeOfTraining) &&
                Objects.equals(costOfLessons, order.costOfLessons);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOrder, typeOfTraining, costOfLessons, number_of_lessons, idClient, idTrainer);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", typeOfTraining='" + typeOfTraining + '\'' +
                ", number_of_lessons=" + number_of_lessons +
                ", idClient=" + idClient +
                ", idTrainer=" + idTrainer +
                '}';
    }
}