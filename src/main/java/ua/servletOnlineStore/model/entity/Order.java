package ua.servletOnlineStore.model.entity;

public class Order extends Product {

    private int orderId;
    private int pId;
    private int uId;
    private int quantity;
    private String date;

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public Order() {
    }

    public Order(int orderId, int uId, int quantity, String date) {
        this.orderId = orderId;
        this.uId = uId;
        this.quantity = quantity;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", pId=" + pId +
                ", uId=" + uId +
                ", quantity=" + quantity +
                ", date='" + date + '\'' +
                '}';
    }
}
