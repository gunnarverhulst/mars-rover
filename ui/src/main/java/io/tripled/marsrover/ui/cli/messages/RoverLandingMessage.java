package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.api.message.Message;
import io.tripled.marsrover.vocabulary.rover.Coordinate;

public class RoverLandingMessage implements Message {

    private final String message;

    public RoverLandingMessage(String roverId, Coordinate roverCoordinate) {
        this.message = "Rover " + roverId + " landed at (" + roverCoordinate.x() + "," + roverCoordinate.y() + ") and is facing NORTH\n\n" +
                "[Please enter a command]";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
