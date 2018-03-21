package com.tsyrulik.dmitry.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable, Cloneable {
    private int idUser;
    private String name;
    private String surname;
    private int yearOld;
    private String sex;
    private String email;
    private String password;
    private String role;

    public User() {

    }

    public User(String name, String surname, int yearOld, String sex, String email, String password, String role) {
        this.name = name;
        this.surname = surname;
        this.yearOld = yearOld;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int idUser, String name, String surname, int yearOld, String sex, String email, String password, String role) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.yearOld = yearOld;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYearOld() {
        return yearOld;
    }

    public void setYearOld(int yearOld) {
        this.yearOld = yearOld;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser &&
                yearOld == user.yearOld &&
                role == user.role &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, name, surname, yearOld, sex, email, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", yearOld=" + yearOld +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", Role='" + role + '\'' +
                '}';
    }
}