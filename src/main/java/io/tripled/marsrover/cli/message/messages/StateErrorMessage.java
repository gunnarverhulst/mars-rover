package io.tripled.marsrover.cli.message.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class StateErrorMessage implements Message {

    private final String message = """
                Rover has not landed yet.\n\n"+
                "[Please enter a command]""";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
