package com.chikueats.models;

public class DeliveryOrder extends Order {
    private String userAddress;

    public String getUserAddress() { return userAddress; }
    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    @Override
    public String getType() { return "Delivery"; }
}
