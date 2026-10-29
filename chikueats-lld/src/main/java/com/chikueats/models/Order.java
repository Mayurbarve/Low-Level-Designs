package com.chikueats.models;

import com.chikueats.strategies.IPaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public abstract class Order {
    private static int nextOrderId = 0;

    protected final int orderId;
    protected User user;
    protected Restaurant restaurant;
    protected List<MenuItem> items = new ArrayList<>();
    protected IPaymentStrategy paymentStrategy;
    protected double total;
    protected String scheduled;   // null => order placed "now"

    public Order() { this.orderId = ++nextOrderId; }

    public boolean processPayment() {
        if (paymentStrategy == null) return false;
        paymentStrategy.pay(total);
        return true;
    }

    public abstract String getType();

    public int getOrderId() { return orderId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
    public List<MenuItem> getItems() { return items; }
    public void setItems(List<MenuItem> items) { this.items = new ArrayList<>(items); }
    public IPaymentStrategy getPaymentStrategy() { return paymentStrategy; }
    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) { this.paymentStrategy = paymentStrategy; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getScheduled() { return scheduled; }
    public void setScheduled(String scheduled) { this.scheduled = scheduled; }
}
