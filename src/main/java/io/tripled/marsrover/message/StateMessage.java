package io.tripled.marsrover.message;

import io.tripled.marsrover.rover.RoverState;

public class StateMessage implements Message{
    private final String message;

    public StateMessage(RoverState roverState) {
        this.message = "Simulation has maxCoordinate " + roverState.simulationSize() + " with a total of " + calculateTotalNumberOfCoordinates(roverState.simulationSize()) + " coordinates.\n" +
                "Rover at Coordinates[x=" + roverState.roverCoordinate().x() + ", y=" + roverState.roverCoordinate().y() + "] is facing NORTH";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }

    private static int calculateTotalNumberOfCoordinates(int simulationSize) {
        return (simulationSize + 1) * (simulationSize + 1);
    }
}
