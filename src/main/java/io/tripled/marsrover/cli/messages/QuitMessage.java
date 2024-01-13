package io.tripled.marsrover.cli.messages;

import io.tripled.marsrover.service.message.messages.Message;

public class QuitMessage implements Message {
    private final String message = "Quitting application";


    @Override
    public String messageToBePrinted() {
        return message;
    }
}
