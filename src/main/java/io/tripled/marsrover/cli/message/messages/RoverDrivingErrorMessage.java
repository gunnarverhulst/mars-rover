package io.tripled.marsrover.cli.message.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class RoverDrivingErrorMessage implements Message {

    private final static String message = "Unable to parse coordinates for landing.";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
