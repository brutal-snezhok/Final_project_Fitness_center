package com.tsyrulik.dmitry.model.entity;

import java.util.Objects;

public class Exercises {
    private int idExercises;
    private String muscleGroup;
    private String nameOfExercises;
    private String equipment;

    public Exercises() {
        idExercises = 1;
    }

    public Exercises(String muscleGroup, String nameOfExercises, String equipment) {
        this.muscleGroup = muscleGroup;
        this.nameOfExercises = nameOfExercises;
        this.equipment = equipment;
    }

    public Exercises(int idExercises, String muscleGroup, String nameOfExercises, String equipment) {
        this.idExercises = idExercises;
        this.muscleGroup = muscleGroup;
        this.nameOfExercises = nameOfExercises;
        this.equipment = equipment;
    }

    public int getIdExercises() {
        return idExercises;
    }

    public void setIdExercises(int idExercises) {
        this.idExercises = idExercises;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getNameOfExercises() {
        return nameOfExercises;
    }

    public void setNameOfExercises(String nameOfExercises) {
        this.nameOfExercises = nameOfExercises;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercises exercises = (Exercises) o;
        return idExercises == exercises.idExercises &&
                Objects.equals(muscleGroup, exercises.muscleGroup) &&
                Objects.equals(nameOfExercises, exercises.nameOfExercises) &&
                Objects.equals(equipment, exercises.equipment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idExercises, muscleGroup, nameOfExercises, equipment);
    }

    @Override
    public String toString() {
        return "Exercises{" +
                "idExercises=" + idExercises +
                ", muscleGroup='" + muscleGroup + '\'' +
                ", nameOfExercises='" + nameOfExercises + '\'' +
                ", equipment='" + equipment + '\'' +
                '}';
    }
}