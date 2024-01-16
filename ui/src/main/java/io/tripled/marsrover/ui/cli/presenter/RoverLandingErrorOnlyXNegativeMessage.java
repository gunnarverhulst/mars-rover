package io.tripled.marsrover.ui.cli.presenter;

import io.tripled.marsrover.api.message.Message;

public class RoverLandingErrorOnlyXNegativeMessage implements Message {

    private String message;
    public RoverLandingErrorOnlyXNegativeMessage(String xCoordinate) {
        message = "Invalid coordinates for landing. They must be greater than zero but were [" + xCoordinate + " , y]";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
