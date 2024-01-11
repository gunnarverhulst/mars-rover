package io.tripled.marsrover.cli.message.messages;

public class RoverDrivingErrorMessage implements Message{

    private final static String message = "Unable to parse coordinates for landing.";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
