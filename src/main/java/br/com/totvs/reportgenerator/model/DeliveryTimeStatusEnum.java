package br.com.totvs.reportgenerator.model;

public enum DeliveryTimeStatusEnum {
    LATE("Atrasado"),
    IN_PROGRESS ("Desenvolvimento"),
    IN_TIME ("No prazo"),
    CANCELED ("Cancelado");

    private final String description;

    DeliveryTimeStatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
