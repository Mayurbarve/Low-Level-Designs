package com.chikueats.models;

public class MenuItem {
    private final String code;
    private final String name;
    private final int price;

    public MenuItem(String code, String name, int price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getPrice() { return price; }

    @Override
    public String toString() { return name + " (Rs." + price + ")"; }
}
