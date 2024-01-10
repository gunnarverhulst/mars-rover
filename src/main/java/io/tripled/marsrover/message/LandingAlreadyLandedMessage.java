package io.tripled.marsrover.message;

public class LandingAlreadyLandedMessage implements Message{

    private final String message = "Rover has already landed.\n\n" +
            "[Please enter a command] : ";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
