package com.tsyrulik.dmitry.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Food {
    private int idFood;
    private String nameOfDish;
    private LocalDate dateReceipt;
    private LocalTime timeOfReceipt;

    public Food() {
        idFood = 1;
    }

    public Food(String nameOfDish, LocalDate dateReceipt, LocalTime timeOfReceipt) {
        this.nameOfDish = nameOfDish;
        this.dateReceipt = dateReceipt;
        this.timeOfReceipt = timeOfReceipt;
    }

    public Food(int idFood, String nameOfDish, LocalDate dateReceipt, LocalTime timeOfReceipt) {
        this.idFood = idFood;
        this.nameOfDish = nameOfDish;
        this.dateReceipt = dateReceipt;
        this.timeOfReceipt = timeOfReceipt;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public void setNameOfDish(String nameOfDish) {
        this.nameOfDish = nameOfDish;
    }

    public LocalDate getDateReceipt() {
        return dateReceipt;
    }

    public void setDateReceipt(LocalDate dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public LocalTime getTimeOfReceipt() {
        return timeOfReceipt;
    }

    public void setTimeOfReceipt(LocalTime timeOfReceipt) {
        this.timeOfReceipt = timeOfReceipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return idFood == food.idFood &&
                Objects.equals(nameOfDish, food.nameOfDish) &&
                Objects.equals(dateReceipt, food.dateReceipt) &&
                Objects.equals(timeOfReceipt, food.timeOfReceipt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idFood, nameOfDish, dateReceipt, timeOfReceipt);
    }

    @Override
    public String toString() {
        return "Food{" +
                "idFood=" + idFood +
                ", NameOfDish='" + nameOfDish + '\'' +
                ", dateReceipt=" + dateReceipt +
                ", timeOfReceipt=" + timeOfReceipt +
                '}';
    }
}