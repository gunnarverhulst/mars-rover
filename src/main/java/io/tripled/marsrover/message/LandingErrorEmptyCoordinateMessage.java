package io.tripled.marsrover.message;

public class LandingErrorEmptyCoordinateMessage implements Message{
    private final String message = "Unable to parse coordinates for landing. Expected two positive numbers [x y]";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
