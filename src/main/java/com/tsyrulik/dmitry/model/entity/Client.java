package com.tsyrulik.dmitry.model.entity;

public class Client extends User {
    private int discount;

    public Client(long id, String name, String surname, int yearOld, String sex, String email,
                  String password, String role, int discount) {
        super(id, name, surname, yearOld, sex, email, password, role);
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        return discount == client.discount;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + discount;
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "discount=" + discount +
                '}';
    }
}