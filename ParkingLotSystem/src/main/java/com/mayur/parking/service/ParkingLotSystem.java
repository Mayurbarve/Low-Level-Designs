package com.mayur.parking.service;

import com.mayur.parking.model.ParkingFloor;
import com.mayur.parking.model.ParkingSpot;
import com.mayur.parking.model.ParkingTicket;
import com.mayur.parking.model.Vehicle;
import com.mayur.parking.strategy.FeeStrategy;
import com.mayur.parking.strategy.NearestFirstStrategy;
import com.mayur.parking.strategy.ParkingStrategy;
import com.mayur.parking.strategy.VehicleBasedFeeStrategy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/** Single in-memory parking lot. All entry/exit operations share this instance's monitor. */
public final class ParkingLotSystem {
    private static final ParkingLotSystem INSTANCE = new ParkingLotSystem();

    private ParkingStrategy parkingStrategy = new NearestFirstStrategy();
    private FeeStrategy feeStrategy = new VehicleBasedFeeStrategy();
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();

    private ParkingLotSystem() { }

    public static ParkingLotSystem getInstance() { return INSTANCE; }

    public synchronized void addFloor(ParkingFloor floor) {
        Objects.requireNonNull(floor, "Floor is required");
        if (floors.stream().anyMatch(existing -> existing.getFloorNumber() == floor.getFloorNumber())) {
            throw new IllegalArgumentException("Floor number already exists: " + floor.getFloorNumber());
        }
        floors.add(floor);
        floors.sort(Comparator.comparingInt(ParkingFloor::getFloorNumber));
    }

    public synchronized Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "Vehicle is required");
        if (activeTickets.containsKey(vehicle.getLicenseNumber())) return Optional.empty();

        Optional<ParkingSpot> candidate = parkingStrategy.findSpot(List.copyOf(floors), vehicle);
        if (candidate.isEmpty()) return Optional.empty();

        ParkingSpot spot = candidate.get();
        spot.parkVehicle(vehicle);
        ParkingTicket ticket = new ParkingTicket(UUID.randomUUID().toString(), spot, System.currentTimeMillis(), vehicle);
        activeTickets.put(vehicle.getLicenseNumber(), ticket);
        return Optional.of(ticket);
    }

    public synchronized Optional<Double> unparkVehicle(String licenseNumber) {
        if (licenseNumber == null || licenseNumber.isBlank()) return Optional.empty();
        String normalized = licenseNumber.trim().toUpperCase(java.util.Locale.ROOT);
        ParkingTicket ticket = activeTickets.get(normalized);
        if (ticket == null) return Optional.empty();

        ticket.markExited(System.currentTimeMillis());
        double fee = feeStrategy.calculateFee(ticket);
        ticket.getSpot().unparkVehicle();
        activeTickets.remove(normalized);
        return Optional.of(fee);
    }

    public synchronized void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = Objects.requireNonNull(parkingStrategy, "Parking strategy is required");
    }

    public synchronized void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = Objects.requireNonNull(feeStrategy, "Fee strategy is required");
    }

    public synchronized ParkingStrategy getParkingStrategy() { return parkingStrategy; }
    public synchronized FeeStrategy getFeeStrategy() { return feeStrategy; }
    public synchronized List<ParkingFloor> getFloors() { return List.copyOf(floors); }
    public synchronized Map<String, ParkingTicket> getActiveTickets() { return Map.copyOf(activeTickets); }

    public synchronized void displayAvailability() {
        System.out.println("Parking availability:");
        for (ParkingFloor floor : floors) {
            long available = floor.availableSpotCount();
            long total = floor.spots().size();
            System.out.printf("  Floor %d: %d/%d spots available%n", floor.getFloorNumber(), available, total);
        }
    }
}
