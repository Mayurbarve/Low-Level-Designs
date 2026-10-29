package com.chikueats;

import com.chikueats.factories.NowOrderFactory;
import com.chikueats.factories.ScheduledOrderFactory;
import com.chikueats.managers.OrderManager;
import com.chikueats.models.*;
import com.chikueats.strategies.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ChikuEats chikueats = new ChikuEats();
        User user = new User(101, "Aditya", "Delhi");

        // 1. Search restaurants by location
        List<Restaurant> found = chikueats.searchRestaurants("Delhi");
        System.out.println("Restaurants in Delhi:");
        for (Restaurant r : found) System.out.println("  " + r.getRestaurantId() + ". " + r.getName());

        // 2. Select restaurant and add items
        chikueats.selectRestaurant(user, found.get(0));
        chikueats.addToCart(user, "P1");
        chikueats.addToCart(user, "P2");
        chikueats.printUserCart(user);

        // 3. Checkout: delivery order NOW, paid by UPI
        Order o1 = chikueats.checkoutNow(user, "Delivery",
                new UpiPaymentStrategy("aditya@upi"), new NowOrderFactory());
        chikueats.payForOrder(o1);
        chikueats.notify(o1);

        // 4. Second order: scheduled pickup, paid by credit card
        chikueats.selectRestaurant(user, found.get(1));
        chikueats.addToCart(user, "T1");
        chikueats.addToCart(user, "T2");
        Order o2 = chikueats.checkoutNow(user, "Pickup",
                new CreditCardPaymentStrategy("1234567812345678"),
                new ScheduledOrderFactory("2026-10-30 20:00"));
        chikueats.payForOrder(o2);
        chikueats.notify(o2);

        OrderManager.getInstance().listOrders();
    }
}
