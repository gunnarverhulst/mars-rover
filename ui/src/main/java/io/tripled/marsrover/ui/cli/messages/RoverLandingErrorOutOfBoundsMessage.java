package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.api.message.Message;

public class RoverLandingErrorOutOfBoundsMessage implements Message {
    private final String message;

    public RoverLandingErrorOutOfBoundsMessage(int xCoordinate, int yCoordinate, int simulationSize) {
        message = "Invalid coordinates for landing. Coordinates out of bound. Max simulation is [" + simulationSize + "] but was [" + xCoordinate + ", " + yCoordinate + "]\n\n" +
            "[Please enter a command] : ";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
