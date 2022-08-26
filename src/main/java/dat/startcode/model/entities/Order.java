package dat.startcode.model.entities;

import java.time.LocalDateTime;

public class Order {

    int orderId;
    int customerId;
    LocalDateTime dateTime;
    int carportType;
    int carportRequestId;
    int price;

    public Order(int orderId, int customerId, LocalDateTime dateTime, int carportType, int carportRequestId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.carportType = carportType;
        this.carportRequestId = carportRequestId;
    }

    public Order(int orderId, int customerId, LocalDateTime dateTime, int carportType, int price, int carportRequestId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.carportType = carportType;
        this.carportRequestId = carportRequestId;
        this.price = price;
    }

    public Order(int customerId, LocalDateTime dateTime, int price) {
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.price = price;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCarportType() {
        return carportType;
    }

    public int getCarportRequestId() {
        return carportRequestId;
    }
}
