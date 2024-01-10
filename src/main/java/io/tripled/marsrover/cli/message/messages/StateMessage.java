package io.tripled.marsrover.cli.message.messages;

import io.tripled.marsrover.service.rover.RoverState;

public class StateMessage implements Message{
    private final String message;

    public StateMessage(int simulationSize, RoverState roverState) {
        this.message = "Simulation has maxCoordinate " + simulationSize + " with a total of " + calculateTotalNumberOfCoordinates(simulationSize) + " coordinates.\n" +
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
