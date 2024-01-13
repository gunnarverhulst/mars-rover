package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;
import io.tripled.marsrover.businesslogic.rover.Coordinate;

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
