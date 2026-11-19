# Parking Lot System: UML-Based Design and Workflow

This plain Java Maven project has been rebuilt around the UML supplied by the user. It uses no Spring, Spring Boot, database, or external library. The class structure separates the parking-lot singleton, physical model, vehicle hierarchy, interchangeable parking algorithms, and interchangeable fee rules.

## Visual UML

Open [ParkingLotSystem_UML.svg](ParkingLotSystem_UML.svg) for the complete visual class diagram and runtime flow. The SVG uses class-shaped boxes, association/composition/inheritance arrows, and flowchart shapes for parking and exit operations.

## Requirements and choices

- Each vehicle has a license number and a size: `Bike` is `SMALL`, `Car` is `MEDIUM`, and `Truck` is `LARGE`.
- Parking spots have one size and can hold at most one vehicle. A spot fits a vehicle of the same or smaller size.
- A floor maps spot IDs to spots and finds a free compatible spot.
- `ParkingLotSystem` is a singleton holding floors, the active ticket map, a parking strategy, and a fee strategy.
- Parking algorithms are interchangeable: `NearestFirstStrategy`, `BestFitStrategy`, and `FarthestFirstStrategy`.
- Fee policies are interchangeable: a fixed hourly rate (`FlatRateFeeStrategy`) or a rate based on vehicle size (`VehicleBasedFeeStrategy`).
- Entry and exit operations on the singleton are synchronized to avoid double parking or releasing the same spot twice within this JVM.

## How the classes connect

### `ParkingLotDemo`

This is the command-line entry point. It configures sample floors and spots, retrieves the singleton, reads commands, creates concrete vehicle classes, and calls `parkVehicle` or `unparkVehicle`. It also lets the operator change active strategies.

### `ParkingLotSystem`

This is the application-level coordinator and singleton. It owns the floor list, maps active licenses to tickets, asks the configured strategy for a spot, creates tickets, calls the fee policy at exit, and updates spot occupancy. It does not decide a particular search order or fee formula itself; those rules live behind interfaces.

### Floors, spots, tickets, and vehicles

- `ParkingFloor` owns a map of its spots and can locate an available compatible spot.
- `ParkingSpot` has an ID, a `VehicleSize`, and an optional currently parked vehicle. `parkVehicle` validates fit and availability; `unparkVehicle` clears occupancy.
- `ParkingTicket` records the ticket ID, spot, entry/exit epoch-millisecond timestamps, and vehicle. Active tickets have no exit timestamp.
- `Vehicle` is abstract; `Bike`, `Car`, and `Truck` provide the size through their constructors.
- `VehicleSize` is the shared size scale used by both allocation and pricing.

### Strategy interfaces

`ParkingStrategy.findSpot(floors, vehicle)` returns an optional compatible spot. Nearest-first chooses the lowest-numbered floor, best-fit chooses the smallest adequate spot, and farthest-first searches high floors and spot IDs first. `ParkingLotSystem` can replace this policy at runtime with `setParkingStrategy`.

`FeeStrategy.calculateFee(ticket)` reads the closed ticket timestamps. Flat-rate charges the same rate for every vehicle; vehicle-based pricing uses $1.00/hour for small, $2.50/hour for medium, and $5.00/hour for large vehicles. Both bill a minimum hour and round partial hours up. The strategy can be changed at runtime with `setFeeStrategy`.

## Check-in flow

For `IN CAR ABC-123`:

1. The console parses `CAR` and creates a `Car`, which normalizes and validates the license number.
2. `ParkingLotSystem.parkVehicle` checks whether the license already has an active ticket.
3. The singleton delegates spot search to the current `ParkingStrategy`.
4. The strategy searches the floor and spot maps using its own ordering and only returns spots that fit the vehicle size.
5. The singleton marks the spot occupied, creates a ticket with a UUID and current timestamp, stores it in `activeTickets`, and returns it.
6. The console prints the ticket, floor, and spot IDs.

If the license is already active or no compatible space exists, the method returns `Optional.empty()` and the console explains that check-in failed.

## Check-out flow

For `OUT ABC-123`:

1. The console passes the license to `ParkingLotSystem.unparkVehicle`.
2. The singleton looks up its active ticket. A missing ticket returns `Optional.empty()`.
3. It stamps the exit time, asks the active `FeeStrategy` to calculate the charge, releases the spot, and removes the active ticket.
4. The console prints the fee.

## Availability flow

`STATUS` calls `displayAvailability`. The singleton iterates the floors, each floor counts free spots from its spot map, and the console prints the available/total count for each floor.

## Strategy semantics

| Strategy | Search behavior |
| --- | --- |
| `NEAREST_FIRST` | Lowest floor number first; first compatible spot on that floor |
| `BEST_FIT` | Smallest available spot size that can fit; ties use spot ID |
| `FARTHEST_FIRST` | Highest floor number first; highest compatible spot ID first |

## Run the demo

Requires JDK 21 and Maven:

```bash
mvn clean package
java -cp target/classes com.mayur.parking.controller.ParkingLotDemo
```

Example commands:

```text
IN BIKE BIKE-101
IN CAR CAR-202
PARKING_STRATEGY BEST_FIT
FEE_STRATEGY FLAT
STATUS
OUT CAR-202
QUIT
```

## Scope

The diagram's active ticket map and floor/spot inventory live in memory and are cleared when the process exits. Synchronization protects a singleton instance in one JVM only. A multi-process or persistent deployment would need a database transaction and shared locking. This implementation follows the diagram's strategy interfaces and keeps the demo focused on the requested low-level design.
