package io.github.edsonzuchi.server.core.entity;

/**
 * Objeto para armazenar informações do quarto
 */
public class Room {

    Integer quantity;
    Integer type;
    Double price;

    public Room(Integer type, Integer quantity, Double price) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity(Integer quantity) {
        this.quantity -= quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return quantity+" quartos do tipo "+type+" estao disponiveis por "+price+" reais por noite";
    }
}
