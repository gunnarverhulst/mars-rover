package io.tripled.marsrover.cli.message.messages;

public class LandingAlreadyLandedMessage implements Message{

    private final String message = "Rover has already landed.\n\n" +
            "[Please enter a command] : ";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
