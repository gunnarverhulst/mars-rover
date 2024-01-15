package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;

public class RoverDrivingErrorMessage implements Message {

    private final static String message = "Unable to parse coordinates for landing.";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
