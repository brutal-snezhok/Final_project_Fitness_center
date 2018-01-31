package com.tsyrulik.dmitry.model.entity;

public class Appointment {
    private Long appIdAppointment;
    private Long appIdExercises;
    private Long appIdFood;
    private Long appIdClient;

    public Appointment(Long appIdAppointment, Long appIdExercises, Long appIdFood, Long appIdClient) {
        this.appIdAppointment = appIdAppointment;
        this.appIdExercises = appIdExercises;
        this.appIdFood = appIdFood;
        this.appIdClient = appIdClient;
    }

    public Long getAppIdAppointment() {
        return appIdAppointment;
    }

    public void setAppIdAppointment(Long appIdAppointment) {
        this.appIdAppointment = appIdAppointment;
    }

    public Long getAppIdExercises() {
        return appIdExercises;
    }

    public void setAppIdExercises(Long appIdExercises) {
        this.appIdExercises = appIdExercises;
    }

    public Long getAppIdFood() {
        return appIdFood;
    }

    public void setAppIdFood(Long appIdFood) {
        this.appIdFood = appIdFood;
    }

    public Long getAppIdClient() {
        return appIdClient;
    }

    public void setAppIdClient(Long appIdClient) {
        this.appIdClient = appIdClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        if (appIdAppointment != null ? !appIdAppointment.equals(that.appIdAppointment) : that.appIdAppointment != null)
            return false;
        if (appIdExercises != null ? !appIdExercises.equals(that.appIdExercises) : that.appIdExercises != null)
            return false;
        if (appIdFood != null ? !appIdFood.equals(that.appIdFood) : that.appIdFood != null) return false;
        return appIdClient != null ? appIdClient.equals(that.appIdClient) : that.appIdClient == null;
    }

    @Override
    public int hashCode() {
        int result = appIdAppointment != null ? appIdAppointment.hashCode() : 0;
        result = 31 * result + (appIdExercises != null ? appIdExercises.hashCode() : 0);
        result = 31 * result + (appIdFood != null ? appIdFood.hashCode() : 0);
        result = 31 * result + (appIdClient != null ? appIdClient.hashCode() : 0);
        return result;
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