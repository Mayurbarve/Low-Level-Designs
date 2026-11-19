package com.mayur.parking.controller;

import com.mayur.parking.model.Bike;
import com.mayur.parking.model.Car;
import com.mayur.parking.model.ParkingFloor;
import com.mayur.parking.model.ParkingSpot;
import com.mayur.parking.model.Truck;
import com.mayur.parking.model.Vehicle;
import com.mayur.parking.model.VehicleSize;
import com.mayur.parking.service.ParkingLotSystem;
import com.mayur.parking.strategy.BestFitStrategy;
import com.mayur.parking.strategy.FarthestFirstStrategy;
import com.mayur.parking.strategy.FlatRateFeeStrategy;
import com.mayur.parking.strategy.NearestFirstStrategy;
import com.mayur.parking.strategy.VehicleBasedFeeStrategy;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public final class ParkingLotDemo {
    private ParkingLotDemo() { }

    public static void main(String[] args) {
        ParkingLotSystem lot = ParkingLotSystem.getInstance();
        if (lot.getFloors().isEmpty()) configureLot(lot);

        System.out.println("=== Parking Lot System ===");
        System.out.println("Commands:");
        System.out.println("  IN <BIKE|CAR|TRUCK> <license>  |  OUT <license>  |  STATUS");
        System.out.println("  PARKING_STRATEGY <NEAREST|BEST_FIT|FARTHEST>  |  FEE_STRATEGY <FLAT|VEHICLE>");
        System.out.println("  QUIT");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) return;
                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("QUIT")) return;
                try {
                    handle(lot, line);
                } catch (RuntimeException exception) {
                    System.out.println("Error: " + exception.getMessage());
                }
            }
        }
    }

    private static void handle(ParkingLotSystem lot, String line) {
        String[] args = line.split("\\s+");
        switch (args[0].toUpperCase()) {
            case "IN" -> {
                if (args.length != 3) throw new IllegalArgumentException("Usage: IN <BIKE|CAR|TRUCK> <license>");
                Vehicle vehicle = createVehicle(args[1], args[2]);
                Optional<com.mayur.parking.model.ParkingTicket> ticket = lot.parkVehicle(vehicle);
                if (ticket.isEmpty()) {
                    System.out.println("No spot available, or this vehicle already has an active ticket.");
                } else {
                    var parked = ticket.get();
                    int floor = lot.getFloors().stream().filter(f -> f.spots().contains(parked.getSpot()))
                            .mapToInt(ParkingFloor::getFloorNumber).findFirst().orElseThrow();
                    System.out.printf("Parked. Ticket=%s Floor=%d Spot=%s%n", parked.getTicketId(), floor, parked.getSpot().getSpotId());
                }
            }
            case "OUT" -> {
                if (args.length != 2) throw new IllegalArgumentException("Usage: OUT <license>");
                Optional<Double> fee = lot.unparkVehicle(args[1]);
                if (fee.isEmpty()) System.out.println("No active parking ticket for this license.");
                else System.out.printf("Vehicle exited. Fee: $%.2f%n", fee.get());
            }
            case "STATUS" -> lot.displayAvailability();
            case "PARKING_STRATEGY" -> {
                if (args.length != 2) throw new IllegalArgumentException("Usage: PARKING_STRATEGY <NEAREST|BEST_FIT|FARTHEST>");
                switch (args[1].toUpperCase()) {
                    case "NEAREST" -> lot.setParkingStrategy(new NearestFirstStrategy());
                    case "BEST_FIT" -> lot.setParkingStrategy(new BestFitStrategy());
                    case "FARTHEST" -> lot.setParkingStrategy(new FarthestFirstStrategy());
                    default -> throw new IllegalArgumentException("Unknown parking strategy: " + args[1]);
                }
                System.out.println("Parking strategy: " + lot.getParkingStrategy().name());
            }
            case "FEE_STRATEGY" -> {
                if (args.length != 2) throw new IllegalArgumentException("Usage: FEE_STRATEGY <FLAT|VEHICLE>");
                switch (args[1].toUpperCase()) {
                    case "FLAT" -> lot.setFeeStrategy(new FlatRateFeeStrategy());
                    case "VEHICLE" -> lot.setFeeStrategy(new VehicleBasedFeeStrategy());
                    default -> throw new IllegalArgumentException("Unknown fee strategy: " + args[1]);
                }
                System.out.println("Fee strategy: " + lot.getFeeStrategy().name());
            }
            default -> throw new IllegalArgumentException("Unknown command: " + args[0]);
        }
    }

    private static Vehicle createVehicle(String type, String license) {
        return switch (type.toUpperCase()) {
            case "BIKE" -> new Bike(license);
            case "CAR" -> new Car(license);
            case "TRUCK" -> new Truck(license);
            default -> throw new IllegalArgumentException("Vehicle type must be BIKE, CAR, or TRUCK");
        };
    }

    private static void configureLot(ParkingLotSystem lot) {
        for (int floorNumber = 1; floorNumber <= 3; floorNumber++) {
            ParkingFloor floor = new ParkingFloor(floorNumber);
            addSpots(floor, "S", VehicleSize.SMALL, 3);
            addSpots(floor, "M", VehicleSize.MEDIUM, 4);
            addSpots(floor, "L", VehicleSize.LARGE, 2);
            lot.addFloor(floor);
        }
    }

    private static void addSpots(ParkingFloor floor, String prefix, VehicleSize size, int count) {
        for (int number = 1; number <= count; number++) {
            floor.addSpot(new ParkingSpot(prefix + floor.getFloorNumber() + "-" + String.format("%02d", number), size));
        }
    }
}
