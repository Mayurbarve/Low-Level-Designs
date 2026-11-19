package com.mayur.parking.strategy;

import com.mayur.parking.model.ParkingFloor;
import com.mayur.parking.model.ParkingSpot;
import com.mayur.parking.model.Vehicle;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class BestFitStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        return floors.stream().sorted(Comparator.comparingInt(ParkingFloor::getFloorNumber))
                .flatMap(floor -> floor.spots().stream()
                        .filter(ParkingSpot::isAvailable)
                        .filter(spot -> spot.canFitVehicle(vehicle))
                        .map(spot -> new Candidate(floor.getFloorNumber(), spot)))
                .min(Comparator.comparingInt((Candidate candidate) -> candidate.spot().getSpotSize().ordinal())
                        .thenComparingInt(Candidate::floorNumber)
                        .thenComparing(candidate -> candidate.spot().getSpotId()))
                .map(Candidate::spot);
    }

    @Override public String name() { return "BEST_FIT"; }

    private record Candidate(int floorNumber, ParkingSpot spot) { }
}
