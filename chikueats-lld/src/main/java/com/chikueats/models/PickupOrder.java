package com.chikueats.models;

public class PickupOrder extends Order {
    private String restaurantAddress;

    public String getRestaurantAddress() { return restaurantAddress; }
    public void setRestaurantAddress(String restaurantAddress) { this.restaurantAddress = restaurantAddress; }

    @Override
    public String getType() { return "Pickup"; }
}
