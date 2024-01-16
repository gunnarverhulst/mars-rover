package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.api.message.Message;

public class LandingAlreadyLandedMessage implements Message {

    private final String message = "Rover has already landed.\n\n" +
            "[Please enter a command] : ";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
