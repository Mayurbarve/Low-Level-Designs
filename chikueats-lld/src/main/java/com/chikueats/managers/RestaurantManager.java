package com.chikueats.managers;

import com.chikueats.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {
    private static RestaurantManager instance;
    private final List<Restaurant> restaurants = new ArrayList<>();

    private RestaurantManager() {}

    public static synchronized RestaurantManager getInstance() {
        if (instance == null) instance = new RestaurantManager();
        return instance;
    }

    public void addRestaurant(Restaurant r) { restaurants.add(r); }

    public List<Restaurant> searchByLocation(String loc) {
        List<Restaurant> result = new ArrayList<>();
        String key = loc.toLowerCase();
        for (Restaurant r : restaurants) {
            if (r.getLocation().toLowerCase().equals(key)) result.add(r);
        }
        return result;
    }
}
