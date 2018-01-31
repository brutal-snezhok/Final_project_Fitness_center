package com.tsyrulik.dmitry.model.entity;

public class ClientOrder {
    private Long idOrder;
    private String typeOfTraining;
    private Double costOfLessons;
    private Long idClient;
    private Long idTrainer;

    public ClientOrder(Long idOrder, String typeOfTraining, Double costOfLessons, Long idClient, Long idTrainer) {
        this.idOrder = idOrder;
        this.typeOfTraining = typeOfTraining;
        this.costOfLessons = costOfLessons;
        this.idClient = idClient;
        this.idTrainer = idTrainer;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getTypeOfTraining() {
        return typeOfTraining;
    }

    public void setTypeOfTraining(String typeOfTraining) {
        this.typeOfTraining = typeOfTraining;
    }

    public Double getCostOfLessons() {
        return costOfLessons;
    }

    public void setCostOfLessons(Double costOfLessons) {
        this.costOfLessons = costOfLessons;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(Long idTrainer) {
        this.idTrainer = idTrainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientOrder that = (ClientOrder) o;

        if (idOrder != null ? !idOrder.equals(that.idOrder) : that.idOrder != null) return false;
        if (typeOfTraining != null ? !typeOfTraining.equals(that.typeOfTraining) : that.typeOfTraining != null)
            return false;
        if (costOfLessons != null ? !costOfLessons.equals(that.costOfLessons) : that.costOfLessons != null)
            return false;
        if (idClient != null ? !idClient.equals(that.idClient) : that.idClient != null) return false;
        return idTrainer != null ? idTrainer.equals(that.idTrainer) : that.idTrainer == null;
    }

    @Override
    public int hashCode() {
        int result = idOrder != null ? idOrder.hashCode() : 0;
        result = 31 * result + (typeOfTraining != null ? typeOfTraining.hashCode() : 0);
        result = 31 * result + (costOfLessons != null ? costOfLessons.hashCode() : 0);
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + (idTrainer != null ? idTrainer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientOrder{" +
                "idOrder=" + idOrder +
                ", typeOfTraining='" + typeOfTraining + '\'' +
                ", costOfLessons=" + costOfLessons +
                ", idClient=" + idClient +
                ", idTrainer=" + idTrainer +
                '}';
    }
}