package io.tripled.marsrover.message.messages;

public class LandingErrorEmptyCoordinateMessage implements Message{
    private final String message = "Unable to parse coordinates for landing. Expected two positive numbers [x y] within simulation boundaries";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
