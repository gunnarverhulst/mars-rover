package io.tripled.marsrover.ui.cli.messages;

import io.tripled.marsrover.businesslogic.message.Message;

public class StateErrorMessage implements Message {

    private final String message = """
                Rover has not landed yet.
                
                [Please enter a command]""";
    @Override
    public String messageToBePrinted() {
        return message;
    }
}
