package com.mayur.parking.model;

import java.util.Objects;

public final class ParkingSpot {
    private final String spotId;
    private final VehicleSize spotSize;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = Objects.requireNonNull(spotId, "Spot ID is required");
        this.spotSize = Objects.requireNonNull(spotSize, "Spot size is required");
    }

    public String getSpotId() { return spotId; }
    public VehicleSize getSpotSize() { return spotSize; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
    public boolean isAvailable() { return parkedVehicle == null; }

    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle != null && spotSize.ordinal() >= vehicle.getSize().ordinal();
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        if (!isAvailable()) throw new IllegalStateException("Spot " + spotId + " is already occupied");
        if (!canFitVehicle(vehicle)) throw new IllegalArgumentException("Vehicle does not fit spot " + spotId);
        parkedVehicle = vehicle;
    }

    public synchronized Vehicle unparkVehicle() {
        if (isAvailable()) throw new IllegalStateException("Spot " + spotId + " is already available");
        Vehicle vehicle = parkedVehicle;
        parkedVehicle = null;
        return vehicle;
    }
}
