package io.tripled.marsrover.message;

import io.tripled.marsrover.rover.Coordinate;
import io.tripled.marsrover.rover.RoverState;

public class LandingMessage implements Message{

    private final String message;

    public LandingMessage(Coordinate roverCoordinate) {
        this.message = "Rover R1 landed at (" + roverCoordinate.x() + "," + roverCoordinate.y() + ") and is facing North\n\n" +
                "[Please enter a command]";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
