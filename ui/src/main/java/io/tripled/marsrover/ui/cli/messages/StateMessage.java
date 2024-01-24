package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.api.message.Message;
import io.tripled.marsrover.vocabulary.rover.RoverState;

public class StateMessage implements Message {
    private final String message;

    public StateMessage(int simulationSize, RoverState roverState) {
        this.message = "Simulation has maxCoordinate " + simulationSize + " with a total of " + calculateTotalNumberOfCoordinates(simulationSize) + " coordinates.\n" +
                "Rover at Coordinates[x=" + roverState.roverCoordinate().x() + ", y=" + roverState.roverCoordinate().y() + "] is facing " + roverState.heading();
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }

    private static int calculateTotalNumberOfCoordinates(int simulationSize) {
        return (int) Math.pow((simulationSize+1), 2);
    }
}
