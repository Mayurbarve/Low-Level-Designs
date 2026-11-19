package com.mayur.parking.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class ParkingFloor {
    private final int floorNumber;
    private final Map<String, ParkingSpot> spots = new LinkedHashMap<>();

    public ParkingFloor(int floorNumber) {
        if (floorNumber < 0) throw new IllegalArgumentException("Floor number cannot be negative");
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() { return floorNumber; }
    public synchronized Map<String, ParkingSpot> getSpots() { return Map.copyOf(spots); }

    public synchronized void addSpot(ParkingSpot spot) {
        Objects.requireNonNull(spot, "Spot is required");
        if (spots.putIfAbsent(spot.getSpotId(), spot) != null) {
            throw new IllegalArgumentException("Spot ID already exists on floor: " + spot.getSpotId());
        }
    }

    public synchronized Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle) {
        return spots.values().stream().filter(ParkingSpot::isAvailable)
                .filter(spot -> spot.canFitVehicle(vehicle)).findFirst();
    }

    public synchronized Collection<ParkingSpot> spots() { return java.util.List.copyOf(spots.values()); }
    public synchronized long availableSpotCount() { return spots.values().stream().filter(ParkingSpot::isAvailable).count(); }
}
