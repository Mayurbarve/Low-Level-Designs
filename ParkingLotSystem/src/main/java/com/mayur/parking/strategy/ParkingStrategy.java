package com.mayur.parking.strategy;

import com.mayur.parking.model.ParkingFloor;
import com.mayur.parking.model.ParkingSpot;
import com.mayur.parking.model.Vehicle;
import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
    String name();
}
