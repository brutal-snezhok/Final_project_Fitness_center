package com.tsyrulik.dmitry.model.entity;

import java.math.BigDecimal;

public class Order {
    private Long idOrder;
    private String typeOfTraining;
    private BigDecimal costOfLessons;
    private int number_of_lessons;
    private Long idClient;
    private int idTrainer;

    public Order() {
    }

    public Order(String typeOfTraining, BigDecimal costOfLessons,
                 int number_of_lessons, Long idClient, int idTrainer) {
        this.typeOfTraining = typeOfTraining;
        this.number_of_lessons = number_of_lessons;
        this.costOfLessons = costOfLessons;
        this.idClient = idClient;
        this.idTrainer = idTrainer;
    }
    public Order(Long idOrder, String typeOfTraining, BigDecimal costOfLessons,
                 int number_of_lessons, Long idClient, int idTrainer) {
        this.idOrder = idOrder;
        this.typeOfTraining = typeOfTraining;

        this.number_of_lessons = number_of_lessons;
        this.idClient = idClient;
        this.idTrainer = idTrainer;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
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

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
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

        Order that = (Order) o;

        if (number_of_lessons != that.number_of_lessons) return false;
        if (idTrainer != that.idTrainer) return false;
        if (idOrder != null ? !idOrder.equals(that.idOrder) : that.idOrder != null) return false;
        if (typeOfTraining != null ? !typeOfTraining.equals(that.typeOfTraining) : that.typeOfTraining != null)
            return false;
        return idClient != null ? idClient.equals(that.idClient) : that.idClient == null;
    }

    @Override
    public int hashCode() {
        int result = idOrder != null ? idOrder.hashCode() : 0;
        result = 31 * result + (typeOfTraining != null ? typeOfTraining.hashCode() : 0);
        result = 31 * result + number_of_lessons;
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + idTrainer;
        return result;
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