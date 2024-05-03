package ibf2024.assessment.paf.batch4.models;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private Date orderDate;
    private int breweryId;
    private List<BeerOrder> orders;

    public Order() {}

    public Order(String orderId, Date orderDate, int breweryId, List<BeerOrder> orders) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.breweryId = breweryId;
        this.orders = orders;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public List<BeerOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<BeerOrder> orders) {
        this.orders = orders;
    }

    
}
