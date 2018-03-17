package com.tsyrulik.dmitry.model.entity;

import java.util.Objects;

public class Client extends User {
    private int idClient;
    private Double discount;
    private int clientIdUser;

    public Client() {
    }

    public Client(User user, Double discount) {
        super(user.getIdUser(), user.getName(), user.getSurname(), user.getYearOld(), user.getSex(), user.getEmail(), user.getPassword(), user.getRole());
        this.discount = discount;
    }

    public Client(User user, int idClient, Double discount, int clientIdUser) {
        super(user.getIdUser(), user.getName(), user.getSurname(), user.getYearOld(), user.getSex(), user.getEmail(), user.getPassword(), user.getRole());
        this.idClient = idClient;
        this.discount = discount;
        this.clientIdUser = clientIdUser;
    }

    public Client(int idUser, String name, String surname, int yearOld, String sex, String email, String password, String role, int idClient, Double discount, int clientIdUser) {
        super(idUser, name, surname, yearOld, sex, email, password, role);
        this.idClient = idClient;
        this.discount = discount;
        this.clientIdUser = clientIdUser;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getClientIdUser() {
        return clientIdUser;
    }

    public void setClientIdUser(int clientIdUser) {
        this.clientIdUser = clientIdUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return idClient == client.idClient &&
                clientIdUser == client.clientIdUser &&
                Objects.equals(discount, client.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idClient, discount, clientIdUser);
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", discount=" + discount +
                ", clientIdUser=" + clientIdUser +
                '}';
    }
}