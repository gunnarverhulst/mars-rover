package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.api.message.Message;

public class RoverLandingErrorXAndOrYNegativeMessage implements Message {

    private final String message;
    public RoverLandingErrorXAndOrYNegativeMessage(String xCoordinate, String yCoordinate) {
        message = "Invalid coordinates for landing. They must be greater than zero but were [" + xCoordinate + " , " + yCoordinate + "]";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
