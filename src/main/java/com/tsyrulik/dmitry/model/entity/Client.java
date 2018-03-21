package com.tsyrulik.dmitry.model.entity;

public class Client extends User {
    private Long idClient;
    private Double discount;
    private Long clientIdUser;

    public Client() {
    }

    public Client(User user, Double discount){
        super(user.getIdUser(),user.getName(),user.getSurname(),user.getYearOld(), user.getSex(),user.getEmail(),user.getPassword(),user.getRole());
        this.discount = discount;
    }
    public Client(User user, Long idClient, Double discount, Long clientIdUser){
        super(user.getIdUser(),user.getName(),user.getSurname(),user.getYearOld(), user.getSex(),user.getEmail(),user.getPassword(),user.getRole());
        this.idClient = idClient;
        this.discount = discount;
        this.clientIdUser = clientIdUser;
    }
    public Client(long idUser, String name, String surname, int yearOld, String sex, String email, String password, String role, Long idClient, Double discount, Long clientIdUser) {
        super(idUser, name, surname, yearOld, sex, email, password, role);
        this.idClient = idClient;
        this.discount = discount;
        this.clientIdUser = clientIdUser;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Long getClientIdUser() {
        return clientIdUser;
    }

    public void setClientIdUser(Long clientIdUser) {
        this.clientIdUser = clientIdUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (idClient != null ? !idClient.equals(client.idClient) : client.idClient != null) return false;
        if (discount != null ? !discount.equals(client.discount) : client.discount != null) return false;
        return clientIdUser != null ? clientIdUser.equals(client.clientIdUser) : client.clientIdUser == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (clientIdUser != null ? clientIdUser.hashCode() : 0);
        return result;
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