package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;
import io.tripled.marsrover.businesslogic.rover.Coordinate;

public class RoverLandingMessage implements Message {

    private final String message;

    public RoverLandingMessage(Coordinate roverCoordinate) {
        this.message = "Rover R1 landed at (" + roverCoordinate.x() + "," + roverCoordinate.y() + ") and is facing NORTH\n\n" +
                "[Please enter a command]";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
