package ua.servletOnlineStore.model.entity;

public class Cart extends Product {

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart() {

    }
}
