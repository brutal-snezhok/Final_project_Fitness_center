package com.tsyrulik.dmitry.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Food {
    private Long idFood;
    private String nameOfDish;
    private LocalDate dateReceipt;
    private LocalTime timeOfReceipt;

    public Food(){
        idFood = (long) 1;
    }

    public Food(String nameOfDish, LocalDate dateReceipt, LocalTime timeOfReceipt) {
        this.nameOfDish = nameOfDish;
        this.dateReceipt = dateReceipt;
        this.timeOfReceipt = timeOfReceipt;
    }
    public Food(Long idFood, String nameOfDish, LocalDate dateReceipt, LocalTime timeOfReceipt) {
        this.idFood = idFood;
        this.nameOfDish = nameOfDish;
        this.dateReceipt = dateReceipt;
        this.timeOfReceipt = timeOfReceipt;
    }

    public Long getIdFood() {
        return idFood;
    }

    public void setIdFood(Long idFood) {
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

        if (idFood != null ? !idFood.equals(food.idFood) : food.idFood != null) return false;
        if (nameOfDish != null ? !nameOfDish.equals(food.nameOfDish) : food.nameOfDish != null) return false;
        if (dateReceipt != null ? !dateReceipt.equals(food.dateReceipt) : food.dateReceipt != null) return false;
        return timeOfReceipt != null ? timeOfReceipt.equals(food.timeOfReceipt) : food.timeOfReceipt == null;
    }

    @Override
    public int hashCode() {
        int result = idFood != null ? idFood.hashCode() : 0;
        result = 31 * result + (nameOfDish != null ? nameOfDish.hashCode() : 0);
        result = 31 * result + (dateReceipt != null ? dateReceipt.hashCode() : 0);
        result = 31 * result + (timeOfReceipt != null ? timeOfReceipt.hashCode() : 0);
        return result;
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