package com.tsyrulik.dmitry.model.entity;

import java.math.BigDecimal;

public class Trainer extends User{
    private String education;
    private BigDecimal costPerHour;

    public Trainer(long id, String name, String surname, int yearOld, String sex, String email, String password, String role, String education, BigDecimal costPerHour) {
        super(id, name, surname, yearOld, sex, email, password, role);
        this.education = education;
        this.costPerHour = costPerHour;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Trainer trainer = (Trainer) o;

        if (education != null ? !education.equals(trainer.education) : trainer.education != null) return false;
        return costPerHour != null ? costPerHour.equals(trainer.costPerHour) : trainer.costPerHour == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (costPerHour != null ? costPerHour.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "education='" + education + '\'' +
                ", costPerHour=" + costPerHour +
                '}';
    }
}