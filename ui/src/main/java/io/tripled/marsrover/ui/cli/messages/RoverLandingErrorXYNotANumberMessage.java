package io.tripled.marsrover.ui.cli.messages;


import io.tripled.marsrover.api.message.Message;

public class RoverLandingErrorXYNotANumberMessage implements Message {
    private String message = "";

    public RoverLandingErrorXYNotANumberMessage(String xCoordinate, String yCoordinate) {
        message = "Unable to parse coordinates for landing. Expected two positive numbers [x y] but was [" + xCoordinate + ", " + yCoordinate + "]";
    }

    @Override
    public String messageToBePrinted() {
        return message;
    }
}
