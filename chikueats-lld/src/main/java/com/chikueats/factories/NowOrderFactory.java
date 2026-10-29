package com.chikueats.factories;

import com.chikueats.models.*;
import com.chikueats.strategies.IPaymentStrategy;

import java.util.List;

public class NowOrderFactory implements IOrderFactory {
    @Override
    public Order createOrder(User user, Cart cart, Restaurant restaurant,
                             List<MenuItem> items, IPaymentStrategy paymentStrategy,
                             double totalCost, String orderType) {
        Order order;
        if ("Delivery".equalsIgnoreCase(orderType)) {
            DeliveryOrder d = new DeliveryOrder();
            d.setUserAddress(user.getAddress());
            order = d;
        } else if ("Pickup".equalsIgnoreCase(orderType)) {
            PickupOrder p = new PickupOrder();
            p.setRestaurantAddress(restaurant.getLocation());
            order = p;
        } else {
            throw new IllegalArgumentException("Unknown order type: " + orderType);
        }
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setItems(items);
        order.setPaymentStrategy(paymentStrategy);
        order.setTotal(totalCost);
        return order;
    }
}
