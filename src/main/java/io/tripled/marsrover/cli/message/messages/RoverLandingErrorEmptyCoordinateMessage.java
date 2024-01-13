package io.tripled.marsrover.cli.message.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class RoverLandingErrorEmptyCoordinateMessage implements Message {
    private final String message = "Unable to parse coordinates for landing. Expected two positive numbers [x y] within simulation boundaries";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
