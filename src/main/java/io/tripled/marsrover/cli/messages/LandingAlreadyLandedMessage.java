package io.tripled.marsrover.cli.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class LandingAlreadyLandedMessage implements Message {

    private final String message = "Rover has already landed.\n\n" +
            "[Please enter a command] : ";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
