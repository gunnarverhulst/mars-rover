package io.tripled.marsrover.cli.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class EndMessage implements Message {
    private final String message = "*****************END*****************";


    @Override
    public String messageToBePrinted() {
        return message;
    }
}
