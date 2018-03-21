package com.tsyrulik.dmitry.model.entity;

public class Exercises {
    private Long idExercises;
    private String muscleGroup;
    private String nameOfExercises;
    private String equipment;

    public Exercises() {
        idExercises = (long)1;
    }

    public Exercises(String muscleGroup, String nameOfExercises, String equipment) {
        this.muscleGroup = muscleGroup;
        this.nameOfExercises = nameOfExercises;
        this.equipment = equipment;
    }

    public Exercises(Long idExercises, String muscleGroup, String nameOfExercises, String equipment) {
        this.idExercises = idExercises;
        this.muscleGroup = muscleGroup;
        this.nameOfExercises = nameOfExercises;
        this.equipment = equipment;
    }

    public Long getIdExercises() {
        return idExercises;
    }

    public void setIdExercises(Long idExercises) {
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

        if (idExercises != null ? !idExercises.equals(exercises.idExercises) : exercises.idExercises != null)
            return false;
        if (muscleGroup != null ? !muscleGroup.equals(exercises.muscleGroup) : exercises.muscleGroup != null)
            return false;
        if (nameOfExercises != null ? !nameOfExercises.equals(exercises.nameOfExercises) : exercises.nameOfExercises != null)
            return false;
        return equipment != null ? equipment.equals(exercises.equipment) : exercises.equipment == null;
    }

    @Override
    public int hashCode() {
        int result = idExercises != null ? idExercises.hashCode() : 0;
        result = 31 * result + (muscleGroup != null ? muscleGroup.hashCode() : 0);
        result = 31 * result + (nameOfExercises != null ? nameOfExercises.hashCode() : 0);
        result = 31 * result + (equipment != null ? equipment.hashCode() : 0);
        return result;
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