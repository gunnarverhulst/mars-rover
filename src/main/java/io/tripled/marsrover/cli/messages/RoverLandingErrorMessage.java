package io.tripled.marsrover.cli.messages;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.service.rover.Coordinate;

public class RoverLandingErrorMessage implements Message {

    private final String message;

    public RoverLandingErrorMessage(Coordinate roverCoordinate) {
        this.message = "The coordinate [" + roverCoordinate.x() + "," + roverCoordinate.y() + "] is not a valid coordinate for the planet with max coordinate\n\n"+
                    "[Please enter a command]";
    }

    @Override
    public String messageToBePrinted() {

        return message;
    }
}
