package com.chikueats.factories;

import com.chikueats.models.*;
import com.chikueats.strategies.IPaymentStrategy;

import java.util.List;

public class ScheduledOrderFactory implements IOrderFactory {
    private final String scheduledTime;

    public ScheduledOrderFactory(String scheduledTime) { this.scheduledTime = scheduledTime; }

    @Override
    public Order createOrder(User user, Cart cart, Restaurant restaurant,
                             List<MenuItem> items, IPaymentStrategy paymentStrategy,
                             double totalCost, String orderType) {
        Order order = new NowOrderFactory()
                .createOrder(user, cart, restaurant, items, paymentStrategy, totalCost, orderType);
        order.setScheduled(scheduledTime);
        return order;
    }
}
