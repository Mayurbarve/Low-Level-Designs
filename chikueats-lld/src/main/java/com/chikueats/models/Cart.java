package com.chikueats.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Restaurant restaurant;
    private final List<MenuItem> items = new ArrayList<>();

    public void addToCart(MenuItem item) {
        if (restaurant == null) {
            throw new IllegalStateException("Set a restaurant before adding items.");
        }
        items.add(item);
    }

    public double getTotalCost() {
        double total = 0;
        for (MenuItem item : items) total += item.getPrice();
        return total;
    }

    public boolean isEmpty() { return restaurant == null || items.isEmpty(); }

    public void clear() {
        items.clear();
        restaurant = null;
    }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
    public List<MenuItem> getItems() { return items; }
}
