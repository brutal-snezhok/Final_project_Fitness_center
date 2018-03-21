package com.tsyrulik.dmitry.model.entity;

import java.util.Objects;

public class Appointment {
    private int appIdAppointment;
    private int appIdExercises;
    private int appIdFood;
    private int appIdClient;

    public Appointment(int appIdClient) {
        this.appIdClient = appIdClient;
    }

    public Appointment(int appIdExercises, int appIdFood, int appIdClient) {
        this.appIdExercises = appIdExercises;
        this.appIdFood = appIdFood;
        this.appIdClient = appIdClient;
    }

    public Appointment(int appIdAppointment, int appIdExercises, int appIdFood, int appIdClient) {
        this.appIdAppointment = appIdAppointment;
        this.appIdExercises = appIdExercises;
        this.appIdFood = appIdFood;
        this.appIdClient = appIdClient;
    }

    public int getAppIdAppointment() {
        return appIdAppointment;
    }

    public void setAppIdAppointment(int appIdAppointment) {
        this.appIdAppointment = appIdAppointment;
    }

    public int getAppIdExercises() {
        return appIdExercises;
    }

    public void setAppIdExercises(int appIdExercises) {
        this.appIdExercises = appIdExercises;
    }

    public int getAppIdFood() {
        return appIdFood;
    }

    public void setAppIdFood(int appIdFood) {
        this.appIdFood = appIdFood;
    }

    public int getAppIdClient() {
        return appIdClient;
    }

    public void setAppIdClient(int appIdClient) {
        this.appIdClient = appIdClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appIdAppointment == that.appIdAppointment &&
                appIdExercises == that.appIdExercises &&
                appIdFood == that.appIdFood &&
                appIdClient == that.appIdClient;
    }

    @Override
    public int hashCode() {

        return Objects.hash(appIdAppointment, appIdExercises, appIdFood, appIdClient);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appIdAppointment=" + appIdAppointment +
                ", appIdExercises=" + appIdExercises +
                ", appIdFood=" + appIdFood +
                ", appIdClient=" + appIdClient +
                '}';
    }
}