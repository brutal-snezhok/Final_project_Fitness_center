package com.tsyrulik.dmitry.model.entity;

import java.io.Serializable;

public class User implements Serializable,Cloneable{
    private long idUser;
    private String name;
    private String surname;
    private int yearOld;
    private String sex;
    private String email;
    private String password;
    private String role;

    public User(){

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

    public User(long idUser, String name, String surname, int yearOld, String sex, String email, String password, String role) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.yearOld = yearOld;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
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
        role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != user.idUser) return false;
        if (yearOld != user.yearOld) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUser ^ (idUser >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + yearOld;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
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