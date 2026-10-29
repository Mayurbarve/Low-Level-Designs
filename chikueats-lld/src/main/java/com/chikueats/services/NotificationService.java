package com.chikueats.services;

import com.chikueats.models.Order;

public class NotificationService {
    private final Order order;

    public NotificationService(Order order) { this.order = order; }

    public void notifyUser() {
        System.out.println("Notification -> " + order.getUser().getName()
                + ": your " + order.getType() + " order #" + order.getOrderId()
                + " from " + order.getRestaurant().getName() + " is confirmed.");
    }
}
