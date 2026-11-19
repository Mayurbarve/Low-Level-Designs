package com.mayur.parking.strategy;

import com.mayur.parking.model.ParkingFloor;
import com.mayur.parking.model.ParkingSpot;
import com.mayur.parking.model.Vehicle;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class NearestFirstStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        return floors.stream().sorted(Comparator.comparingInt(ParkingFloor::getFloorNumber))
                .map(floor -> floor.findAvailableSpot(vehicle)).flatMap(Optional::stream).findFirst();
    }

    @Override public String name() { return "NEAREST_FIRST"; }
}
