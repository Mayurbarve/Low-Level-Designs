package com.mayur.parking.model;

import java.util.Locale;
import java.util.Objects;

public abstract class Vehicle {
    private final String licenseNumber;
    private final VehicleSize size;

    protected Vehicle(String licenseNumber, VehicleSize size) {
        Objects.requireNonNull(licenseNumber, "License number is required");
        String normalized = licenseNumber.trim().toUpperCase(Locale.ROOT);
        if (!normalized.matches("[A-Z0-9-]{2,20}")) {
            throw new IllegalArgumentException("License number must contain 2-20 letters, digits, or hyphens");
        }
        this.licenseNumber = normalized;
        this.size = Objects.requireNonNull(size, "Vehicle size is required");
    }

    public String getLicenseNumber() { return licenseNumber; }
    public VehicleSize getSize() { return size; }
}
