package com.chikueats.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static int nextRestaurantId = 0;

    private final int restaurantId;
    private final String name;
    private final String location;
    private final List<MenuItem> menu = new ArrayList<>();   // Aggregation (1..*)

    public Restaurant(String name, String location) {
        this.restaurantId = ++nextRestaurantId;
        this.name = name;
        this.location = location;
    }

    public void addMenuItem(MenuItem item) { menu.add(item); }

    public int getRestaurantId() { return restaurantId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public List<MenuItem> getMenu() { return menu; }
}
