package io.tripled.marsrover.ui.cli.messages;


import io.tripled.marsrover.api.message.Message;

public class EndMessage implements Message {
    private final String message = "*****************END*****************";


    @Override
    public String messageToBePrinted() {
        return message;
    }
}
