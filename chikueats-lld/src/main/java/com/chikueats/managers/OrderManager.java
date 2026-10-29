package com.chikueats.managers;

import com.chikueats.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private final List<Order> orders = new ArrayList<>();

    private OrderManager() {}

    public static synchronized OrderManager getInstance() {
        if (instance == null) instance = new OrderManager();
        return instance;
    }

    public void addOrder(Order order) { orders.add(order); }

    public void listOrders() {
        System.out.println("\n--- All Orders ---");
        for (Order o : orders) {
            System.out.println(o.getType() + " order #" + o.getOrderId()
                    + " | user: " + o.getUser().getName()
                    + " | restaurant: " + o.getRestaurant().getName()
                    + " | total: Rs." + o.getTotal()
                    + (o.getScheduled() != null ? " | scheduled: " + o.getScheduled() : ""));
        }
    }

    public List<Order> getOrders() { return orders; }
}
