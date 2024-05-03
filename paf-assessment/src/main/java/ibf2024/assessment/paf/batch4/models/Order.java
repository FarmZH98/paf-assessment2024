package ibf2024.assessment.paf.batch4.models;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private Date date; //orderDate
    private int breweryId;
    private List<BeerOrder> orders;

    public Order() {}

    public Order(String orderId, Date orderDate, int breweryId, List<BeerOrder> orders) {
        this.orderId = orderId;
        this.date = orderDate;
        this.breweryId = breweryId;
        this.orders = orders;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date orderDate) {
        this.date = orderDate;
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
