package com.tsyrulik.dmitry.model.entity;

import java.math.BigDecimal;

public class Trainer extends User{
    private int idTrainer;
    private String education;
    private BigDecimal costPerHour;
    private int trainerIdUser;


    public Trainer(long idUser, String name, String surname, int yearOld, String sex, String email, String password, String role, int idTrainer, String education, BigDecimal costPerHour, int trainerIdUser) {
        super(idUser, name, surname, yearOld, sex, email, password, role);
        this.idTrainer = idTrainer;
        this.education = education;
        this.costPerHour = costPerHour;
        this.trainerIdUser = trainerIdUser;
    }

    public int getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(int idTrainer) {
        this.idTrainer = idTrainer;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public BigDecimal getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(BigDecimal costPerHour) {
        this.costPerHour = costPerHour;
    }

    public int getTrainerIdUser() {
        return trainerIdUser;
    }

    public void setTrainerIdUser(int trainerIdUser) {
        this.trainerIdUser = trainerIdUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Trainer trainer = (Trainer) o;

        if (idTrainer != trainer.idTrainer) return false;
        if (trainerIdUser != trainer.trainerIdUser) return false;
        if (education != null ? !education.equals(trainer.education) : trainer.education != null) return false;
        return costPerHour != null ? costPerHour.equals(trainer.costPerHour) : trainer.costPerHour == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idTrainer;
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (costPerHour != null ? costPerHour.hashCode() : 0);
        result = 31 * result + trainerIdUser;
        return result;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "idTrainer=" + idTrainer +
                ", education='" + education + '\'' +
                ", costPerHour=" + costPerHour +
                ", trainerIdUser=" + trainerIdUser +
                '}';
    }
}