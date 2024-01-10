package io.tripled.marsrover.message.messages;

import io.tripled.marsrover.rover.Coordinate;

public class LandingErrorMessage implements Message{

    private final String message;

    public LandingErrorMessage(Coordinate roverCoordinate) {
        this.message = "The coordinate [" + roverCoordinate.x() + "," + roverCoordinate.y() + "] is not a valid coordinate for the planet with max coordinate\n\n"+
                    "[Please enter a command]";
    }

    @Override
    public String messageToBePrinted() {

        return message;
    }
}
