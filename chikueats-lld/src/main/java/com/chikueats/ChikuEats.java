package com.chikueats;

import com.chikueats.factories.IOrderFactory;
import com.chikueats.managers.OrderManager;
import com.chikueats.managers.RestaurantManager;
import com.chikueats.models.*;
import com.chikueats.services.NotificationService;
import com.chikueats.strategies.IPaymentStrategy;

import java.util.List;

/** Orchestration class: wires managers, factories, payment and notifications together. */
public class ChikuEats {

    public ChikuEats() { initializeRestaurants(); }

    private void initializeRestaurants() {
        Restaurant r1 = new Restaurant("Bikaner", "Delhi");
        r1.addMenuItem(new MenuItem("P1", "Chole Bhature", 120));
        r1.addMenuItem(new MenuItem("P2", "Samosa", 15));

        Restaurant r2 = new Restaurant("Tandoori Nights", "Delhi");
        r2.addMenuItem(new MenuItem("T1", "Paneer Tikka", 250));
        r2.addMenuItem(new MenuItem("T2", "Butter Naan", 40));

        Restaurant r3 = new Restaurant("Udupi Bhavan", "Bangalore");
        r3.addMenuItem(new MenuItem("U1", "Masala Dosa", 90));

        RestaurantManager rm = RestaurantManager.getInstance();
        rm.addRestaurant(r1);
        rm.addRestaurant(r2);
        rm.addRestaurant(r3);
    }

    public List<Restaurant> searchRestaurants(String location) {
        return RestaurantManager.getInstance().searchByLocation(location);
    }

    public void selectRestaurant(User user, Restaurant restaurant) {
        Cart cart = user.getCart();
        cart.clear();
        cart.setRestaurant(restaurant);
    }

    public void addToCart(User user, String itemCode) {
        Restaurant r = user.getCart().getRestaurant();
        if (r == null) {
            System.out.println("Please select a restaurant first.");
            return;
        }
        for (MenuItem item : r.getMenu()) {
            if (item.getCode().equals(itemCode)) {
                user.getCart().addToCart(item);
                return;
            }
        }
        System.out.println("Item " + itemCode + " not found.");
    }

    public void printUserCart(User user) {
        System.out.println("\nCart of " + user.getName() + ":");
        for (MenuItem item : user.getCart().getItems()) System.out.println("  " + item);
        System.out.println("  Total: Rs." + user.getCart().getTotalCost());
    }

    public Order checkoutNow(User user, String orderType, IPaymentStrategy payment,
                             IOrderFactory factory) {
        if (user.getCart().isEmpty()) {
            System.out.println("Cart is empty.");
            return null;
        }
        Cart cart = user.getCart();
        Order order = factory.createOrder(user, cart, cart.getRestaurant(),
                cart.getItems(), payment, cart.getTotalCost(), orderType);
        OrderManager.getInstance().addOrder(order);
        return order;
    }

    public void payForOrder(Order order) {
        if (order != null && order.processPayment()) {
            order.getUser().getCart().clear();
        }
    }

    public void notify(Order order) {
        new NotificationService(order).notifyUser();
    }
}
