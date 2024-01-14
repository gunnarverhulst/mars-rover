package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;

public class RoverLandingErrorOnlyXMessage implements Message{

    private String message = "";

    public RoverLandingErrorOnlyXMessage(String xCoordinate) {
        message = "Unable to parse coordinates for landing. Expected two positive numbers [x y] but was [" + xCoordinate + ", y]";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
