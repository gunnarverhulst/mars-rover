package io.tripled.marsrover.cli.message.messages;

import io.tripled.marsrover.service.message.messages.Message;
import io.tripled.marsrover.service.rover.Coordinate;

public class LandingMessage implements Message {

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
