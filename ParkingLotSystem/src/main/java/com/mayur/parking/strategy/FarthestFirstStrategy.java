package com.mayur.parking.strategy;

import com.mayur.parking.model.ParkingFloor;
import com.mayur.parking.model.ParkingSpot;
import com.mayur.parking.model.Vehicle;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class FarthestFirstStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        return floors.stream().sorted(Comparator.comparingInt(ParkingFloor::getFloorNumber).reversed())
                .map(floor -> floor.spots().stream().filter(ParkingSpot::isAvailable)
                        .filter(spot -> spot.canFitVehicle(vehicle))
                        .max(Comparator.comparing(ParkingSpot::getSpotId)))
                .flatMap(Optional::stream).findFirst();
    }

    @Override public String name() { return "FARTHEST_FIRST"; }
}
